package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 验证正则表达式量词元字符
 * @author: zhangyanfeng
 * @create: 2023-11-05 12:34
 **/
public class QuantifierRegexExample {
    public static void main(String[] args) {
        // 使用正则表达式验证量词元字符

        // 创建匹配器并打印结果
        MatcherWithInfo("1. Matches for 'a*':", "aaaabb", "a*");
        MatcherWithInfo("2. Matches for 'b+':", "aaaabb", "b+");
        MatcherWithInfo("3. Matches for 'c?':", "aaaabb", "c?");
        MatcherWithInfo("4. Matches for 'd{3}':", "aaadddbb", "d{3}");
        MatcherWithInfo("5. Matches for 'e{2,}':", "eeeefbb", "e{2,}");
        MatcherWithInfo("6. Matches for 'f{1,3}':", "ffbbfffb", "f{1,3}");
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
