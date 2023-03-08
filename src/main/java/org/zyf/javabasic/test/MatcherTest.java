package org.zyf.javabasic.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/2/22  16:05
 */
public class MatcherTest {
    public static void main(String[] args) {
        String regular = "^/(?<org>[^/]+)/(?<app>[^/]+)/pattern";
        String example = "/org/app/pattern123";

        Pattern compile = Pattern.compile(regular);
        Matcher matcher = compile.matcher(example);
        boolean isMatch = matcher.find();

        System.out.println(isMatch);
    }
}
