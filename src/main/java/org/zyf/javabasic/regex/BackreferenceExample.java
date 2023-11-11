package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 找到文本中连续重复的单词
 * @author: zhangyanfeng
 * @create: 2023-11-05 15:13
 **/
public class BackreferenceExample {
    public static void main(String[] args) {
        String text = "This is an example of repeated words: apple apple, cat cat cat, and dog dog dog dog dog.";

        // 定义匹配连续重复单词的正则表达式模式
        String pattern = "\\b(\\w+)(?: \\1)+\\b";

        // 编译正则表达式模式
        Pattern regex = Pattern.compile(pattern);

        // 创建匹配器对象
        Matcher matcher = regex.matcher(text);

        // 查找匹配的连续重复单词
        while (matcher.find()) {
            String repeatedWords = matcher.group();
            System.out.println("Found repeated words: " + repeatedWords);
        }
    }
}
