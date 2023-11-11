package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 重复分组
 * @author: zhangyanfeng
 * @create: 2023-11-05 15:19
 **/
public class RepeatedGroupExample {
    public static void main(String[] args) {
        String text = "rfABCDGHabcabcabcJMMMKJBGHJNhgjjhkj";

        // 定义匹配重复的子表达式的正则表达式模式
        String pattern = "(abc){3}";

        // 编译正则表达式模式
        Pattern regex = Pattern.compile(pattern);

        // 创建匹配器对象
        Matcher matcher = regex.matcher(text);

        // 查找匹配的文本
        while (matcher.find()) {
            String matchedText = matcher.group();
            System.out.println("Found: " + matchedText);
        }
    }
}
