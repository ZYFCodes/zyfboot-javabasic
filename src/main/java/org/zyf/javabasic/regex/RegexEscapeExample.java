package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 验证正则表达式中的转义字符
 * @author: zhangyanfeng
 * @create: 2023-11-05 16:48
 **/
public class RegexEscapeExample {
    public static void main(String[] args) {
        // 要匹配的文本
        String text = "This is a dot (.) and a question mark (?).";

        // 使用正则表达式匹配点号和问号，需要转义这两个字符
        String regex = "\\.";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        System.out.println("Matching dots:");
        while (matcher.find()) {
            System.out.println("Match: " + matcher.group());
        }

        // 使用正则表达式匹配问号
        regex = "\\?";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(text);

        System.out.println("\nMatching question marks:");
        while (matcher.find()) {
            System.out.println("Match: " + matcher.group());
        }
    }
}
