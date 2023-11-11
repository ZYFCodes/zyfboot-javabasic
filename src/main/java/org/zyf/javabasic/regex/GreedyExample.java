package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用贪婪模式的实际案例分析
 * @author: zhangyanfeng
 * @create: 2023-11-05 14:14
 **/
public class GreedyExample {
    public static void main(String[] args) {
        // 案例 1: 提取段落文本
        String paragraphText = "<p>段落 1</p> <p>段落 2</p> <p>段落 3</p>";
        Pattern paragraphPattern = Pattern.compile("<p>(.*?)</p>");
        Matcher paragraphMatcher = paragraphPattern.matcher(paragraphText);

        System.out.println("案例 1: 提取段落文本\n文本: " + paragraphText);

        int paragraphNumber = 1;
        while (paragraphMatcher.find()) {
            String paragraph = paragraphMatcher.group(1);
            System.out.println("段落 " + paragraphNumber + ": " + paragraph);
            paragraphNumber++;
        }

        // 案例 2: 提取注释内容
        String code = "/* 这是一个注释 */ int x = 10; /* 另一个注释 */";
        Pattern commentPattern = Pattern.compile("/\\*(.*?)\\*/");
        Matcher commentMatcher = commentPattern.matcher(code);

        System.out.println("\n案例 2: 提取注释内容\n代码: " + code);

        while (commentMatcher.find()) {
            String comment = commentMatcher.group(1);
            System.out.println("注释: " + comment);
        }

        // 案例 3: 匹配 HTML 标签
        String htmlText = "<div class=\"container\">这是一个 <span>示例</span> HTML 文档。</div>";
        Pattern htmlPattern = Pattern.compile("<(.*?)>");
        Matcher htmlMatcher = htmlPattern.matcher(htmlText);

        System.out.println("\n案例 3: 匹配 HTML 标签\nHTML 文本: " + htmlText);

        while (htmlMatcher.find()) {
            String htmlTag = htmlMatcher.group(1);
            System.out.println("HTML 标签: " + htmlTag);
        }
    }
}
