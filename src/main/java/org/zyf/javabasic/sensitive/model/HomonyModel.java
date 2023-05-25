package org.zyf.javabasic.sensitive.model;

import com.github.houbb.pinyin.constant.enums.PinyinStyleEnum;
import com.github.houbb.pinyin.util.PinyinHelper;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.common.utils.CommonUtils;
import org.zyf.javabasic.common.utils.SensitiveConfigUtil;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.base.Base64;
import org.zyf.javabasic.sensitive.base.HitWordResultForBusiness;
import org.zyf.javabasic.sensitive.base.SensitiveWordTypeEnum;
import org.zyf.javabasic.sensitive.base.SensitiveWordWithType;
import org.zyf.javabasic.sensitive.base.ValidateResult;
import org.zyf.javabasic.sensitive.base.ValidateRule;
import org.zyf.javabasic.sensitive.base.ValidateWordNodeResult;
import org.zyf.javabasic.sensitive.base.WordDomain;
import org.zyf.javabasic.sensitive.model.dfa.FilterSet;
import org.zyf.javabasic.sensitive.model.dfa.WordNode;
import org.zyf.javabasic.sensitive.service.BusinessIdentityService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:47
 */
@Component
@Slf4j
public class HomonyModel implements ValidateModel {

    public static final String HOMONY_SEPARATOR = ",";
    /**
     * FIRST_CHAR_SET,FIRST_NODES_MAP在构建时是强一致性的，先使用volatile保证，后续如果需要保证超高并发下的一致性，可统一封装到校验器中
     * FIRST_CHAR_SET-存储首字；FIRST_NODES_MAP-存储节点
     */
    private static volatile FilterSet FIRST_CHAR_SET = new FilterSet();
    private static volatile Map<Integer, WordNode> FIRST_NODES_MAP = new HashMap<Integer, WordNode>(1024, 1);
    // 停顿词
    private static volatile Set<Integer> STOP_WORD_SET = new HashSet<>();


    @Resource
    private BusinessIdentityService businessIdentityService;

    /**
     * 同音词树基本构建
     *
     * @param words 同音词
     * @return 构建成功
     */
    @Override
    public boolean init(List<WordDomain> words) {
        if (CollectionUtils.isEmpty(words)) {
            log.warn("[sensitive keyword model init failure] words is empty");
            return false;
        }
        /*1.更新同音词 dfa 树*/
        addHomonyWord(words);
        /*2.支持过滤停顿符，目前暂时没有*/
        List<String> stopWords = SensitiveConfigUtil.getStopWords();
        addStopWord(stopWords);
        return true;
    }

    /**
     * 同音词匹配校验结果
     *
     * @param content 原数据内容
     * @param wmPoiId 门店id信息
     * @return 校验结果
     */
    @Override
    public List<ValidateResult> validate(String content, Long wmPoiId) {
        List<ValidateResult> sensitives = Lists.newArrayList();
        if (StringUtils.isBlank(content)) {
            log.warn("content is blank ...");
            return sensitives;
        }

        if (!(FIRST_CHAR_SET != null && FIRST_NODES_MAP != null)) {
            return sensitives;
        }

//        DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_MODEL_VALIDATE_CHART, DiggerConstants.HOMONY_SENSITIVE_MODEL_VALIDATE_LINE);

        /*清洗文本将相关停顿词进行剔除*/
        StringBuilder valueValidChars = new StringBuilder();
        char[] contentChars = content.toCharArray();
        for (int i = 0; i < contentChars.length; i++) {
            int temp = CommonUtils.charConvert(contentChars[i]);
            if (STOP_WORD_SET != null && STOP_WORD_SET.contains(temp)) {
                /*过滤停顿字符*/
                continue;
            }
            valueValidChars.append(contentChars[i]);
        }

        /*将剔除掉停顿词的相关内容进行拼音转换*/
        String valueStr = PinyinHelper.toPinyin(valueValidChars.toString(), PinyinStyleEnum.NORMAL);
        /*同音词树校验开始*/
        char[] valueChars = valueStr.toCharArray();
        int v;
        for (int i = 0; i < valueChars.length; i++) {
            /*当前检查的字符*/
            v = CommonUtils.charConvert(valueChars[i]);
            //1.快速找当前文本中第一个包含同音词字符的位置
            if (!FIRST_CHAR_SET.contains(v)) {
                continue;
            }
            //2.从当前位置进行深度计算，获取深度最长的(每个首字对应命中同音词可能是多个)
            List<ValidateResult> validateResults = getHomonyWordList(i, valueChars, valueValidChars.toString());
            if (CollectionUtils.isEmpty(validateResults)) {
                continue;
            }
            sensitives.addAll(validateResults);
        }

        if (CollectionUtils.isNotEmpty(sensitives)) {
//            DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_MODEL_VALIDATE_HIT_CHART, DiggerConstants.HIT_HOMONY_SENSITIVE_MODEL_LINE);
        }
        return sensitives;
    }

    /**
     * 获取实际同音词集合信息
     *
     * @param start      找到的第一个字符的信息内容
     * @param valueChars 对应待校验的内容
     * @return 实际命中同音词信息
     */
    private List<ValidateResult> getHomonyWordList(int start, char[] valueChars, String contentValid) {
        int v = CommonUtils.charConvert(valueChars[start]);
        /*实际每个根节点实际命中同音词可能是多个*/
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
            /*此时说明没有命中任何同音词信息*/
            return null;
        }

        return getHomonyWordsByBusinessIdetity(hitWordResults, contentValid);
    }

    /**
     * 根据业务身份判断是否真实命中同音词
     *
     * @param hitWordResults 同音词列表
     * @return 对应业务身份真实命中同音词集合
     */
    private List<ValidateResult> getHomonyWordsByBusinessIdetity(List<ValidateWordNodeResult> hitWordResults, String contentValid) {
        List<ValidateResult> validateResultList = Lists.newArrayList();
        if (CollectionUtils.isEmpty(hitWordResults)) {
            return validateResultList;
        }

        for (ValidateWordNodeResult hitWordResult : hitWordResults) {
            /*根据以上命中同音词，此时如果有业务身份需根据当前的业务身份判断是否命中同音词*/
            String wordContent = hitWordResult.getWordContent();
            HitWordResultForBusiness hitWordResultForBusiness = businessIdentityService.hitWordResultForBusinessIdetity(wordContent);
            if (!hitWordResultForBusiness.isHit()) {
                continue;
            }
            /*如果当前业务身份命中，则进行添加*/
            String[] words = hitWordResultForBusiness.getHitWord().getWords().split(HOMONY_SEPARATOR);
            /*线上词进行分析处理提炼对应的同音词内容*/
            String wordstr = Base64.decodeToString(words[0]);
            /*将解码后的同音词内容进行拼音转换*/
            String homonyWord = PinyinHelper.toPinyin(wordstr, PinyinStyleEnum.NORMAL);
            /*如果存在相关"跳过校验同音词"内容，需要进行进一步的判断来决定是否命中对应的同音词内容*/
            if (words.length == 2) {
                String homonyIgnoreWord = Base64.decodeToString(words[1]);
                List<String> homonyIgnoreWordList = Splitter.on("|").omitEmptyStrings().splitToList(homonyIgnoreWord);
                if (StringUtils.containsAny(contentValid, homonyIgnoreWordList.toArray(new String[]{}))) {
                    continue;
                }
            }
            /*进一步优化分析：如果文本中是多余包含词的情况 例如只有象xiang没有先xian，但是我们的同音词是先xian，当前也不该被命中*/
            if (!isIgnore(homonyWord, contentValid)) {
                continue;
            }

            List<String> sensitiveWord = Lists.newArrayList();
            sensitiveWord.add(homonyWord + "的同音词");
            SensitiveWordWithType sensitiveWordWithType = SensitiveWordWithType.builder().build();
            sensitiveWordWithType.setType(SensitiveWordTypeEnum.NOT_CONTAIN.getValue());
            sensitiveWordWithType.setSensitiveWord(sensitiveWord);
            validateResultList.add(new ValidateResult(null,
                    homonyWord + "的同音词", hitWordResultForBusiness.getHitWord(), ValidateRule.HOMONY,
                    sensitiveWordWithType, null, null));
        }

        return validateResultList;
    }

    /**
     * @param homonyWord   同音词拼音文本信息
     * @param contentValid 用户文本有效字段信息
     * @return false-需要被忽略不该命中；
     */
    private boolean isIgnore(String homonyWord, String contentValid) {

        /*有效文本转换成对应的拼音*/
        String contentValidWords = PinyinHelper.toPinyin(contentValid, PinyinStyleEnum.NORMAL);

        /*如果就是词本身则一定是命中的*/
        if (contentValidWords.length() == homonyWord.length() && homonyWord.equals(contentValidWords)) {
            return true;
        }

        /*转变成对应的拼音链表*/
        List<String> contentValidPys = Splitter.on(" ").omitEmptyStrings().splitToList(contentValidWords);
        if (CollectionUtils.isEmpty(contentValidPys)) {
            return false;
        }

        /*如果就是本身的拼音则不应该被忽略*/
        if (contentValidPys.size() == 1) {
            /*有效词就是单子的话，如果长度大于同音词，则发音不通，不该被命中*/
            if (contentValidPys.get(0).length() > homonyWord.length()) {
                return false;
            }
            if (homonyWord.equals(contentValidPys.get(0))) {
                return true;
            }
        }

        /*先进行有效词内部分析：如果对应拼音链表中有对应的同音词且大小一致则一定是命中的，不应该被忽略*/
        boolean innerHit = innerHit(contentValidPys, homonyWord);
        if (innerHit) {
            return true;
        }

        /*在进行有效词外部分析： 同音词-xi jin ping  有效词：shi xi jin ping 在进行有效词内部分析后直接包含的必然是命中同音词的*/
        List<String> homonyWordPys = Splitter.on(" ").omitEmptyStrings().splitToList(homonyWord);
        if (contentValidWords.contains(homonyWord) && homonyWordPys.size() != 1) {
            return true;
        }

        return false;
    }


    private boolean innerHit(List<String> contentValidPys, String homonyWord) {
        /*内部单文本homonyWord*/
        for (String py : contentValidPys) {
            /*同音词：xian 有效本文：hen xiang 应该返回false不该被命中  hen xian a应该被命中 即文本内部分开完全和指定的相同则才是命中的*/
            if (py.contains(homonyWord) && py.length() == homonyWord.length()) {
                return true;
            }
        }

        /*内部没有完全命中*/
        return false;
    }


    /**
     * 添加同音词DFA节点
     *
     * @param words
     */
    private static void addHomonyWord(final List<WordDomain> words) {
        FilterSet firstChars = new FilterSet();
        Map<Integer, WordNode> firstNodesMap = new HashMap<Integer, WordNode>(1024, 1);

        if (CollectionUtils.isEmpty(words)) {
            return;
        }
        char[] homonyWordChars;
        int fchar;
        int lastIndex;
        // 首字母节点
        WordNode fnode;
        for (WordDomain word : words) {
            /*线上存储可能是多个词的组合形式*/
            String sw = word.getWords();
            if (StringUtils.isEmpty(sw)) {
                continue;
            }
            /*线上词进行分析处理提炼对应的同音词内容*/
            String wordstr = Base64.decodeToString(word.getWords().split(HOMONY_SEPARATOR)[0]);
            if (StringUtils.isBlank(wordstr)) {
                continue;
            }
            /*将解码后的同音词内容进行拼音转换*/
            String homonyWord = PinyinHelper.toPinyin(wordstr, PinyinStyleEnum.NORMAL);

            /*构建相关基本信息*/
            homonyWordChars = homonyWord.toCharArray();
            //全角转半角
            fchar = CommonUtils.charConvert(homonyWordChars[0]);
            // 没有首字定义
            if (!firstChars.contains(fchar)) {
                //首字标志位 可重复add
                firstChars.add(fchar);
                boolean isLast = homonyWordChars.length == 1;
                String wordContent = isLast ? word.getWords() : null;
                fnode = new WordNode(fchar, isLast, wordContent);
                firstNodesMap.put(fchar, fnode);
            } else {
                fnode = firstNodesMap.get(fchar);
                if (!fnode.isLast() && homonyWordChars.length == 1) {
                    fnode.setLast(true);
                    fnode.setWordContent(word.getWords());
                }
            }
            lastIndex = homonyWordChars.length - 1;
            for (int i = 1; i < homonyWordChars.length; i++) {
                fnode = fnode.addIfNoExist(CommonUtils.charConvert(homonyWordChars[i]), i == lastIndex, word.getWords());
            }
        }
        FIRST_CHAR_SET = firstChars;
        FIRST_NODES_MAP = firstNodesMap;
    }

    /**
     * 增加停顿词
     *
     * @param words
     */
    private static void addStopWord(final List<String> words) {
        Set<Integer> stopWords = new HashSet<>();
        if (!CollectionUtils.isEmpty(words)) {
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
}

