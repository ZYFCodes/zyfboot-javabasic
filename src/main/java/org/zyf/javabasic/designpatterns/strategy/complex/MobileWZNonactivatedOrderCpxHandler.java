package org.zyf.javabasic.designpatterns.strategy.complex;

import lombok.extern.log4j.Log4j2;
import org.zyf.javabasic.designpatterns.strategy.combination.*;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description 手机端端微信或支付宝支付，用户当前未开通会员
 * @date 2022/3/11  23:50
 */
@Log4j2
@OrderProcessorType(source = OrderSourceEnum.MOBILE, payMethod = {OrderPayMethodEnum.ZHIFUBAO, OrderPayMethodEnum.WEIXIN}, memberType = MemberTypeEnum.NONACTIVATED)
public class MobileWZNonactivatedOrderCpxHandler implements OrderHandler {
    /**
     * 订单满10减3，送腾讯会员7天或爱奇艺会员7天（随机）
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
                .right("送腾讯会员7天或爱奇艺会员7天（随机）").build();
    }
}
