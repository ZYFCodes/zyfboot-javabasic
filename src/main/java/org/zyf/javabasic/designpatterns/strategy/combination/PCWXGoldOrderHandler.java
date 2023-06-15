package org.zyf.javabasic.designpatterns.strategy.combination;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description pc端微信支付，用户当前为黄金会员
 * @date 2022/3/7  20:53
 */
@Log4j2
@Service
@OrderHandlerType(source = OrderSourceEnum.PC, payMethod = OrderPayMethodEnum.WEIXIN, memberType = MemberTypeEnum.GOLD)
public class PCWXGoldOrderHandler implements OrderHandler {
    /**
     * 套餐支持满100减20优惠，同时送减免整合套餐
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        BigDecimal amount = order.getAmount();
        BigDecimal basePrice = BigDecimal.valueOf(100);
        BigDecimal discountedPrice = BigDecimal.valueOf(20);
        /*如果没有超过100
        (a.compareTo(b) == -1)--a小于b;(a.compareTo(b) == 0)--a等于b
        (a.compareTo(b) == 1)--a大于b;(a.compareTo(b) > -1)--a大于等于b
        (a.compareTo(b) < 1)--a小于等于b*/
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
