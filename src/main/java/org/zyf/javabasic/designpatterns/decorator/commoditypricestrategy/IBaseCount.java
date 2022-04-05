package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy;

import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.OrderDetail;

import java.math.BigDecimal;

/**
 * 描述：计算支付金额接口类
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:09
 */

public interface IBaseCount {
    /**
     * 功能描述：计算支付金额
     *
     * @param orderDetail
     * @return BigDecimal
     * @author yanfengzhang
     * @date 2020-04-19 14:40
     */
    BigDecimal countPayMoney(OrderDetail orderDetail);
}
