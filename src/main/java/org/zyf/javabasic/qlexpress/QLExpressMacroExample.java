package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @program: zyfboot-javabasic
 * @description: macro 宏定义
 * @author: zhangyanfeng
 * @create: 2023-11-19 17:40
 **/
public class QLExpressMacroExample {
    public static void main(String[] args) {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 定义宏
            runner.addMacro("计算平均成绩", "(语文+数学+英语)/3.0");
            runner.addMacro("是否优秀", "计算平均成绩>90");

            // 设置变量值
            context.put("语文", 88);
            context.put("数学", 99);
            context.put("英语", 95);

            // 执行表达式并打印结果
            Object result = runner.execute("是否优秀", context, null, false, false);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
