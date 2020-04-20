package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：优惠券
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:13
 */
@Data
public class UserCoupon {
    /**
     * 优惠券ID
     */
    private int id;
    /**
     * 领取优惠券用户ID
     */
    private int userId;
    /**
     * 商品SKU
     */
    private String sku;
    /**
     * 优惠金额
     */
    private BigDecimal coupon;
}
