package org.zyf.javabasic.aop.complex.service.verify;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.annotation.ZYFActivityDealer;
import org.zyf.javabasic.aop.complex.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.complex.constants.ActivityBizMethod;
import org.zyf.javabasic.aop.complex.entity.dto.AutoRenewalActivityDto;
import org.zyf.javabasic.aop.complex.entity.dto.BuyForGetActivityDto;
import org.zyf.javabasic.aop.complex.entity.dto.DiscountActivityDto;
import org.zyf.javabasic.aop.complex.entity.dto.FirstPurchaseActivityDto;
import org.zyf.javabasic.aop.complex.entity.dto.LimitTimeActivityDto;
import org.zyf.javabasic.aop.complex.entity.dto.PriceCutActivityDto;

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
        PriceCutActivityDto priceCutActivityDto = new PriceCutActivityDto();
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
        log.info("deletePriceCutActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return true;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.PRICE_CUT, activityMethod = ActivityBizMethod.ONLINE)
    public void onlinePriceCutActivityDetail(Long id) {
        log.info("onlinePriceCutActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.PRICE_CUT, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlinePriceCutActivityDetail(Long id) {
        log.info("offlinePriceCutActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
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
        log.info("deleteFirstPurchaseActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.FIRST_PURCHASE, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineFirstPurchaseActivityDetail(Long id) {
        log.info("onlineFirstPurchaseActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.FIRST_PURCHASE, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineFirstPurchaseActivityDetail(Long id) {
        log.info("offlineFirstPurchaseActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
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
        log.info("deleteLimitTimeActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.LIMIT_TIME, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineLimitTimeActivityDetail(Long id) {
        log.info("onlineLimitTimeActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.LIMIT_TIME, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineLimitTimeActivityDetail(Long id) {
        log.info("offlineLimitTimeActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
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
        log.info("deleteDiscountActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.DISCOUNT, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineDiscountActivityDetail(Long id) {
        log.info("onlineDiscountActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.DISCOUNT, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineDiscountActivityDetail(Long id) {
        log.info("offlineDiscountActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
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
        log.info("deleteBuyForGetActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.BUY_FOR_GET, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineBuyForGetActivityDetail(Long id) {
        log.info("onlineBuyForGetActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.BUY_FOR_GET, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineBuyForGetActivityDetail(Long id) {
        log.info("offlineBuyForGetActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
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
        log.info("deleteAutoRenewalActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.AUTO_RENEMAL, activityMethod = ActivityBizMethod.ONLINE)
    public void onlineAutoRenewalActivityDetail(Long id) {
        log.info("onlineAutoRenewalActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.AUTO_RENEMAL, activityMethod = ActivityBizMethod.OFFLINE)
    public void offlineAutoRenewalActivityDetail(Long id) {
        log.info("offlineAutoRenewalActivityDetail：若打印本日志，则说明对应增加或更新AOP未按切面进行或AOP失效，按照本方法的实际内容操作！");
        return;
    }


}
