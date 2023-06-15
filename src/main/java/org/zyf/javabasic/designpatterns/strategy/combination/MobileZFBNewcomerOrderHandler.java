package org.zyf.javabasic.designpatterns.strategy.combination;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description 手机端支付宝支付，用户当前为新晋会员
 * @date 2022/3/8  23:46
 */
@Log4j2
@Service
@OrderHandlerType(source = OrderSourceEnum.MOBILE, payMethod = OrderPayMethodEnum.ZHIFUBAO, memberType = MemberTypeEnum.NEWCOMER)
public class MobileZFBNewcomerOrderHandler implements OrderHandler {
    /**
     * 支持满130减120优惠，同时赠送新晋套餐
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        BigDecimal amount = order.getAmount();
        BigDecimal basePrice = BigDecimal.valueOf(130);
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
                .right("赠送新晋套餐").build();
    }
}
