package org.zyf.javabasic.sensitive.model;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.common.utils.CommonUtils;
import org.zyf.javabasic.common.utils.SensitiveConfigUtil;
import org.zyf.javabasic.sensitive.base.*;
import org.zyf.javabasic.sensitive.model.dfa.FilterSet;
import org.zyf.javabasic.sensitive.model.dfa.WordNode;
import org.zyf.javabasic.sensitive.service.BusinessIdentityService;
import org.zyf.javabasic.sensitive.service.MuslimDealService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:03
 */
@Component
@Slf4j
public class KeywordModel implements ValidateModel {
    /**
     * FIRST_CHAR_SET,FIRST_NODES_MAP在构建时是强一致性的，先使用volatile保证，后续如果需要保证超高并发下的一致性，可统一封装到校验器中
     * FIRST_CHAR_SET-存储首字；FIRST_NODES_MAP-存储节点
     */
    private static volatile FilterSet FIRST_CHAR_SET = new FilterSet();
    private static volatile Map<Integer, WordNode> FIRST_NODES_MAP = new HashMap<Integer, WordNode>(1024, 1);
    // 停顿词
    private static volatile Set<Integer> STOP_WORD_SET = new HashSet<>();
    // 敏感词过滤替换
    private static final char SIGN = '*';

    @Resource
    private BusinessIdentityService businessIdentityService;
    @Resource
    private MuslimDealService muslimDealService;

    @Override
    public boolean init(List<WordDomain> words) {
        if (CollectionUtils.isEmpty(words)) {
            log.warn("[sensitive keyword model init failure] words is empty");
            return false;
        }
        //1.更新dfa树
        addSensitiveWord(words);
        //2.支持过滤停顿符，目前暂时没有
        List<String> stopWords = SensitiveConfigUtil.getStopWords();
        addStopWord(stopWords);
        return true;
    }

    @Override
    public List<ValidateResult> validate(String content, Long wmPoiId) {
        List<ValidateResult> sensitives = Lists.newArrayList();
        if (StringUtils.isBlank(content)) {
            log.warn("content is blank ...");
            return sensitives;
        }
//        DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_MODEL_VALIDATE_CHART, DiggerConstants.KEYWORD_SENSITIVE_MODEL_VALIDATE_LINE);


        if (FIRST_CHAR_SET != null && FIRST_NODES_MAP != null) {
            char[] valueChars = content.toCharArray();
            // 当前检查的字符
            int v;
            for (int i = 0; i < valueChars.length; i++) {
                v = CommonUtils.charConvert(valueChars[i]);
                //1.快速找当前文本中第一个包含敏感词字符的位置
                if (!FIRST_CHAR_SET.contains(v)) {
                    continue;
                }
                //2.从当前位置进行深度计算，获取深度最长的(每个首字对应命中敏感词可能是多个)
                List<ValidateResult> validateResults = getSensitiveWordList(i, valueChars, wmPoiId);
                if (CollectionUtils.isEmpty(validateResults)) {
                    continue;
                }
                sensitives.addAll(validateResults);
            }
        }


        if (CollectionUtils.isNotEmpty(sensitives)) {
//            DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_MODEL_VALIDATE_HIT_CHART, DiggerConstants.HIT_KEYWORD_SENSITIVE_MODEL_LINE);
        }
        return sensitives;
    }


    /**
     * 增加停顿词
     *
     * @param words
     */
    private static void addStopWord(final List<String> words) {
        Set<Integer> stopWords = new HashSet<>();
        if (CollectionUtils.isNotEmpty(words)) {
            char[] chs;
            for (String curr : words) {
                chs = curr.toCharArray();
                for (char c : chs) {
                    stopWords.add(CommonUtils.charConvert(c));
                }
            }
        }
        STOP_WORD_SET = stopWords;
    }

    /**
     * 添加DFA节点
     *
     * @param words
     */
    private static void addSensitiveWord(final List<WordDomain> words) {
        FilterSet firstChars = new FilterSet();
        Map<Integer, WordNode> firstNodesMap = new HashMap<Integer, WordNode>(1024, 1);

        if (CollectionUtils.isEmpty(words)) {
            return;
        }
        char[] wordChars;
        int fchar;
        int lastIndex;
        // 首字母节点
        WordNode fnode;
        for (WordDomain word : words) {
            String sw = word.getWords();
            if (StringUtils.isEmpty(sw)) {
                continue;
            }
            wordChars = sw.toCharArray();
            //全角转半角
            fchar = CommonUtils.charConvert(wordChars[0]);
            // 没有首字定义
            if (!firstChars.contains(fchar)) {
                //首字标志位 可重复add
                firstChars.add(fchar);
                boolean isLast = wordChars.length == 1;
                String wordContent = isLast ? word.getWords() : null;
                fnode = new WordNode(fchar, isLast, wordContent);
                firstNodesMap.put(fchar, fnode);
            } else {
                fnode = firstNodesMap.get(fchar);
                if (!fnode.isLast() && wordChars.length == 1) {
                    fnode.setLast(true);
                    fnode.setWordContent(word.getWords());
                }
            }
            lastIndex = wordChars.length - 1;
            for (int i = 1; i < wordChars.length; i++) {
                fnode = fnode.addIfNoExist(CommonUtils.charConvert(wordChars[i]), i == lastIndex, word.getWords());
            }
        }
        FIRST_CHAR_SET = firstChars;
        FIRST_NODES_MAP = firstNodesMap;
    }

    /**
     * 获取实际敏感词集合信息
     *
     * @param start      找到的第一个字符的信息内容
     * @param valueChars 对应待校验的内容
     * @return 实际命中敏感词信息
     */
    private List<ValidateResult> getSensitiveWordList(int start, char[] valueChars, Long wmPoiId) {
        int v = CommonUtils.charConvert(valueChars[start]);
        /*实际每个根节点实际命中敏感词可能是多个*/
        List<ValidateWordNodeResult> hitWordResults = Lists.newArrayList();
        WordNode node = FIRST_NODES_MAP.get(v);
        StringBuilder sensitive = new StringBuilder((char) node.getValue() + "");
        if (node.isLast()) {
            // 单字匹配如：（日）
            hitWordResults.add(ValidateWordNodeResult.builder()
                    .sensitive(sensitive.toString())
                    .wordContent(node.getWordContent()).build());
        }

        for (int i = start + 1; i < valueChars.length; i++) {
            int temp = CommonUtils.charConvert(valueChars[i]);
            if (STOP_WORD_SET != null && STOP_WORD_SET.contains(temp)) {
                //过滤停顿字符
                continue;
            }
            node = node.querySub(temp);
            if (node == null) {
                // 没有了
                break;
            }
            sensitive.append((char) node.getValue());
            /*如果当前已是last标识节点，直接添加*/
            if (node.isLast()) {
                hitWordResults.add(ValidateWordNodeResult.builder()
                        .sensitive(sensitive.toString())
                        .wordContent(node.getWordContent()).build());
            }

            /*如果对应节点还存在子节点，则继续查找*/
            if (CollectionUtils.isEmpty(node.getSubNodes())) {
                break;
            }
        }

        if (CollectionUtils.isEmpty(hitWordResults)) {
            /*此时说明没有命中任何敏感词信息*/
            return null;
        }

        return getWordsByBusinessIdetity(hitWordResults, wmPoiId);
    }

    /**
     * 根据业务身份判断是否真实命中敏感词
     *
     * @param hitWordResults 敏感词列表
     * @return 对应业务身份真实命中敏感词集合
     */
    private List<ValidateResult> getWordsByBusinessIdetity(List<ValidateWordNodeResult> hitWordResults, Long wmPoiId) {
        List<ValidateResult> validateResultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(hitWordResults)) {
            return validateResultList;
        }

        hitWordResults.forEach(hitWordResult -> {
            /*根据以上命中敏感词，此时如果有业务身份需根据当前的业务身份判断是否命中敏感词*/
            String wordContent = hitWordResult.getWordContent();
            HitWordResultForBusiness hitWordResultForBusiness = businessIdentityService.hitWordResultForBusinessIdetity(wordContent);
            if (hitWordResultForBusiness.isHit()) {
                /*如果当前业务身份命中，则进行添加*/
                String words = hitWordResultForBusiness.getHitWord().getWords();
                List<String> sensitiveWord = Lists.newArrayList();
                sensitiveWord.add(words);
                SensitiveWordWithType sensitiveWordWithType = SensitiveWordWithType.builder()
                        .type(SensitiveWordTypeEnum.NOT_CONTAIN.getValue())
                        .sensitiveWord(sensitiveWord).build();
                SensitiveWordWithMuslim sensitiveWordWithMuslim = muslimDealService.getMuslimType(hitWordResultForBusiness.getHitWord(), wmPoiId);
                sensitiveWordWithMuslim.setSensitiveWord(words);
                validateResultList.add(new ValidateResult(null,
                        wordContent, hitWordResultForBusiness.getHitWord(), ValidateRule.KEYWORD,
                        sensitiveWordWithType, sensitiveWordWithMuslim, SensitiveWordWithQua.builder().sensitiveWord(words).build()));
            }
        });

        return validateResultList;
    }
}
