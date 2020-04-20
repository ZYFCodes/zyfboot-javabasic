package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy;

import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.OrderDetail;
import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.PromotionType;

import java.math.BigDecimal;

/**
 * 描述：计算使用红包后的金额
 *
 * @author yanfengzhang
 * @date 2020-04-19 15:24
 */
public class RedPacketDecorator extends BaseCountDecorator {

    public RedPacketDecorator(IBaseCount count) {
        super(count);
    }

    @Override
    public BigDecimal countPayMoney(OrderDetail orderDetail) {
        BigDecimal payTotalMoney = new BigDecimal(0);
        payTotalMoney = super.countPayMoney(orderDetail);
        payTotalMoney = countCouponPayMoney(orderDetail);
        return payTotalMoney;
    }

    private BigDecimal countCouponPayMoney(OrderDetail orderDetail) {

        BigDecimal redPacket = orderDetail.getMerchandise().getSupportPromotions().get(PromotionType.REDPACKED).getUserRedPacket().getRedPacket();
        System.out.println("红包优惠金额：" + redPacket);

        orderDetail.setPayMoney(orderDetail.getPayMoney().subtract(redPacket));
        return orderDetail.getPayMoney();
    }
}
