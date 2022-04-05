package org.zyf.javabasic.test;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/9/15  17:27
 */
public class ValidateResultListTest {
    public static void main(String[] args) {
        ContainsSensitiveWordResult result = new ContainsSensitiveWordResult();

        List<ValidateResult> checkResults = Lists.newArrayList();
        ValidateResult validateResult1 = new ValidateResult("1", "1", new WordDomain(), ValidateRule.BAOSHIJIE);
        ValidateResult validateResult2 = new ValidateResult("2", "2", new WordDomain(), ValidateRule.BAOSHIJIE);
        ValidateResult validateResult3 = new ValidateResult("3", "3", new WordDomain(), ValidateRule.BAOSHIJIE);
        checkResults.add(validateResult1);
        checkResults.add(validateResult2);
        checkResults.add(validateResult3);
        List<ValidateResult> checkResultsNew = Lists.newArrayList(checkResults);

        Set<String> sensitiveWordSet = Sets.newHashSet();
        sensitiveWordSet.add("s");
        result.setSensitiveWordSet(sensitiveWordSet);

        System.out.println(checkResults);
        System.out.println(checkResultsNew);
        System.out.println(result);

        Set<String> sensitiveWordInWhiteListSet = Sets.newHashSet();
        for (ValidateResult validateResult : checkResultsNew) {
            if (validateResult.getSource().equals("1")) {
                checkResults.remove(validateResult);
                sensitiveWordInWhiteListSet.add(validateResult.getSensitive());
            }
        }
        result.setSensitiveWordInWhiteListSet(sensitiveWordInWhiteListSet);
        System.out.println(checkResults);
        System.out.println(checkResultsNew);
        System.out.println(result);

        Set<String> sensitiveWordInWhiteListSetTest = Sets.newHashSet();
        sensitiveWordInWhiteListSetTest.add("w");
        sensitiveWordInWhiteListSetTest.add("o");
        System.out.println(sensitiveWordInWhiteListSetTest);


    }

    public static class ValidateResult {
        /**
         * 原始字符串，
         * 全词匹配：校验时可能会对特殊字符进行过，所有source和sensitive存在不一样的情况
         */
        private String source;

        /**
         * 命中的敏感词
         */
        private String sensitive;

        /**
         * 敏感词
         */
        private WordDomain wordDomain;

        /**
         * 当前匹配的类型
         */
        private ValidateRule rule;

        public ValidateResult(String source, String sensitive, WordDomain word, ValidateRule rule) {
            this.source = source;
            this.sensitive = sensitive;
            this.wordDomain = word;
            this.rule = rule;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSensitive() {
            return sensitive;
        }

        public void setSensitive(String sensitive) {
            this.sensitive = sensitive;
        }

        public ValidateRule getRule() {
            return rule;
        }

        public void setRule(ValidateRule rule) {
            this.rule = rule;
        }

        public WordDomain getWordDomain() {
            return wordDomain;
        }

        public void setWordDomain(WordDomain wordDomain) {
            this.wordDomain = wordDomain;
        }
    }

    public static enum ValidateRule {
        /**
         * 当敏感词开启泛化时，nlp会走泛化逻辑
         */
        NLP(1),

        /**
         * 当敏感词未开启泛化时，NLP中会当做去次匹配来处理，code用于日志记录，与nlp保持一致
         */
        NLP_KEYWORD(1),

        /**
         * 关键字
         */
        KEYWORD(2),
        /**
         * 正则
         */
        REGULAR(3),

        /**
         * 过滤后的保时洁结果
         */
        BAOSHIJIE_WAIMAI(4),

        /**
         * 保时洁返回的所有结果
         */
        BAOSHIJIE(5);

        private int code;

        ValidateRule(int code) {
            this.code = code;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public static ValidateRule getRule(String ruleName) {
            for (ValidateRule validateRule : ValidateRule.values()) {
                if (validateRule.name().equals(ruleName)) {
                    return validateRule;
                }
            }
            return null;
        }

    }

    public static class WordDomain {

    }

    @Data
    public static class ContainsSensitiveWordResult {
        private boolean illegal;
        private String content;
        private Set<String> sensitiveWordSet = Sets.newHashSet();
        private Set<String> sensitiveWordInWhiteListSet = Sets.newHashSet();
    }


}
