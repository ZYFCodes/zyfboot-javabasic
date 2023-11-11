package org.zyf.javabasic.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 使用命名捕获分组分别捕获月、日和年
 * @author: zhangyanfeng
 * @create: 2023-11-05 15:08
 **/
public class NamedCapturingGroupExample {
    public static void main(String[] args) {
        String text = "Today's date is 12/25/2022, and tomorrow is 01/01/2023.";

        // 定义匹配日期的正则表达式模式，使用命名捕获分组
        String pattern = "(?<month>\\d{1,2})/(?<day>\\d{1,2})/(?<year>\\d{4})";

        // 编译正则表达式模式
        Pattern regex = Pattern.compile(pattern);

        // 创建匹配器对象
        Matcher matcher = regex.matcher(text);

        // 查找匹配的日期
        while (matcher.find()) {
            // 使用命名捕获分组提取月、日和年
            String month = matcher.group("month");
            String day = matcher.group("day");
            String year = matcher.group("year");

            // 打印提取的日期信息
            System.out.println("Month: " + month);
            System.out.println("Day: " + day);
            System.out.println("Year: " + year);
        }
    }
}
