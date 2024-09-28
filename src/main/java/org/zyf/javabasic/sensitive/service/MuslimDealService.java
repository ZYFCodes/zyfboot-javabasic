package org.zyf.javabasic.sensitive.service;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.sensitive.base.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yanfengzhang
 * @description 清真相关基本内容处理分析
 * @date 2022/7/25  17:49
 */
@Service
@Slf4j
public class MuslimDealService {

//    @Autowired
//    private WmLabelThriftService wmLabelThriftService;

    /**
     * 获取对应命中敏感词归属的商家类型(当前外卖身份上该内容的信息需要分析给出相关内容)
     *
     * @param wordDomain 敏感词基本信息(要求是命中的敏感词信息)
     * @param wmPoiId    门店ID
     * @return SensitiveWordWithMuslim 敏感词清真对应具体类型
     */
    public SensitiveWordWithMuslim getMuslimType(WordDomain wordDomain, Long wmPoiId) {
        SensitiveWordWithMuslim sensitiveWordWithMuslim = SensitiveWordWithMuslim.builder()
                .type(SensitiveWordMuslimType.NON_MUSLIM.getValue()).build();
        /*传递为null时直接视为普通词，该场景应该不存在*/
        if (Objects.isNull(wordDomain)) {
            return sensitiveWordWithMuslim;
        }

        /*1.获取对应改词当前的规则配置信息,为空场景一般不存在*/
        List<WordRuleDomain> wordRules = wordDomain.getWordRules();
        if (CollectionUtils.isEmpty(wordRules)) {
            return sensitiveWordWithMuslim;
        }

        /*2.分析其中的规则：只要有指明是清真的则需要结合门店信息来分析其归属信息*/
        for (WordRuleDomain wordRule : wordRules) {
            /*2.1查看对应词的过滤场景类型来直接判断是否是用于识别信息词*/
            if (wordRule.getScene() == SensitiveRuleSceneEnum.QZ_IDEENTIFICATION.getValue()) {
                sensitiveWordWithMuslim.setType(SensitiveWordMuslimType.MUSLIM_IDENTIFY.getValue());
                return sensitiveWordWithMuslim;
            }
            /*2.2对应规则中存在清真相关内容，则需要根据商家是否是清真门店来进行区分对应的内容*/
            if (wordRule.getMerchantType() != SensitiveMerchantTypeEnum.ALL.getValue()) {
                return getMuslimType(wordRule.getMerchantType(), wmPoiId, wordDomain);
            }
        }

        /*3.规则无清真配置则认为是"商家没有命中清真相关敏感词"*/
        return sensitiveWordWithMuslim;
    }

    /**
     * 获取对应命中敏感词归属的商家类型
     *
     * @param merchantType 商家类型规则： 0-【全部商家】 1-【清真商家】 2-【非清真商家】
     * @param wmPoiId      门店ID
     * @return SensitiveWordWithMuslim 敏感词清真对应具体类型
     */
    private SensitiveWordWithMuslim getMuslimType(Integer merchantType, Long wmPoiId, WordDomain wordDomain) {

        /*1.根据门店信息查看对应的门店是否是清真商家*/
        List<Long> subjectIds = Stream.of(wmPoiId).collect(Collectors.toList());
        Map<Long, Boolean> wmLabelInfo = Maps.newHashMap();
        try {
//            wmLabelInfo = wmLabelThriftService.haveLabelRel(subjectIds, LabelSubjectTypeEnum.POI.getCode(), ConfigUtil.getWmLabelIdForQZ());
        } catch (Exception e) {
//            log.error("调用门店接口存在异常：subjectIds={},subjectType={},labelId={}", subjectIds, LabelSubjectTypeEnum.POI.getCode(), ConfigUtil.getWmLabelIdForQZ(), e);
            return null;
        }
//        /*todo:上线前删除以下日志打印*/
//        if (log.isInfoEnabled()) {
//            log.info("根据门店信息查看对应的门店是否是清真商家，请求信息（subjectIds={},subjectType={},labelId={}），返回结果为:{}",
//                    subjectIds, LabelSubjectTypeEnum.POI.getCode(), ConfigUtil.getWmLabelIdForQZ(),
//                    wmLabelInfo);
//        }

        /*2.如果没有相关数据默认返回空值*/
        if (MapUtils.isEmpty(wmLabelInfo)) {
            return SensitiveWordWithMuslim.builder()
                    .type(SensitiveWordMuslimType.MUSLIM_INVALID.getValue())
                    .sensitiveWord(wordDomain.getWords()).build();
        }

        /*3.根据是否为清真商家已经对应规则来返回实际需要的敏感词清真类型*/
        boolean isMuslimPoi = wmLabelInfo.get(wmPoiId);
        if (isMuslimPoi && merchantType.equals(SensitiveMerchantTypeEnum.QZ.getValue())) {
            /*如果对应门店是清真门店且对应规则是清真商家敏感词规则*/
            return SensitiveWordWithMuslim.builder()
                    .type(SensitiveWordMuslimType.MUSLIM_POI.getValue())
                    .sensitiveWord(wordDomain.getWords()).build();
        }
        if (!isMuslimPoi && merchantType.equals(SensitiveMerchantTypeEnum.NON_QZ.getValue())) {
            /*如果对应门店是非清真门店且对应规则是非清真商家敏感词规则*/
            return SensitiveWordWithMuslim.builder()
                    .type(SensitiveWordMuslimType.MUSLIM_NON_POI.getValue())
                    .sensitiveWord(wordDomain.getWords()).build();
        }

        return SensitiveWordWithMuslim.builder()
                .type(SensitiveWordMuslimType.MUSLIM_INVALID.getValue())
                .sensitiveWord(wordDomain.getWords()).build();
    }
}

