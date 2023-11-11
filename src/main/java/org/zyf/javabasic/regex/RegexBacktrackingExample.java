package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 演示如何使用修改后的正则表达式来匹配嵌套括号表达式
 * @author: zhangyanfeng
 * @create: 2023-11-05 14:37
 **/
public class RegexBacktrackingExample {
    public static void main(String[] args) {
        String text = "((a + b) * (c - d)) + (e * (f / g))";
        Pattern pattern = Pattern.compile("\\((?:[^()]+|(?R))*\\)");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String matchedExpression = matcher.group();
            System.out.println("Match: " + matchedExpression);
        }
    }
}
