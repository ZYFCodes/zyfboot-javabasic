package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用不区分大小写模式匹配用户名
 * @author: zhangyanfeng
 * @create: 2023-11-05 15:25
 **/
public class CaseInsensitiveExample {
    public static void main(String[] args) {
        String text = "Welcome, uSer123!";

        // 启用不区分大小写模式，匹配用户名
        Pattern pattern = Pattern.compile("(?i)user123");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println("Matched: " + matcher.group());
        } else {
            System.out.println("No match");
        }
    }
}
