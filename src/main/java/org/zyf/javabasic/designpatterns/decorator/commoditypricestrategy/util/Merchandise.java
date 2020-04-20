package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 描述：具体商品
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:09
 */
@Data
public class Merchandise {
    /**
     * 商品SKU
     */
    private String sku;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品单价
     */
    private BigDecimal price;
    /**
     * 支持促销类型
     */
    private Map<PromotionType, SupportPromotions> supportPromotions;
}
