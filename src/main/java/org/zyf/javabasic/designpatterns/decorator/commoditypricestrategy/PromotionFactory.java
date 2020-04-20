package org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy;

import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.OrderDetail;
import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.PromotionType;
import org.zyf.javabasic.designpatterns.decorator.commoditypricestrategy.util.SupportPromotions;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 描述：计算促销后的支付价格
 *
 * @author yanfengzhang
 * @date 2020-04-19 14:58
 */
public class PromotionFactory {
    public static BigDecimal getPayMoney(OrderDetail orderDetail) {
        /**获取给商品设定的促销类型*/
        Map<PromotionType, SupportPromotions> supportPromotionsList = orderDetail.getMerchandise().getSupportPromotions();

        /**初始化计算类*/
        IBaseCount baseCount = new BaseCount();
        if (supportPromotionsList != null && supportPromotionsList.size() > 0) {
            for (PromotionType promotionType : supportPromotionsList.keySet()) {
                /**遍历设置的促销类型，通过装饰器组合促销类型*/
                baseCount = protmotion(supportPromotionsList.get(promotionType), baseCount);
            }
        }
        return baseCount.countPayMoney(orderDetail);
    }

    /**
     * 组合促销类型 * @param supportPromotions * @param baseCount * @return
     */
    private static IBaseCount protmotion(SupportPromotions supportPromotions, IBaseCount baseCount) {
        if (supportPromotions.getPromotionType() == PromotionType.COUPON) {
            baseCount = new CouponDecorator(baseCount);
        } else if (supportPromotions.getPromotionType() == PromotionType.REDPACKED) {
            baseCount = new RedPacketDecorator(baseCount);
        }
        return baseCount;
    }
}
