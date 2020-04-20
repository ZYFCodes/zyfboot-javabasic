package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy;

import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.OrderDetail;

import java.math.BigDecimal;

/**
 * 描述：计算支付金额的抽象类
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:48
 */
public abstract class BaseCountDecorator implements IBaseCount {
    private IBaseCount count;

    public BaseCountDecorator(IBaseCount count) {
        this.count = count;
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        if (count != null) {
            payTotalMoney = count.countPayMoney(orderDetail);
        }
        return payTotalMoney;
    }
}
