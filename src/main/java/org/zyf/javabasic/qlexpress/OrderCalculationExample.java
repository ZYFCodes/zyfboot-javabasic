package org.zyf.javabasic.qlexpress;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.math.BigDecimal;

/**
 * @program: zyfboot-javabasic
 * @description: 在订单计价场景中如何使用 QLExpress 的高精度计算功能
 * @author: zhangyanfeng
 * @create: 2023-11-19 20:01
 **/
public class OrderCalculationExample {
    public static void main(String[] args) {
        try {
            // 创建 QLExpress 运行器
            ExpressRunner runner = new ExpressRunner();

            // 创建上下文并设置订单相关参数
            DefaultContext<String, Object> context = new DefaultContext<>();
            context.put("单价", new BigDecimal("10.5"));
            context.put("数量", new BigDecimal("100"));
            context.put("首重价格", new BigDecimal("5.0"));
            context.put("总重量", new BigDecimal("120.5"));
            context.put("首重", new BigDecimal("100"));
            context.put("续重单价", new BigDecimal("2.0"));

            // 计算订单总价公式
            String formula = "double e = 单价 * 数量 + 首重价格 + （总重量 - 首重） * 续重单价";
            Object result = runner.execute(formula, context, null, false, false);

            // 输出结果
            System.out.println("订单总价: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
