package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用行的开始和行的结束断言
 * @author: zhangyanfeng
 * @create: 2023-11-05 16:28
 **/
public class LineBoundaryExample {
    public static void main(String[] args) {
        // 文本示例
        String text = "apple\nbanana\ncherry\norange";

        // 使用^断言匹配行的开始
        Pattern pattern1 = Pattern.compile("^apple", Pattern.MULTILINE);
        Matcher matcher1 = pattern1.matcher(text);

        while (matcher1.find()) {
            System.out.println("Match at line start: " + matcher1.group());
        }

        // 使用$断言匹配行的结束
        Pattern pattern2 = Pattern.compile("cherry$", Pattern.MULTILINE);
        Matcher matcher2 = pattern2.matcher(text);

        while (matcher2.find()) {
            System.out.println("Match at line end: " + matcher2.group());
        }
    }
}
