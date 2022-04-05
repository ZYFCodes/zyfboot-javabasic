package org.zyf.javabasic.designpatterns.strategy.combination;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author yanfengzhang
 * @description 订单消费结果
 * @date 2022/3/8  23:14
 */
@Data
@Builder
public class OrderConsumerResult {
    /**
     * 订单编号
     */
    private String code;
    /**
     * 实际支付金额
     */
    private BigDecimal amountSpent;
    /**
     * 本次订单可获取的权益情况 （只做简单测试，所以不做复杂逻辑处理了）
     */
    private String right;
}
