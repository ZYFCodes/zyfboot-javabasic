package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用点号通配模式匹配跨越多行的 HTML 标签内容
 * @author: zhangyanfeng
 * @create: 2023-11-05 15:30
 **/
public class DotAllModeExample {
    public static void main(String[] args) {
        String html = "<div>\nThis is some text inside a div.\n</div>";

        // 启用点号通配模式，匹配 div 标签内容
        Pattern pattern = Pattern.compile("<div>(.*?)</div>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);

        if (matcher.find()) {
            String divContent = matcher.group(1);
            System.out.println("Div content: " + divContent);
        } else {
            System.out.println("No match");
        }
    }
}
