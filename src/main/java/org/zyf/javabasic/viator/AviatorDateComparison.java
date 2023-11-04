package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @program: zyfboot-javabasic
 * @description: 演示验证日期比较
 * @author: zhangyanfeng
 * @create: 2023-10-29 17:59
 **/
public class AviatorDateComparison {
    public static void main(String[] args) throws ParseException {
        // 创建日期格式化器
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // 创建两个日期对象
        Date date1 = sdf.parse("2010-01-01");
        Date date2 = sdf.parse("2010-01-02");

        // 创建 Aviator 表达式环境
        Map<String, Object> env = new HashMap<>();
        env.put("date1", date1);
        env.put("date2", date2);

        // 比较日期是否相等
        boolean isEqual = (boolean) AviatorEvaluator.execute("date1 == date2", env);
        System.out.println("Are the dates equal? " + isEqual);  // 输出 "Are the dates equal? false"

        // 比较日期的大小
        boolean isGreaterThan = (boolean) AviatorEvaluator.execute("date1 > date2", env);
        System.out.println("Is date1 greater than date2? " + isGreaterThan);  // 输出 "Is date1 greater than date2? false"

        // 计算日期的差值（天数差）
        long dateDiff = date2.getTime() - date1.getTime();
        env.put("dateDiff", TimeUnit.DAYS.convert(dateDiff, TimeUnit.MILLISECONDS));

        // 使用变量 dateDiff 计算日期差值
        long calculatedDateDiff = (long) AviatorEvaluator.execute("dateDiff", env);
        System.out.println("Date difference in days: " + calculatedDateDiff);  // 输出 "Date difference in days: 1"
    }
}
