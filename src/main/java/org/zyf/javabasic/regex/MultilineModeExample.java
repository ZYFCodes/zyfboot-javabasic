package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用多行模式匹配包含多行注释的文本
 * @author: zhangyanfeng
 * @create: 2023-11-05 15:37
 **/
public class MultilineModeExample {
    public static void main(String[] args) {
        String text = "This is a sample text.\n" +
                "/* This is a multiline\n" +
                "   comment. */\n" +
                "More text.";

        // 启用多行模式，匹配多行注释内容
        Pattern pattern = Pattern.compile("/\\*.*?\\*/", Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String comment = matcher.group();
            System.out.println("Multiline Comment: " + comment);
        }
    }
}
