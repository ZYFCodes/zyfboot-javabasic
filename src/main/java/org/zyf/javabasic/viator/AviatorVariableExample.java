package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 使用变量
 * @author: zhangyanfeng
 * @create: 2023-10-21 01:01
 **/
public class AviatorVariableExample {
    public static void main(String[] args) {
        // 定义变量
        Map<String, Object> variables = new HashMap<>();
        variables.put("x", 10);
        variables.put("y", 5);
        variables.put("name", "Alice");

        // 创建 Aviator 表达式
        String expressionString = "x > y ? name + ' says: Hello!' : name + ' says: Hi!'";
        Expression expression = AviatorEvaluator.getInstance().compile(expressionString);

        // 评估表达式并获取结果
        Object result = expression.execute(variables);

        // 输出结果
        System.out.println("Result: " + result); // 输出 Result: Alice says: Hello!
    }
}
