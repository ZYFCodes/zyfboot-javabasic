package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 特殊单字符元字符验证
 * @author: zhangyanfeng
 * @create: 2023-11-05 00:04
 **/
public class SpecialSingleCharRegexExample {
    public static void main(String[] args) {
        // 使用正则表达式验证特殊单字符元字符

        // 创建匹配器
        MatcherWithInfo("1. Matches for 'c.t'", "cat cut c@t", "c.t");
        MatcherWithInfo("2. Matches for '\\d'", "The answer is 42", "\\d");
        MatcherWithInfo("3. Matches for '\\w'", "word_123", "\\w");
        MatcherWithInfo("4. Matches for '\\s'", "Hello\tworld\n", "\\s");

        // 反义元字符

        MatcherWithInfo("5. Matches for '\\D'", "Hello, World!", "\\D");
        MatcherWithInfo("6. Matches for '\\W'", "text-with-hyphen", "\\W");
        MatcherWithInfo("7. Matches for '\\S'", "This is text", "\\S");
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
