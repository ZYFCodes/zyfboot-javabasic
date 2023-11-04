package org.zyf.javabasic.viator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

/**
 * @program: zyfboot-javabasic
 * @description: Aviator举例
 * @author: zhangyanfeng
 * @create: 2023-10-20 23:25
 **/
public class AviatorExample {
    public static void main(String[] args) {
        // 创建 Aviator 表达式
        String expressionString = "1 + 2 + 3";
        Expression expression = AviatorEvaluator.getInstance().compile(expressionString);

        // 执行表达式并获取结果
        Long result = (Long) expression.execute();

        // 输出结果
        System.out.println("Result: " + result);
    }
}
