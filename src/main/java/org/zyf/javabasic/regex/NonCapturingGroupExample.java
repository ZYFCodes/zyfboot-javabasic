package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用非捕获分组
 * @author: zhangyanfeng
 * @create: 2023-11-05 15:01
 **/
public class NonCapturingGroupExample {
    public static void main(String[] args) {
        String text = "Contact us at support@example.com or info@company.com";

        // 定义匹配电子邮件地址域名的正则表达式模式
        String pattern = "(?<=@)(?:[a-zA-Z0-9.-]+\\.)+[a-zA-Z]{2,4}";

        // 编译正则表达式模式
        Pattern regex = Pattern.compile(pattern);

        // 创建匹配器对象
        Matcher matcher = regex.matcher(text);

        // 查找匹配的电子邮件地址域名
        while (matcher.find()) {
            String domainName = matcher.group();
            System.out.println("Found domain: " + domainName);
        }
    }
}
