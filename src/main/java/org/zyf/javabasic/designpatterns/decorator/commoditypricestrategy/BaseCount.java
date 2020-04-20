package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy;

import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.OrderDetail;

import java.math.BigDecimal;

/**
 * 描述：支付基本类
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:41
 */
public class BaseCount implements IBaseCount {
    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        orderDetail.setPayMoney(orderDetail.getMerchandise().getPrice());
        System.out.println("商品原单价金额为：" + orderDetail.getPayMoney());
        return orderDetail.getPayMoney();
    }
}
