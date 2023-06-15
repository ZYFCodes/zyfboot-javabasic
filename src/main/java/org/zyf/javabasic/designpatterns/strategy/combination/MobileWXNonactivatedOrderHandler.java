package org.zyf.javabasic.designpatterns.strategy.combination;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description 手机端微信支付，用户为未开通状态
 * @date 2022/3/8  23:42
 */
@Service
@OrderHandlerType(source = OrderSourceEnum.MOBILE, payMethod = OrderPayMethodEnum.WEIXIN, memberType = MemberTypeEnum.NONACTIVATED)
public class MobileWXNonactivatedOrderHandler implements OrderHandler {
    /**
     * 订单满10减3，送腾讯会员3天
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        BigDecimal amount = order.getAmount();
        BigDecimal basePrice = BigDecimal.valueOf(10);
        BigDecimal discountedPrice = BigDecimal.valueOf(3);
        if (amount.compareTo(basePrice) < 0) {
            return OrderConsumerResult.builder()
                    .code(order.getCode())
                    .amountSpent(amount)
                    .right("当前没有新增权益内容").build();
        }

        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(amount.subtract(discountedPrice))
                .right("送腾讯会员3天").build();
    }
}
