package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @program: zyfboot-javabasic
 * @description: 通过 function 关键字来定义函数
 * @author: zhangyanfeng
 * @create: 2023-11-12 22:11
 **/
public class QLExpressFunctionExample {
    public static void main(String[] args) {
        try {
            final String express = "function add(int a, int b){\n" +
                    "  return a + b;\n" +
                    "};\n" +
                    "\n" +
                    "function sub(int a, int b){\n" +
                    "  return a - b;\n" +
                    "};\n" +
                    "\n" +
                    "a = 10;\n" +
                    "result = add(a, 4) + sub(a, 9);\n" +
                    "return result;";
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 执行脚本
            Object result = runner.execute(express, context, null, true, false);

            // 输出脚本执行结果
            System.out.println("Result: " + result);

            // 输出函数调用过程中的参数和返回值
            System.out.println("add function call: a + 4 = " + context.get("a") + " + 4 = " + context.get("result"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





