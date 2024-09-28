package org.zyf.javabasic.designpatterns.strategy.complex;

import lombok.extern.log4j.Log4j2;
import org.zyf.javabasic.designpatterns.strategy.combination.*;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description pc端支付宝支付，用户当前为黄金会员
 * @date 2022/3/11  23:46
 */
@Log4j2
@OrderProcessorType(source = OrderSourceEnum.PC, payMethod = OrderPayMethodEnum.ZHIFUBAO, memberType = MemberTypeEnum.GOLD)
public class PCZFBGoldOrderCpxHandler implements OrderHandler {
    /**
     * 套餐支持满100减40优惠，同时送减免整合套餐
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        BigDecimal amount = order.getAmount();
        BigDecimal basePrice = BigDecimal.valueOf(100);
        BigDecimal discountedPrice = BigDecimal.valueOf(40);
        if (amount.compareTo(basePrice) < 0) {
            return OrderConsumerResult.builder()
                    .code(order.getCode())
                    .amountSpent(amount)
                    .right("当前没有新增权益内容").build();
        }

        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(amount.subtract(discountedPrice))
                .right("送减免整合套餐").build();
    }
}
