package org.zyf.javabasic.designpatterns.strategy.complex;

import lombok.extern.log4j.Log4j2;
import org.zyf.javabasic.designpatterns.strategy.combination.MemberTypeEnum;
import org.zyf.javabasic.designpatterns.strategy.combination.Order;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderConsumerResult;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderHandler;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderPayMethodEnum;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderSourceEnum;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description pc端微信或支付宝支付，用户当前为新晋会员
 * @date 2022/3/11  23:44
 */
@Log4j2
@OrderProcessorType(source = OrderSourceEnum.PC, payMethod = {OrderPayMethodEnum.ZHIFUBAO, OrderPayMethodEnum.WEIXIN}, memberType = MemberTypeEnum.NEWCOMER)
public class PCWZNewcomerOrderCpxHandler implements OrderHandler {
    /**
     * 支持满100减50优惠，同时赠送新晋套餐
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        BigDecimal amount = order.getAmount();
        BigDecimal basePrice = BigDecimal.valueOf(100);
        BigDecimal discountedPrice = BigDecimal.valueOf(50);
        if (amount.compareTo(basePrice) < 0) {
            return OrderConsumerResult.builder()
                    .code(order.getCode())
                    .amountSpent(amount)
                    .right("当前没有新增权益内容").build();
        }

        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(amount.subtract(discountedPrice))
                .right("赠送新晋套餐").build();
    }
}
