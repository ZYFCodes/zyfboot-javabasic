package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: exec 方法
 * @author: zhangyanfeng
 * @create: 2023-10-21 01:12
 **/
public class AviatorExecMethodExample {
    public static void main(String[] args) {
        // 创建 Aviator 表达式
        String expressionString = "x + y";

        // 编译表达式
        Expression expression = AviatorEvaluator.getInstance().compile(expressionString);

        // 定义变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 10);
        variables.put("y", 5);

        // 第一次执行表达式
        Object result1 = expression.execute(variables);
        System.out.println("Result 1: " + result1); // 输出 Result 1: 15

        // 修改变量值
        variables.put("x", 20);
        variables.put("y", 8);

        // 第二次执行表达式
        Object result2 = expression.execute(variables);
        System.out.println("Result 2: " + result2); // 输出 Result 2: 28
    }
}
