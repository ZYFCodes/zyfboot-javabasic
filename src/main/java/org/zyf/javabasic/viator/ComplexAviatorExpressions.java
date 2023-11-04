package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * @program: zyfboot-javabasic
 * @description: 复杂表达式计算
 * @author: zhangyanfeng
 * @create: 2023-10-21 00:48
 **/
public class ComplexAviatorExpressions {
    public static void main(String[] args) {
        // 创建 Aviator 表达式
        String expressionString = "((5 + 3) * 2) / (4 - 1)";

        Expression expression = AviatorEvaluator.getInstance().compile(expressionString);

        // 执行表达式并获取结果
        Number result = (Number) expression.execute();

        // 输出结果
        System.out.println("Result: " + result); // 6.0
    }
}
