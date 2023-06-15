package org.zyf.javabasic.designpatterns.strategy.combination;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description pc端支付宝支付，用户当前为铂金会员
 * @date 2022/3/8  11:27
 */
@Log4j2
@Service
@OrderHandlerType(source = OrderSourceEnum.PC, payMethod = OrderPayMethodEnum.ZHIFUBAO, memberType = MemberTypeEnum.PLATINUM)
public class PCZFBPlatinumOrderHandler implements OrderHandler {
    /**
     * 套餐支持满150减120优惠、减100、减80各一张优惠，同时送特别礼品（随机）
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        BigDecimal amount = order.getAmount();
        BigDecimal basePrice = BigDecimal.valueOf(150);
        BigDecimal discountedPrice = BigDecimal.valueOf(120);
        if (amount.compareTo(basePrice) < 0) {
            return OrderConsumerResult.builder()
                    .code(order.getCode())
                    .amountSpent(amount)
                    .right("当前没有新增权益内容").build();
        }

        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(amount.subtract(discountedPrice))
                .right("本次套餐支持满150减120优惠，同时赠送满150减100、减80各一张优惠，送特别礼品（随机），后续履约").build();
    }
}
