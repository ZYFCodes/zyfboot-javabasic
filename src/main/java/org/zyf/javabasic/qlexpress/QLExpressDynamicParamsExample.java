package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.DynamicParamsUtil;
import com.ql.util.express.ExpressRunner;

/**
 * @program: zyfboot-javabasic
 * @description: 不定参数的使用
 * @author: zhangyanfeng
 * @create: 2023-11-19 18:00
 **/
public class QLExpressDynamicParamsExample {
    public static void main(String[] args) {
        try {
            // 创建 ExpressRunner 实例
            ExpressRunner runner = new ExpressRunner();

            // 创建 DefaultContext 实例
            DefaultContext<String, Object> expressContext = new DefaultContext<>();

            // 在 runner 中添加一个函数，使用不定参数
            runner.addFunctionOfServiceMethod("getTemplate", new QLExpressDynamicParamsExample(), "getTemplate",
                    new Class[]{Object[].class}, null);

            // 调用 getTemplate 方法，传递数组作为参数
            Object resultWithArray = runner.execute("getTemplate([11, '22', 33L, true])",
                    expressContext, null, false, false);
            System.out.println("Result with Array: " + resultWithArray);

            // 打开全局开关，启用动态参数调用
            DynamicParamsUtil.supportDynamicParams = true;

            // 调用 getTemplate 方法，传递多个参数
            Object resultWithDynamicParams = runner.execute("getTemplate(11, '22', 33L, true)", expressContext,
                    null, false, false);
            System.out.println("Result with Dynamic Params: " + resultWithDynamicParams);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 等价于 getTemplate(Object[] params)
    public Object getTemplate(Object... params) {
        StringBuilder result = new StringBuilder();
        for (Object obj : params) {
            result.append(obj).append(",");
        }
        return result.toString();
    }
}
