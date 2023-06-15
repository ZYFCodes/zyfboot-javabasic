package org.zyf.javabasic.designpatterns.strategy.combination;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author yanfengzhang
 * @description pc端微信支付，用户为未开通状态
 * @date 2022/3/8  23:35
 */
@Log4j2
@Service
@OrderHandlerType(source = OrderSourceEnum.PC, payMethod = OrderPayMethodEnum.WEIXIN, memberType = MemberTypeEnum.NONACTIVATED)
public class PCWXNonactivatedOrderHandler implements OrderHandler {
    /**
     * 进行原价支付
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(order.getAmount())
                .right("该会员不参与其他权益履约适宜").build();
    }
}
