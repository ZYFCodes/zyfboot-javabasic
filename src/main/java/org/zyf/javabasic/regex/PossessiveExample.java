package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用独占模式匹配重复字符
 * @author: zhangyanfeng
 * @create: 2023-11-05 14:29
 **/
public class PossessiveExample {
    public static void main(String[] args) {
        String text = "aaaabbbbbcccccddddd";
        Pattern pattern = Pattern.compile("(\\w+)++");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String group = matcher.group(1);
            System.out.println("Match: " + group);
        }
    }
}
