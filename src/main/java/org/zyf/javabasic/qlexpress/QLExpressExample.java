package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @program: zyfboot-javabasic
 * @description: 演示如何使用 QLExpress 计算折扣后的金额
 * @author: zhangyanfeng
 * @create: 2023-11-12 21:40
 **/
public class QLExpressExample {
    public static void main(String[] args) {
        try {
            // 创建 QLExpress 引擎
            ExpressRunner runner = new ExpressRunner();

            // 创建上下文并设置变量
            DefaultContext<String, Object> context = new DefaultContext<>();
            context.put("amount", 1000);
            context.put("discount", 0.1);

            // 执行脚本
            String expression = "amount * (1 - discount)";
            Object result = runner.execute(expression, context, null, true, false);

            // 输出结果
            System.out.println("Result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
