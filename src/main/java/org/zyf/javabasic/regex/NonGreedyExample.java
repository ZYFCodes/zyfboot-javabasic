package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用非贪婪模式提取链接
 * @author: zhangyanfeng
 * @create: 2023-11-05 14:25
 **/
public class NonGreedyExample {
    public static void main(String[] args) {
        String htmlText = "<a href=\"https://example.com\">Example 1</a> <a href=\"https://example2.com\">Example 2</a>";
        Pattern pattern = Pattern.compile("<a href=\"(.*?)\">(.*?)</a>");
        Matcher matcher = pattern.matcher(htmlText);

        while (matcher.find()) {
            String linkUrl = matcher.group(1);
            String linkText = matcher.group(2);
            System.out.println("URL: " + linkUrl);
            System.out.println("Link Text: " + linkText);
        }
    }
}
