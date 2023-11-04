package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * @program: zyfboot-javabasic
 * @description: 示例自定义函数
 * @author: zhangyanfeng
 * @create: 2023-10-21 15:20
 **/
public class AviatorCustomFunctionExample {
    public static void main(String[] args) {
        // 定义自定义函数并添加到 Aviator
        AviatorEvaluator.addFunction(new MultiplyFunction());

        // 定义表达式
        String expression = "multiply(3, 4)";

        try {
            // 解析并计算表达式
            Object result = AviatorEvaluator.execute(expression);
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
