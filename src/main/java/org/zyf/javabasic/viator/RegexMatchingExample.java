package org.zyf.javabasic.viator;

import com.google.common.collect.Maps;
import com.googlecode.aviator.AviatorEvaluator;

import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 正则表达式匹配
 * @author: zhangyanfeng
 * @create: 2023-10-21 16:40
 **/
public class RegexMatchingExample {
    public static void main(String[] args) {
        // 添加自定义函数
        AviatorEvaluator.addFunction(new RegexMatchFunction());

        // 定义表达式
        String expression = "regexMatch(s, 'Hello.*')";

        Map<String, Object> env = Maps.newHashMap();
        env.put("s", "Hello, World!");

        try {
            // 解析并计算表达式
            Object result = AviatorEvaluator.execute(expression, env);
            System.out.println(result);  // 输出 true
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
