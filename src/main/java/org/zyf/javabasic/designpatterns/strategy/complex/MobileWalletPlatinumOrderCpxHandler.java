package org.zyf.javabasic.designpatterns.strategy.complex;

import lombok.extern.log4j.Log4j2;
import org.zyf.javabasic.designpatterns.strategy.combination.MemberTypeEnum;
import org.zyf.javabasic.designpatterns.strategy.combination.Order;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderConsumerResult;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderHandler;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderPayMethodEnum;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderSourceEnum;

/**
 * @author yanfengzhang
 * @description 手机端本地钱包支付，用户当前为铂金会员
 * @date 2022/3/11  23:55
 */
@Log4j2
@OrderProcessorType(source = OrderSourceEnum.MOBILE, payMethod = OrderPayMethodEnum.WALLET, memberType = MemberTypeEnum.PLATINUM)
public class MobileWalletPlatinumOrderCpxHandler implements OrderHandler {
    /**
     * 存入钱包500得800，套餐支持满100减50优惠、减40、减30各一张优惠，同时送特别礼品（随机）和内部优惠无限制满5减5（3张）
     *
     * @param order 基本订单情况
     * @return 实际消费结果
     */
    @Override
    public OrderConsumerResult handle(Order order) {
        return OrderConsumerResult.builder()
                .code(order.getCode())
                .amountSpent(order.getAmount())
                .right("存入钱包500得800，套餐支持满100减50优惠、减40、减30各一张优惠，同时送特别礼品（随机）和内部优惠无限制满5减5（3张），后续履约").build();
    }
}
