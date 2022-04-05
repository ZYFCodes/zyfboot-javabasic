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
 * @description pc端本地钱包支付，用户当前为白银会员
 * @date 2022/3/11  23:47
 */
@Log4j2
@OrderProcessorType(source = OrderSourceEnum.PC, payMethod = OrderPayMethodEnum.WALLET, memberType = MemberTypeEnum.SILVER)
public class PCWalletSilverOrderCpxHandler implements OrderHandler {
    /**
     * 套餐支持满100减10优惠，同时送减免整合套餐和内部优惠无限制满5减5（2张）
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        BigDecimal amount = order.getAmount();
        BigDecimal basePrice = BigDecimal.valueOf(100);
        BigDecimal discountedPrice = BigDecimal.valueOf(10);
        if (amount.compareTo(basePrice) < 0) {
            return OrderConsumerResult.builder()
                    .code(order.getCode())
                    .amountSpent(amount)
                    .right("当前没有新增权益内容").build();
        }

        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(amount.subtract(discountedPrice))
                .right("送减免整合套餐和内部优惠无限制满5减5（2张）").build();
    }
}
