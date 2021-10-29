package org.zyf.javabasic.test;

import lombok.Data;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.OptionalInt;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/10/12  16:05
 */
public class TestMaxStringInList {

    public static void main(String[] args) {
        List<ValidateResult> sensitiveWordList = Lists.newArrayList();
        for (int i = 0; i < 11; i++) {
            ValidateResult validateResult=new ValidateResult();
            validateResult.setSource("nihao"+i);
            sensitiveWordList.add(validateResult);
        }
        System.out.println(sensitiveWordList);
        OptionalInt s = sensitiveWordList.stream().map(ValidateResult::getSource).mapToInt(String::length).max();
        System.out.println(s.getAsInt());
    }

    @Data
    static class ValidateResult {
        /**
         * 原始字符串，
         * 全词匹配：校验时可能会对特殊字符进行过，所有source和sensitive存在不一样的情况
         */
        private String source;

        /**
         * 命中的敏感词
         */
        private String sensitive;

    }

}
