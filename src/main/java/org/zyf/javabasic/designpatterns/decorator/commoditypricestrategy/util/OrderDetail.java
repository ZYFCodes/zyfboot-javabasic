package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util;

import lombok.Data;
import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.Merchandise;

import java.math.BigDecimal;

/**
 * 描述：详细订单
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:06
 */
@Data
public class OrderDetail {
    /**
     * 详细订单ID
     */
    private int id;
    /**
     * 主订单ID
     */
    private int orderId;
    /**
     * 商品详情
     */
    private Merchandise merchandise;
    /**
     * 支付单价
     */
    private BigDecimal payMoney;
}
