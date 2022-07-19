package org.zyf.javabasic.aop.bizdeal.service.activity.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.bizdeal.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.bizdeal.entity.ActivityDo;
import org.zyf.javabasic.aop.bizdeal.entity.dto.DiscountActivityDto;
import org.zyf.javabasic.aop.bizdeal.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.bizdeal.service.activity.ActivityService;

/**
 * @author yanfengzhang
 * @description 折上优惠活动相关方法整理
 * @date 2020/11/2  12:14
 */
@Component
@Slf4j
public class DiscountActivityServiceImpl implements ActivityService<DiscountActivityDto>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register(ActivityBizConstants.ActivityBizType.DISCOUNT, this);
    }

    @Override
    public DiscountActivityDto queryActivityDetail(Object[] args) {
        /*按传入信息查当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        DiscountActivityDto discountActivityDto = new DiscountActivityDto();
        discountActivityDto.setActivityType(6);
        discountActivityDto.setActivityName("折上优惠活动");

        log.info("查询折上优惠活动:{}",discountActivityDto);

        return discountActivityDto;
    }

    @Override
    public DiscountActivityDto createOrUpdateActivityDetail(Object[] args) {
        /*按传入信息更新或创建当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        DiscountActivityDto discountActivityDto = new DiscountActivityDto();
        discountActivityDto.setActivityType(6);
        discountActivityDto.setActivityName("折上优惠活动");

        log.info("创建或更新折上优惠活动:{}",discountActivityDto);

        return discountActivityDto;
    }

    @Override
    public void deleteActivityDetail(Object[] args) {
        log.info("删除折上优惠活动");
    }

    @Override
    public boolean checkActivityInfo(Object[] args) {
        log.info("校验折上优惠活动信息是否有误");
        return false;
    }

    @Override
    public void onlineActivity(Object[] args) {
        log.info("上线折上优惠活动");
    }

    @Override
    public void offlineActivity(Object[] args) {
        log.info("下线折上优惠活动");
    }
}
