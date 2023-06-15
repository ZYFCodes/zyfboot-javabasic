package org.zyf.javabasic.designpatterns.strategy.combination;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/8  11:35
 */

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * @author yanfengzhang
 * @description pc端本地钱包支付支付，用户为未开通状态
 * @date 2022/3/8  23:39
 */
@Log4j2
@Service
@OrderHandlerType(source = OrderSourceEnum.PC, payMethod = OrderPayMethodEnum.WALLET, memberType = MemberTypeEnum.NONACTIVATED)
public class PCWalletNonactivatedOrderHandler implements OrderHandler {
    /**
     * 存入钱包50得60，订单满30减30
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(order.getAmount())
                .right("存入钱包50得60，订单满30减30").build();
    }
}
