package org.zyf.javabasic.sensitive;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.rits.cloning.Cloner;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.sensitive.base.BusinessEnum;
import org.zyf.javabasic.sensitive.base.ContainsSensitiveWordResult;
import org.zyf.javabasic.sensitive.base.SensitiveMatchSceneEnum;
import org.zyf.javabasic.sensitive.base.SensitiveMerchantTypeEnum;
import org.zyf.javabasic.sensitive.base.SensitiveWordMuslimType;
import org.zyf.javabasic.sensitive.base.SensitiveWordQueType;
import org.zyf.javabasic.sensitive.base.SensitiveWordWithMuslim;
import org.zyf.javabasic.sensitive.base.SensitiveWordWithQua;
import org.zyf.javabasic.sensitive.base.SensitiveWordWithType;
import org.zyf.javabasic.sensitive.base.ValidateResult;
import org.zyf.javabasic.sensitive.base.ValidateRule;
import org.zyf.javabasic.sensitive.base.WordDomain;
import org.zyf.javabasic.sensitive.base.WordRuleDomain;
import org.zyf.javabasic.sensitive.model.HomonyModel;
import org.zyf.javabasic.sensitive.model.KeywordModel;
import org.zyf.javabasic.sensitive.model.RegularModel;
import org.zyf.javabasic.sensitive.service.WmSensitiveWordWhiteListService;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/24  20:13
 */
@Service
@Log4j2
public class ValidateService {

    @Resource
    private KeywordModel keywordModel;
    @Resource
    private RegularModel regularModel;
    @Resource
    private HomonyModel homonyModel;
    @Autowired
    private WmSensitiveWordWhiteListService wmSensitiveWordWhiteListService;

    /**
     * 4.按规则进行校验
     * 后续如有性能瓶颈可改为并行校验
     *
     * @return 返回最终命中的结果
     */
    private List<ValidateResult> validateByRule(List<ValidateRule> rules, String content, Long wmPoiId) {
        List<ValidateResult> result = Lists.newArrayList();
        for (ValidateRule rule : rules) {
            switch (rule) {
                case KEYWORD:
                    result.addAll(keywordModel.validate(content, wmPoiId));
                    break;
                case NLP:
//                    result.addAll(nlpModel.validate(content));
                    break;
                case REGULAR:
                    result.addAll(regularModel.validate(content, wmPoiId));
                    break;
                case HOMONY:
                    result.addAll(homonyModel.validate(content, wmPoiId));
                    break;
                default:
                    log.warn("[validate]当前类型:{}，暂无校验实现", rule);
                    break;
            }
        }
        return result;
    }

    /**
     * 2.判断是否需要进行拦截
     *
     * @return true, 无需拦截，false需要拦截
     */
    private boolean isfilter() {
        return false;
    }

    /**
     * 校验总接口
     *
     * @param wmPoiId
     * @param poiTagIds
     * @param rules
     * @param cityId
     * @param validatePair
     * @return
     */
    public ContainsSensitiveWordResult validate(Long wmPoiId, List<Long> poiTagIds, List<ValidateRule> rules, Integer cityId, ValidatePair validatePair,
                                                BusinessEnum bizType, long spTagId, long platformBrandId, boolean needQueGuide, Long spuId) {
        ContainsSensitiveWordResult result = new ContainsSensitiveWordResult(false, validatePair.content,
                Sets.newHashSet(), Sets.newHashSet(), Sets.newHashSet(), Sets.newHashSet(), Sets.newHashSet());
        /*1.先走过滤器，因目前在thrift接口层已经做了过滤，此处咱做保留，后续如果有需求可采用过滤链的形式*/
        if (isfilter()) {
            log.info("当前请求被过滤器拦截，无需校验");
            return result;
        }

        /*2.进行校验：本处进行全词校验，不区分清真词或普通敏感词等内容*/
        List<ValidateResult> checkResults = validateByRule(rules, validatePair.content, wmPoiId);

        // 通过审核场景过滤，事前事中分开
        checkResults = filterResultByScene(checkResults);

        /*3.校验结果过滤：需要保持原有逻辑的基础上区分下清真的内容*/
        checkResults = checkResultsFilter(wmPoiId, checkResults, cityId, validatePair, poiTagIds, bizType, needQueGuide);
        if (CollectionUtils.isNotEmpty(checkResults)) {
            result.setIllegal(true);
        }

        /*4.返回所有命中的敏感词*/
        Set<String> sensitiveWordSet = Sets.newHashSet();
        Set<SensitiveWordWithType> sensitiveWordWithTypeSet = Sets.newHashSet();
        Set<SensitiveWordWithMuslim> sensitiveWordWithMuslimSet = Sets.newHashSet();
        Set<SensitiveWordWithQua> sensitiveWordWithQueSet = Sets.newHashSet();
        for (ValidateResult validateResult : checkResults) {
            sensitiveWordSet.add(validateResult.getSensitive());
            sensitiveWordWithTypeSet.add(validateResult.getSensitiveWordWithType());
            if (Objects.nonNull(validateResult.getSensitiveWordWithMuslim())) {
                sensitiveWordWithMuslimSet.add(validateResult.getSensitiveWordWithMuslim());
            }
            if (Objects.nonNull(validateResult.getSensitiveWordWithQua())) {
                sensitiveWordWithQueSet.add(validateResult.getSensitiveWordWithQua());
            }
        }
        result.setSensitiveWordSet(sensitiveWordSet);
        result.setSensitiveWordWithTypeSet(sensitiveWordWithTypeSet);
        result.setSensitiveWordWithMuslimSet(sensitiveWordWithMuslimSet);
        result.setSensitiveWordWithQueSet(sensitiveWordWithQueSet);

        // 避免 ConcurrentModificationException
        List<ValidateResult> checkResultsNew = Lists.newArrayList(checkResults);
        Set<String> sensitiveWordInWhiteListSet = Sets.newHashSet();
        for (ValidateResult validateResult : checkResultsNew) {
            if (wmSensitiveWordWhiteListService.isWhiteListBySensitiveWord(validateResult, validatePair, wmPoiId, spTagId, platformBrandId)) {
                String sensitive = validateResult.getSensitive();
                checkResults.remove(validateResult);
                result.getSensitiveWordSet().remove(sensitive);
                /*将在白名单中命中的敏感词加入到对应的sensitiveWordInWhiteListSet中*/
                sensitiveWordInWhiteListSet.add(sensitive);
                if (CollectionUtils.isEmpty(result.getSensitiveWordSet())) {
                    result.setIllegal(false);
                }
            }
        }
        result.setSensitiveWordInWhiteListSet(sensitiveWordInWhiteListSet);

        /*6.记录拦截日志log*/
//        validateLogService.writeLog(checkResults, wmPoiId, validatePair.content, cityId, validatePair.field, poiTagIds, rules, spuId, spTagId);
        return result;
    }

    /**
     * 通过审核场景过滤，敏感词的事前事中分开
     */
    public List<ValidateResult> filterResultByScene(List<ValidateResult> checkResults) {
        List<ValidateResult> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(checkResults)) {
            return result;
        }
        SensitiveMatchSceneEnum tracerSceneEnum = getSceneByTrace();
//        if (ConfigUtil.getTestLogSwitch()) {
//            log.info("#filterResultByScene , checkResults={}, tracerSceneEnum={}", JSON.toJSONString(checkResults), tracerSceneEnum);
//        }


        List<ValidateResult> checkResultsTmp = Cloner.standard().deepClone(checkResults);
        for (ValidateResult validateResult : checkResultsTmp) {
            if (null == validateResult.getWordDomain()) {
                continue;
            }
            SensitiveMatchSceneEnum wordScene = SensitiveMatchSceneEnum.findByValue(validateResult.getWordDomain().getScene());
            // 匹配场景不相同
            if (!tracerSceneEnum.equals(wordScene)) {
                continue;
            }
            result.add(validateResult);
        }
        return result;
    }

    /**
     * 5.校验结果，根据敏感词rule进行过滤
     *
     * @param checkResults
     * @return
     */
    private List<ValidateResult> checkResultsFilter(Long wmPoiId, List<ValidateResult> checkResults, final Integer cityLocationId,
                                                    ValidatePair validatePair, List<Long> poiTagIds, BusinessEnum bizType,
                                                    boolean needQueGuide) {
        if (CollectionUtils.isEmpty(checkResults)) {
            return checkResults;
        }
        for (int i = checkResults.size() - 1; i > -1; i--) {
            if (!checkResultValid(wmPoiId, checkResults.get(i), cityLocationId, validatePair, poiTagIds, bizType, needQueGuide)) {
                checkResults.remove(i);
            }
        }
        return checkResults;
    }

    /**
     * 校验单个敏感词是有效
     *
     * @param result         查询词库得到的校验结果
     * @param cityLocationId 城市信息过滤
     * @param validatePair   校验过滤字段信息
     * @param poiTagIds      过滤品类信息
     * @param bizType        业务数据类型：商品or药品标品
     * @return true 有效，false无效
     */
    private boolean checkResultValid(Long wmPoiId, ValidateResult result, final Integer cityLocationId,
                                     ValidatePair validatePair, List<Long> poiTagIds, BusinessEnum bizType,
                                     boolean needQueGuide) {
        if (null == result || null == result.getWordDomain()) {
            return false;
        }
        WordDomain word = result.getWordDomain();

        /*1.药品标品只过全词匹配*/
        if (null != bizType && bizType.equals(BusinessEnum.MEDIC_SP)) {
            WordRuleDomain rule = getRuleByType(1, word.getWordRules());
            return (null != rule);
        }

        if (CollectionUtils.isEmpty(word.getWordRules())) {
            log.error("无配置规则的敏感词信息异常，word={}", JSON.toJSONString(word));
        }
        /*2.其他*/
        WordRuleDomain rule = getRuleByPoiTagIds(poiTagIds, word.getWordRules(), validatePair.field);
        if (null == rule) {
            return false;
        }

        if (illegalRule(wmPoiId, result, rule, needQueGuide)) {
            return false;
        }

        /*3.过滤字段如果不匹配返回false*/
        String field = "," + rule.getField() + ",";
        if (!field.contains("," + validatePair.field + ",")) {
            return false;
        }

        /*4.匹配过滤区域分析：1.非清真门店识别词查看对应的敏感词的生效范围；2.清真门店词结合生效返回返回是否需要提醒门店补充资质信息*/
        if (needQueGuide && result.getSensitiveWordWithMuslim().getType() == SensitiveWordMuslimType.MUSLIM_IDENTIFY.getValue()) {
            /*清真门店识别处理*/
            if (validateLocation(rule, cityLocationId)) {
                result.getSensitiveWordWithQua().setType(SensitiveWordQueType.NEED.getValue());
                return true;
            }
            result.getSensitiveWordWithQua().setType(SensitiveWordQueType.NONNEED.getValue());
            return true;
        } else if (!validateLocation(rule, cityLocationId)) {
            return false;
        }
        return true;
    }

    /**
     * 校验地域是否满足
     *
     * @param rule
     * @param currCityId
     * @return
     */
    private boolean validateLocation(WordRuleDomain rule, Integer currCityId) {
        // 1.全部地域
        if ("0".equals(rule.getPoiRegionId()) || Objects.isNull(rule.getPoiRegionId())) {
            return true;
        }
        String regionIds = "," + rule.getPoiRegionId() + ",";
        // 2.先匹配城市
        if (regionIds.contains("," + currCityId + ",")) {
            return true;
        }
        // 3.匹配身份
//        Integer proviceId = poiService.getProvinceIdByCityId(currCityId);
        Integer proviceId = 22;
        if (null != proviceId && regionIds.contains("," + proviceId + ",")) {
            return true;
        }
        return false;
    }

    private WordRuleDomain getRuleByType(int type, List<WordRuleDomain> wordRules) {
        for (WordRuleDomain rule : wordRules) {
            if (rule.getType() == type) {
                return rule;
            }
        }
        return null;
    }

    /**
     * 优先匹配子，只需匹配一个，如果没有在匹配父级
     *
     * @param poiTagIds 主营品类子父节点，已排序
     * @param wordRules
     * @param field
     * @return
     */
    private WordRuleDomain getRuleByPoiTagIds(List<Long> poiTagIds, List<WordRuleDomain> wordRules, Integer field) {
        Iterator<Long> iterator = poiTagIds.iterator();
        while (iterator.hasNext()) {
            WordRuleDomain rule = getRuleByPoiTagId(iterator.next(), wordRules);
            if (Objects.isNull(rule)) {
                continue;
            }
            /*3.过滤字段如果不匹配返回false*/
            String ruleField = "," + rule.getField() + ",";
            if (ruleField.contains("," + field + ",")) {
                return rule;
            }
        }
        return null;
    }

    private WordRuleDomain getRuleByPoiTagId(Long poiTagId, List<WordRuleDomain> wordRules) {
        if (CollectionUtils.isEmpty(wordRules)) {
            return null;
        }
        //1.优先以配置的品类为准
        for (WordRuleDomain wordRuleDomain : wordRules) {
            String ids = "," + wordRuleDomain.poiTagId + ",";
            if (ids.contains("," + poiTagId + ",")) {
                return wordRuleDomain;
            }
        }

        //2.当敏感词未对当前品类配置个性化配置时走全部
        for (WordRuleDomain wordRuleDomain : wordRules) {
            if ("0".equals(wordRuleDomain.getPoiTagId()) || Objects.isNull(wordRuleDomain.getPoiTagId())) {
                return wordRuleDomain;
            }
        }
        return null;
    }

    /**
     * 验证规则合法性
     *
     * @param wmPoiId 门店ID
     * @param result  校验结果
     * @param rule    规则信息
     * @return true-非法规则
     */
    private boolean illegalRule(Long wmPoiId, ValidateResult result, WordRuleDomain rule, boolean needQueGuide) {
        /*1.如果匹配结果是泛化类型，但命中结果是非泛化类型，则不满足*/
        if (ValidateRule.NLP == result.getRule() && 1 != rule.getNlp()) {
            return true;
        }

        /*2.如果匹配结果NLP的全词匹配类型，但命中规则是泛化类型，则不满足*/
        if (ValidateRule.NLP_KEYWORD == result.getRule() && 0 != rule.getNlp()) {
            return true;
        }

        /*3.当门店非法时，如果对应的规则中存在清真的内容（即不是外卖门店），则需要将对应命中的敏感词删除*/
        if (wmPoiId <= 0 && rule.getMerchantType() != SensitiveMerchantTypeEnum.ALL.getValue()) {
            return true;
        }

        /*4.门店合法，分析对应的业务是否为外卖，不是外卖身份则规则中是清真*/
//        if (wmPoiId > 0) {
//            WmPoiAggre wmPoiAggre = poiService.getWmPoiAggreById(wmPoiId);
//            if (!Objects.isNull(wmPoiAggre) && wmPoiAggre.getBiz_org_code() != PoiOrgEnum.WAI_MAI.getCode() && rule.getMerchantType() != SensitiveMerchantTypeEnum.ALL.getValue()) {
//                return true;
//            }
//        }

        /*同音词下不考虑清真相关内容*/
        if (null == result.getSensitiveWordWithMuslim()) {
            return false;
        }

        /*5.如果是指定的清真无效词则直接过滤即可*/
        if (result.getSensitiveWordWithMuslim().getType() == SensitiveWordMuslimType.MUSLIM_INVALID.getValue()) {
            return true;
        }

        /*6.如果是指定的清真商家识别且🈶️不需要验证识别的其他词，则需要过滤*/
        if (result.getSensitiveWordWithMuslim().getType() == SensitiveWordMuslimType.MUSLIM_IDENTIFY.getValue() && !needQueGuide) {
            return true;
        }

        /*7.清真商家识别词对应商家类型为全部商家*/
        if (rule.getMerchantType() != SensitiveMerchantTypeEnum.ALL.getValue() && needQueGuide) {
            return true;
        }

        /*8.不是清真商家识别词的其他，但又需要进行清真识别的也需要去除*/
        if (result.getSensitiveWordWithMuslim().getType() != SensitiveWordMuslimType.MUSLIM_IDENTIFY.getValue() && needQueGuide) {
            return true;
        }

        return false;
    }


    /**
     * 通过Trace获取审核场景
     * {@link SensitiveMatchSceneEnum}
     */
    public SensitiveMatchSceneEnum getSceneByTrace() {
        SensitiveMatchSceneEnum sceneEnum = SensitiveMatchSceneEnum.BEFORE;
//        String scene = Tracer.getContext(WmSensitiveWordValidateThriftServiceImpl.SCENE);
//        if (StringUtils.isNotBlank(scene)) {
//            try {
//                sceneEnum = SensitiveMatchSceneEnum.findByValue(Integer.parseInt(scene));
//            } catch (NumberFormatException e) {
//                log.warn("{}", e);
//            }
//        }
        return sceneEnum;
    }
}
