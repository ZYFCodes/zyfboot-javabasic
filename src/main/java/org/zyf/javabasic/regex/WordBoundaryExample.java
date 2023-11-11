package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用单词边界断言来验证和匹配文本
 * @author: zhangyanfeng
 * @create: 2023-11-05 16:16
 **/
public class WordBoundaryExample {
    public static void main(String[] args) {
        // 文本示例
        String text = "The quick brown fox jumps over the lazy dog. ";

        // 使用\b断言匹配整个单词 "fox"
        Pattern pattern1 = Pattern.compile("\\bfox\\b");
        Matcher matcher1 = pattern1.matcher(text);

        while (matcher1.find()) {
            System.out.println("Match: " + matcher1.group());
        }

        // 使用\B断言匹配非单词边界的 "fox"
        Pattern pattern2 = Pattern.compile("\\Bfox\\B");
        Matcher matcher2 = pattern2.matcher(text);

        while (matcher2.find()) {
            System.out.println("Non-Match: " + matcher2.group());
        }
    }
}
