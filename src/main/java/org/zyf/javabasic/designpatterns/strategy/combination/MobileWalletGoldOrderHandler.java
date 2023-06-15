package org.zyf.javabasic.designpatterns.strategy.combination;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description 本地钱包支付宝支付，用户当前为黄金会员
 * @date 2022/3/8  23:48
 */
@Log4j2
@Service
@OrderHandlerType(source = OrderSourceEnum.MOBILE, payMethod = OrderPayMethodEnum.WALLET, memberType = MemberTypeEnum.GOLD)
public class MobileWalletGoldOrderHandler implements OrderHandler {
    /**
     * 存入钱包200得400，套餐支持满100减20优惠，同时送减免整合套餐和内部优惠无限制满5减5（2张）
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        BigDecimal amount = order.getAmount();
        BigDecimal basePrice = BigDecimal.valueOf(100);
        BigDecimal discountedPrice = BigDecimal.valueOf(20);
        if (amount.compareTo(basePrice) < 0) {
            return OrderConsumerResult.builder()
                    .code(order.getCode())
                    .amountSpent(amount)
                    .right("当前没有新增权益内容").build();
        }

        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(amount.subtract(discountedPrice))
                .right("存入钱包200得400，同时送减免整合套餐和内部优惠无限制满5减5(2张)").build();
    }
}
