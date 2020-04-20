package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 描述：主订单
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:01
 */
@Data
public class Order {
    /**
     * 订单ID
     */
    private int id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 总支付金额
     */
    private BigDecimal totalPayMoney;
    /**
     * 详细订单列表
     */
    private List<OrderDetail> list;
}
