package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 验证正则表达式中的范围元字符和备选项
 * @author: zhangyanfeng
 * @create: 2023-11-05 13:39
 **/
public class RangeRegexExample {
    public static void main(String[] args) {
        // 使用正则表达式验证范围元字符和备选项

        // 创建匹配器并打印结果
        MatcherWithInfo("1. Matches for '[aeiou]':", "Hello, world!", "[aeiou]");
        MatcherWithInfo("2. Matches for '[a-z]':", "The quick brown fox", "[a-z]");
        MatcherWithInfo("3. Matches for '[0-9]':", "12345 and 67890", "[0-9]");
        MatcherWithInfo("4. Matches for 'apple|banana|cherry':", "I like cherry pie.", "apple|banana|cherry");
        MatcherWithInfo("5. Matches for '[^aeiou]':", "This is a test.", "[^aeiou]");
    }

    // 匹配器并打印结果
    private static void MatcherWithInfo(String header, String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        StringBuilder matches = new StringBuilder();

        while (matcher.find()) {
            matches.append(matcher.group()).append(" ");
        }

        System.out.println(header);
        System.out.println("   Original Text: " + text);
        System.out.println("   Matching Result: " + (matches.length() > 0 ? matches.toString().trim() : "No Match"));
        System.out.println();
    }
}
