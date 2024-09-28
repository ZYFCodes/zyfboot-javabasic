package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

/**
 * @program: zyfboot-javabasic
 * @description: 编译脚本，查询外部需要定义的变量和函数。
 * @author: zhangyanfeng
 * @create: 2023-11-19 17:49
 **/
public class QLExpressCompileExample {
    public static void main(String[] args) {
        try {
            ExpressRunner runner = new ExpressRunner(true, true);
            DefaultContext<String, Object> context = new DefaultContext<>();
            context.put("语文", 120);
            context.put("数学", 23);
            context.put("英语", 23);
            context.put("综合考试", 235);

            // 定义脚本
            String express = "double 平均分 = (语文 + 数学 + 英语 + 综合考试) / 4.0; return 平均分";

            // 查询外部需要定义的变量
            String[] variableNames = runner.getOutVarNames(express);
            System.out.println("外部需要定义的变量:");
            for (String variableName : variableNames) {
                System.out.println("var : " + variableName);
            }

            // 查询外部需要定义的函数
            String[] functionNames = runner.getOutFunctionNames(express);
            System.out.println("\n外部需要定义的函数:");
            for (String functionName : functionNames) {
                System.out.println("function : " + functionName);
            }

            // 编译脚本并执行
            Object result = runner.execute(express, context, null, false, false);
            System.out.println("\n脚本执行结果: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
