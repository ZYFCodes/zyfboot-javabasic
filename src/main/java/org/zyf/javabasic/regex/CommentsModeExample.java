package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用注释模式添加注释来解释正则表达式的含义
 * @author: zhangyanfeng
 * @create: 2023-11-05 15:41
 **/
public class CommentsModeExample {
    public static void main(String[] args) {
        String text = "Hello, John! My name is ZYF";

        // 启用注释模式，匹配问候语
        Pattern pattern = Pattern.compile(
                "(?x)        # 启用注释模式\n" +
                        "Hello,      # 匹配 Hello\n" +
                        "\\s         # 匹配一个空白字符\n" +
                        "John        # 匹配 John"
        );
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println("Matched: " + matcher.group());
        } else {
            System.out.println("No match");
        }
    }
}
