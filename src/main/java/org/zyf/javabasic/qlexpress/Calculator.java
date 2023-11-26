package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @program: zyfboot-javabasic
 * @description: 一些简单的运算和条件判断
 * @author: zhangyanfeng
 * @create: 2023-11-12 21:48
 **/
public class Calculator {
    public static void main(String[] args) {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 设置变量
            context.put("a", 10);
            context.put("b", 5);

            // 算术运算示例
            executeAndPrint(runner, context, "a + b", "Addition");

            // 比较运算示例
            executeAndPrint(runner, context, "a > b", "Greater than");

            // 逻辑运算示例
            executeAndPrint(runner, context, "a > 0 && b > 0", "Logical AND");

            // 三元运算示例
            executeAndPrint(runner, context, "a > b ? 'a is greater' : 'b is greater'", "Ternary Operator");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void executeAndPrint(ExpressRunner runner, DefaultContext<String, Object> context, String expression, String operation) throws Exception {
        // 执行脚本
        Object result = runner.execute(expression, context, null, true, false);

        // 输出结果
        System.out.println(operation + ": " + result);
    }
}
