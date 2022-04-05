package org.zyf.javabasic.designpatterns.strategy.combination;

/**
 * @author yanfengzhang
 * @description 规定处理订单的方法
 * @date 2022/3/8  23:25
 */
public interface OrderHandler {
    /**
     * 处理订单生成消费结果
     *
     * @param order 订单基本信息
     * @return 订单实际消费结果
     */
    OrderConsumerResult handle(Order order);
}
