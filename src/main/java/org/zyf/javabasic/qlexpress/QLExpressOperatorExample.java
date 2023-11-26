package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义操作符（Operator）来扩展语言的功能
 * @author: zhangyanfeng
 * @create: 2023-11-12 23:13
 **/
public class QLExpressOperatorExample {
    public static void main(String[] args) {
        try {
            // 示例 1：替换 if then else 关键字
            ExpressRunner runner1 = new ExpressRunner();
            DefaultContext<String, Object> context1 = new DefaultContext<>();
            context1.put("语文",120);
            context1.put("数学",23);
            context1.put("英语",23);

            runner1.addOperatorWithAlias("如果", "if", null);
            runner1.addOperatorWithAlias("则", "then", null);
            runner1.addOperatorWithAlias("否则", "else", null);

            String express1 = "如果 (语文 + 数学 + 英语 > 270) 则 {return 1;} 否则 {return 0;}";
            Object result1 = runner1.execute(express1, context1, null, false, false, 100L);
            System.out.println("Result 1: " + result1); // 输出结果 1

            // 示例 2：自定义 Operator
            ExpressRunner runner2 = new ExpressRunner();
            DefaultContext<String, Object> context2 = new DefaultContext<>();

            // 自定义 Operator
            runner2.addOperator("join", new JoinOperator());

            // 示例 2.1：addOperator
            Object result2_1 = runner2.execute("1 join 2 join 3", context2, null, false, false);
            System.out.println("Result 2.1: " + result2_1); // 输出结果 [1, 2, 3]

            // 示例 2.2：replaceOperator
            ExpressRunner runner2_2 = new ExpressRunner();
            runner2_2.replaceOperator("+", new JoinOperator());
            Object result2_2 = runner2_2.execute("1 + 2 + 3", context2, null, false, false);
            System.out.println("Result 2.2: " + result2_2); // 输出结果 [1, 2, 3]

            // 示例 2.3：addFunction
            ExpressRunner runner2_3 = new ExpressRunner();
            runner2_3.addFunction("join", new JoinOperator());
            Object result2_3 = runner2_3.execute("join(1, 2, 3)", context2, null, false, false);
            System.out.println("Result 2.3: " + result2_3); // 输出结果 [1, 2, 3]

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // JoinOperator 类的定义
    public static class JoinOperator extends com.ql.util.express.Operator {
        public Object executeInner(Object[] list) throws Exception {
            Object opdata1 = list[0];
            Object opdata2 = list[1];
            if (opdata1 instanceof List) {
                ((List) opdata1).add(opdata2);
                return opdata1;
            } else {
                List result = new ArrayList();
                for (Object opdata : list) {
                    result.add(opdata);
                }
                return result;
            }
        }
    }
}
