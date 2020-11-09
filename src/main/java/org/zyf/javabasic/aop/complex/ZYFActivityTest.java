package org.zyf.javabasic.aop.complex;

import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.annotation.ZYFActivityDealer;
import org.zyf.javabasic.aop.complex.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.complex.constants.ActivityBizMethod;
import org.zyf.javabasic.aop.complex.entity.dto.PriceCutActivityDto;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/11/9  15:49
 */
@Component
public class ZYFActivityTest {

    public static void main(String[] args) {
        ZYFActivityTest zyfActivityTest = new ZYFActivityTest();
        System.out.println(zyfActivityTest.queryActivityDetail((long) 100));
    }

    @ZYFActivityDealer(activityType = ActivityBizConstants.ActivityBizType.PRICE_CUT, activityMethod = ActivityBizMethod.QUERY)
    public PriceCutActivityDto queryActivityDetail(Long id) {
        PriceCutActivityDto priceCutActivityDto = new PriceCutActivityDto();
        priceCutActivityDto.setActivityName("降价活动---AOP失效");
        return priceCutActivityDto;
    }
}
