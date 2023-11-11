package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 验证和打印正则表达式空白符元字符
 * @author: zhangyanfeng
 * @create: 2023-11-05 12:21
 **/
public class WhitespaceRegexExample {
    public static void main(String[] args) {
        // 使用正则表达式验证空白符元字符

        // 创建匹配器并打印结果
        MatcherWithInfo("1. Matches for '\\s':", "This is some text with spaces and tabs.", "\\s");
        MatcherWithInfo("2. Matches for '\\t':", "Tab\tSeparated\tText", "\\t");
        MatcherWithInfo("3. Matches for '\\n':", "Newline\nSeparated\nText", "\\n");
        MatcherWithInfo("4. Matches for '\\r':", "Carriage\rReturn", "\\r");
        MatcherWithInfo("5. Matches for '\\f':", "Form\fFeed", "\\f");
        MatcherWithInfo("6. Matches for '\\v':", "Vertical\\vTab", "\\v");
        MatcherWithInfo("7. Matches for '\\h':", "Horizontal hWhitespace hExample", "\\h");
        MatcherWithInfo("8. Matches for '\\v':", "Vertical vWhitespace vExample", "\\v");
        MatcherWithInfo("9. Matches for '\\S':", "This_is_non-space_text", "\\S");
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
