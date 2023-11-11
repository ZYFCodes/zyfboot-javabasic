package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用环视（Lookaround）来验证文本中的条件
 * @author: zhangyanfeng
 * @create: 2023-11-05 16:38
 **/
public class LookaroundExample {
    public static void main(String[] args) {
        // 文本示例
        String text = "The quick brown fox jumps over the lazy dog.";

        // 正向预查示例：匹配包含 "fox" 后面是 "jumps" 的情况
        Pattern pattern1 = Pattern.compile("fox(?= jumps)");
        Matcher matcher1 = pattern1.matcher(text);

        while (matcher1.find()) {
            System.out.println("Positive Lookahead: " + matcher1.group());
        }

        // 负向预查示例：匹配包含 "fox" 后面不是 "lazy" 的情况
        Pattern pattern2 = Pattern.compile("fox(?! lazy)");
        Matcher matcher2 = pattern2.matcher(text);

        while (matcher2.find()) {
            System.out.println("Negative Lookahead: " + matcher2.group());
        }

        // 正向回顾示例：匹配包含 "fox" 前面是 "quick" 的情况
        text = "The quick fox jumps over the lazy dog.";
        Pattern pattern3 = Pattern.compile("(?<=quick )fox");
        Matcher matcher3 = pattern3.matcher(text);

        while (matcher3.find()) {
            System.out.println("Positive Lookbehind: " + matcher3.group());
        }

        // 负向回顾示例：匹配包含 "fox" 前面不是 "brown" 的情况
        Pattern pattern4 = Pattern.compile("(?<!brown )fox");
        Matcher matcher4 = pattern4.matcher(text);

        while (matcher4.find()) {
            System.out.println("Negative Lookbehind: " + matcher4.group());
        }
    }
}
