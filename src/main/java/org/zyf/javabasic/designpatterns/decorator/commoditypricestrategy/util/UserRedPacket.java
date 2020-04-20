package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 描述：红包
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:14
 */
@Data
public class UserRedPacket {
    /**
     * 红包ID
     */
    private int id;
    /**
     * 领取用户ID
     */
    private int userId;
    /**
     * 商品SKU
     */
    private String sku;
    /**
     * 领取红包金额
     */
    private BigDecimal redPacket;
}
