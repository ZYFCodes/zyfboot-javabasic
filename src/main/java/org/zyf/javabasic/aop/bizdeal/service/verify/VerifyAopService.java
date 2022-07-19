package org.zyf.javabasic.aop.bizdeal.service.verify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.bizdeal.annotation.ZYFActivityDealer;
import org.zyf.javabasic.aop.bizdeal.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.bizdeal.constants.ActivityBizMethod;
import org.zyf.javabasic.aop.bizdeal.entity.dto.AutoRenewalActivityDto;
import org.zyf.javabasic.aop.bizdeal.entity.dto.BuyForGetActivityDto;
import org.zyf.javabasic.aop.bizdeal.entity.dto.DiscountActivityDto;
import org.zyf.javabasic.aop.bizdeal.entity.dto.FirstPurchaseActivityDto;
import org.zyf.javabasic.aop.bizdeal.entity.dto.LimitTimeActivityDto;
import org.zyf.javabasic.aop.bizdeal.entity.dto.PriceCutActivityDto;

/**
 * @author yanfengzhang
 * @description 各活动AOP实现操作汇总
 * @date 2020/11/10  23:19
 */
@Component
@Slf4j
public class VerifyAopService {

    /**
     * 降价活动的相关AOP操作
     */
    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.PRICE_CUT, activityMethod = ActivityBizMethod.QUERY)
    public PriceCutActivityDto queryPriceCutActivityDetail(Long id) {
        PriceCutActivityDto priceCutActivityDto = PriceCutActivityDto.builder().activityType(1).activityName("降价活动").build();
        priceCutActivityDto.setActivityName("降价活动---AOP失效");
        return priceCutActivityDto;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.PRICE_CUT, activityMethod = ActivityBizMethod.CREATE_OR_UPDATE)
    public void addOrUpdatePriceCutActivityDetail(PriceCutActivityDto priceCutActivityDto) {
        log.info("addOrUpdatePriceCutActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.PRICE_CUT, activityMethod = ActivityBizMethod.DELETE)
    public boolean deletePriceCutActivityDetail(Long id) {
        return true;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.PRICE_CUT, activityMethod = ActivityBizMethod.ONLINE)
    public void onlinePriceCutActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.PRICE_CUT, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlinePriceCutActivityDetail(Long id) {
        return;
    }

    /**
     * 首购优惠活动相关AOP实现
     */
    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.FIRST_PURCHASE, activityMethod = ActivityBizMethod.QUERY)
    public FirstPurchaseActivityDto queryFirstPurchaseActivityDetail(Long id) {
        FirstPurchaseActivityDto firstPurchaseActivityDto = new FirstPurchaseActivityDto();
        firstPurchaseActivityDto.setActivityName("首购优惠活动---AOP失效");
        return firstPurchaseActivityDto;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.FIRST_PURCHASE, activityMethod = ActivityBizMethod.CREATE_OR_UPDATE)
    public void addOrUpdateFirstPurchaseActivityDetail(FirstPurchaseActivityDto firstPurchaseActivityDto) {
        log.info("addOrUpdateFirstPurchaseActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.FIRST_PURCHASE, activityMethod = ActivityBizMethod.DELETE)
    public void deleteFirstPurchaseActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.FIRST_PURCHASE, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineFirstPurchaseActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.FIRST_PURCHASE, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineFirstPurchaseActivityDetail(Long id) {
        return;
    }


    /**
     * 限时活动相关AOP实现
     */
    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.LIMIT_TIME, activityMethod = ActivityBizMethod.QUERY)
    public LimitTimeActivityDto queryLimitTimeActivityDetail(Long id) {
        LimitTimeActivityDto limitTimeActivityDto = new LimitTimeActivityDto();
        limitTimeActivityDto.setActivityName("限时活动---AOP失效");
        return limitTimeActivityDto;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.LIMIT_TIME, activityMethod = ActivityBizMethod.CREATE_OR_UPDATE)
    public void addOrUpdateLimitTimeActivityDetail(LimitTimeActivityDto limitTimeActivityDto) {
        log.info("addOrUpdateLimitTimeActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.LIMIT_TIME, activityMethod = ActivityBizMethod.DELETE)
    public void deleteLimitTimeActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.LIMIT_TIME, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineLimitTimeActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.LIMIT_TIME, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineLimitTimeActivityDetail(Long id) {
        return;
    }

    /**
     * 折上优惠活动相关AOP实现
     */
    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.DISCOUNT, activityMethod = ActivityBizMethod.QUERY)
    public DiscountActivityDto queryDiscountActivityDetail(Long id) {
        DiscountActivityDto discountActivityDto = new DiscountActivityDto();
        discountActivityDto.setActivityName("折上优惠活动---AOP失效");
        return discountActivityDto;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.DISCOUNT, activityMethod = ActivityBizMethod.CREATE_OR_UPDATE)
    public void addOrUpdateDiscountActivityDetail(DiscountActivityDto discountActivityDto) {
        log.info("addOrUpdateDiscountActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.DISCOUNT, activityMethod = ActivityBizMethod.DELETE)
    public void deleteDiscountActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.DISCOUNT, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineDiscountActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.DISCOUNT, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineDiscountActivityDetail(Long id) {
        return;
    }

    /**
     * 买赠活动相关AOP实现
     */
    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.BUY_FOR_GET, activityMethod = ActivityBizMethod.QUERY)
    public BuyForGetActivityDto queryBuyForGetActivityDetail(Long id) {
        BuyForGetActivityDto buyForGetActivityDto = new BuyForGetActivityDto();
        buyForGetActivityDto.setActivityName("买赠活动---AOP失效");
        return buyForGetActivityDto;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.BUY_FOR_GET, activityMethod = ActivityBizMethod.CREATE_OR_UPDATE)
    public void addOrUpdateBuyForGetActivityDetail(BuyForGetActivityDto buyForGetActivityDto) {
        log.info("addOrUpdateBuyForGetActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.BUY_FOR_GET, activityMethod = ActivityBizMethod.DELETE)
    public void deleteBuyForGetActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.BUY_FOR_GET, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineBuyForGetActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.BUY_FOR_GET, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineBuyForGetActivityDetail(Long id) {
        return;
    }

    /**
     * 自动续费活动相关AOP实现
     */
    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.AUTO_RENEMAL, activityMethod = ActivityBizMethod.QUERY)
    public AutoRenewalActivityDto queryAutoRenewalActivityDetail(Long id) {
        AutoRenewalActivityDto autoRenewalActivityDto = new AutoRenewalActivityDto();
        autoRenewalActivityDto.setActivityName("自动续费活动---AOP失效");
        return autoRenewalActivityDto;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.AUTO_RENEMAL, activityMethod = ActivityBizMethod.CREATE_OR_UPDATE)
    public void addOrUpdateAutoRenewalActivityDetail(AutoRenewalActivityDto autoRenewalActivityDto) {
        log.info("addOrUpdateAutoRenewalActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.AUTO_RENEMAL, activityMethod = ActivityBizMethod.DELETE)
    public void deleteAutoRenewalActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.AUTO_RENEMAL, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineAutoRenewalActivityDetail(Long id) {
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.AUTO_RENEMAL, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineAutoRenewalActivityDetail(Long id) {
        return;
    }


}
