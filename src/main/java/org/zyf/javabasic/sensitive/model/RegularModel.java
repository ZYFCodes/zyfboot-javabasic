package org.zyf.javabasic.sensitive.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.base.Base64;
import org.zyf.javabasic.sensitive.base.*;
import org.zyf.javabasic.sensitive.service.BusinessIdentityService;
import org.zyf.javabasic.sensitive.service.MuslimDealService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:44
 */
@Component
@Slf4j
public class RegularModel implements ValidateModel {

    @Resource
    private BusinessIdentityService businessIdentityService;
    @Autowired
    private MuslimDealService muslimDealService;

    public static final String CHAR_PATTERN = "[\\s\\d\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥× ]";
    public static final String REGEX_SEPARATOR = ",";
    private static volatile Map<WordDomain, Pattern[]> WORD_TO_PATTERN = Maps.newHashMap();

    @Override
    public boolean init(List<WordDomain> words) {
        if (CollectionUtils.isEmpty(words)) {
            log.warn("[sensitive regular model init failure] words is empty");
            return false;
        }
        //1.重新构建
        Map<WordDomain, Pattern[]> wordToPattern = Maps.newHashMap();
        String[] wordstr;
        Pattern p1;
        Pattern p2;
        for (WordDomain wordDomain : words) {
            try {
                switch (SensitiveValidateTypeEnum.getByCode(wordDomain.getType())) {
                    case SIAMPLE_REGULAR:
                        Pattern p = Pattern.compile(Base64.decodeToString(wordDomain.getWords()), Pattern.CASE_INSENSITIVE);
                        wordToPattern.put(wordDomain, new Pattern[]{p});
                        break;
                    case AND_REGULAR:
                    case N_AND_REGULAR:
                        wordstr = wordDomain.getWords().split(REGEX_SEPARATOR);
                        if (2 != wordstr.length) {
                            log.warn("[sensitive regular model words is valid] wordId={},wordstr={}", wordDomain.getId(), wordDomain.getWords());
                            continue;
                        }
                        p1 = Pattern.compile(Base64.decodeToString(wordstr[0]), Pattern.CASE_INSENSITIVE);
                        p2 = Pattern.compile(Base64.decodeToString(wordstr[1]), Pattern.CASE_INSENSITIVE);
                        wordToPattern.put(wordDomain, new Pattern[]{p1, p2});
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                log.error("init Exception, wordDomain : {}, e", wordDomain, e);
            }
        }
        WORD_TO_PATTERN = wordToPattern;
        return true;
    }

    /**
     *
     */
    @Override
    public List<ValidateResult> validate(String content, Long wmPoiId) {
        List<ValidateResult> result = Lists.newArrayList();

        if (StringUtils.isEmpty(content)) {
            log.warn("content is empty");
            return result;
        }

//        DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_MODEL_VALIDATE_CHART, DiggerConstants.REGULAR_SENSITIVE_MODEL_VALIDATE_LINE);

        //保留现有接口逻辑，先进行特殊字符过滤
        content = content.replaceAll(CHAR_PATTERN, "");
        boolean illegal = false;
        Matcher matcher1 = null;
        Matcher matcher2 = null;
        for (Map.Entry<WordDomain, Pattern[]> e : WORD_TO_PATTERN.entrySet()) {
            switch (SensitiveValidateTypeEnum.getByCode(e.getKey().getType())) {
                case SIAMPLE_REGULAR:
                    Matcher matcher = e.getValue()[0].matcher(content);
                    illegal = matcher.find();
                    /*已经命中正则的前提下，需要同时满足业务身份的要求*/
                    if (illegal && businessIdentityService.isHitWordForBusinessIdetity(e.getKey().getWords())) {
                        result.add(getValidateResult(e.getKey(), content, matcher, null, wmPoiId));
                    }
                    break;
                case AND_REGULAR:
                    matcher1 = e.getValue()[0].matcher(content);
                    matcher2 = e.getValue()[1].matcher(content);
                    illegal = matcher1.find() && matcher2.find();
                    /*已经命中正则的前提下，需要同时满足业务身份的要求*/
                    if (illegal && businessIdentityService.isHitWordForBusinessIdetity(e.getKey().getWords())) {
                        result.add(getValidateResult(e.getKey(), content, matcher1, matcher2, wmPoiId));
                    }
                    break;
                case N_AND_REGULAR:
                    matcher1 = e.getValue()[0].matcher(content);
                    matcher2 = e.getValue()[1].matcher(content);
                    illegal = matcher1.find() && !matcher2.find();
                    /*已经命中正则的前提下，需要同时满足业务身份的要求*/
                    if (illegal && businessIdentityService.isHitWordForBusinessIdetity(e.getKey().getWords())) {
                        result.add(getValidateResult(e.getKey(), content, matcher1, null, wmPoiId));
                    }
                    break;
                default:
                    break;
            }

        }
        if (CollectionUtils.isNotEmpty(result)) {
//            DiggerUtil.diggerLog(DiggerConstants.SENSITIVE_MODEL_VALIDATE_HIT_CHART, DiggerConstants.HIT_REGULAR_SENSITIVE_MODEL_LINE);
        }
        return result;
    }

    public Pattern[] getPattern(WordDomain word) {
        return WORD_TO_PATTERN.get(word);
    }

    /**
     * 整合校验结果信息
     *
     * @param wordDomain 敏感词内容
     * @param content    文本内容
     * @param matcher1   正则匹配1
     * @param matcher2   正则匹配2
     * @return 校验结果信息
     */
    private ValidateResult getValidateResult(WordDomain wordDomain, String content, Matcher matcher1, Matcher matcher2, Long wmPoiId) {
        List<String> sensitiveWord = Lists.newArrayList();
        sensitiveWord.add(matcher1.group());

        SensitiveWordWithType sensitiveWordWithType = SensitiveWordWithType.builder().build();
        if (Objects.nonNull(matcher2)) {
            sensitiveWord.add(matcher2.group());
            sensitiveWordWithType.setType(SensitiveWordTypeEnum.NOT_CONTAIN_BOTH.getValue());
            sensitiveWordWithType.setSensitiveWord(sensitiveWord);
        } else {
            sensitiveWordWithType.setType(SensitiveWordTypeEnum.NOT_CONTAIN.getValue());
            sensitiveWordWithType.setSensitiveWord(sensitiveWord);
        }

        SensitiveWordWithMuslim sensitiveWordWithMuslim = muslimDealService.getMuslimType(wordDomain, wmPoiId);
        /*69741545清真乱码问题解决*/
        sensitiveWordWithMuslim.setSensitiveWord(matcher1.group());
        return new ValidateResult(content, matcher1.group(), wordDomain, ValidateRule.REGULAR, sensitiveWordWithType,
                sensitiveWordWithMuslim, SensitiveWordWithQua.builder().sensitiveWord(matcher1.group()).build());
    }
}

