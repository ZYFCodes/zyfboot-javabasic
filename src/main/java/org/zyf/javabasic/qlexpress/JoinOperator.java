package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 实现一个功能强大的 join 操作符
 * @author: zhangyanfeng
 * @create: 2023-12-03 22:27
 **/
public class JoinOperator extends Operator {
    public Object executeInner(Object[] list) throws Exception {
        List<Object> result = new ArrayList<>();
        for (Object opdata : list) {
            if (opdata instanceof List) {
                result.addAll((List<?>) opdata);
            } else {
                result.add(opdata);
            }
        }
        return result;

    }

    public static void main(String[] args) {
        try {
            ExpressRunner runner = new ExpressRunner();
            DefaultContext<String, Object> context = new DefaultContext<>();

            // 注入自定义的 join 操作符
            runner.addOperator("join", new JoinOperator());

            // 执行脚本，测试 join 操作符
            Object result1 = runner.execute("_list = 1 join 2 join 3;_", context, null, false, false);
            System.out.println("Result 1: " + result1);  // 预期输出: [1, 2, 3]

            Object result2 = runner.execute("_list = join(list, 4, 5, 6);_", context, null, false, false);
            System.out.println("Result 2: " + result2);  // 预期输出: [1, 2, 3, 4, 5, 6]

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

