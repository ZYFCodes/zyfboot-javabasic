package org.zyf.javabasic.aop.complex.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.entity.ActivityDo;
import org.zyf.javabasic.aop.complex.entity.dto.FirstPurchaseActivityDto;
import org.zyf.javabasic.aop.complex.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.complex.service.ActivityService;

/**
 * @author yanfengzhang
 * @description 首购优惠活动相关操作
 * @date 2020/11/2  12:10
 */
@Component
@Slf4j
public class FirstPurchaseActivityServiceImpl implements ActivityService<FirstPurchaseActivityDto>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register("4", this);
    }

    @Override
    public FirstPurchaseActivityDto queryActivityDetail(Object[] args) {
        /*按传入信息查当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        FirstPurchaseActivityDto firstPurchaseActivityDto = new FirstPurchaseActivityDto();
        firstPurchaseActivityDto.setActivityType(4);
        firstPurchaseActivityDto.setActivityName("首购优惠活动");

        log.info("查询首购优惠活动");

        return firstPurchaseActivityDto;
    }

    @Override
    public FirstPurchaseActivityDto createOrUpdateActivityDetail(Object[] args) {
        /*按传入信息创建或更新当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        FirstPurchaseActivityDto firstPurchaseActivityDto = new FirstPurchaseActivityDto();
        firstPurchaseActivityDto.setActivityType(4);
        firstPurchaseActivityDto.setActivityName("首购优惠活动");

        log.info("创建或更新首购优惠活动");

        return firstPurchaseActivityDto;
    }

    @Override
    public void deleteActivityDetail(Object[] args) {
        log.info("删除首购优惠活动");
    }

    @Override
    public boolean checkActivityInfo(Object[] args) {
        log.info("校验首购优惠活动信息是否有误");
        return false;
    }

    @Override
    public void onlineActivity(Object[] args) {
        log.info("上线首购优惠活动");
    }

    @Override
    public void offlineActivity(Object[] args) {
        log.info("下线首购优惠活动");
    }
}
