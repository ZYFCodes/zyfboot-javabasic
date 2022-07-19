package org.zyf.javabasic.aop.complex.service.activity.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.complex.entity.ActivityDo;
import org.zyf.javabasic.aop.complex.entity.dto.LimitTimeActivityDto;
import org.zyf.javabasic.aop.complex.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.complex.service.activity.ActivityService;

/**
 * @author yanfengzhang
 * @description 限时活动相关操作
 * @date 2020/11/2  12:06
 */
@Component
@Slf4j
public class LimitTimeActivityServiceImpl implements ActivityService<LimitTimeActivityDto>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register(ActivityBizConstants.ActivityBizType.LIMIT_TIME, this);
    }

    @Override
    public LimitTimeActivityDto queryActivityDetail(Object[] args) {
        /*按传入信息查当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        LimitTimeActivityDto limitTimeActivityDto = new LimitTimeActivityDto();
        limitTimeActivityDto.setActivityType(2);
        limitTimeActivityDto.setActivityName("限时活动");

        log.info("查询限时活动");

        return limitTimeActivityDto;
    }

    @Override
    public LimitTimeActivityDto createOrUpdateActivityDetail(Object[] args) {
        /*按传入信息创建或更新当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        LimitTimeActivityDto limitTimeActivityDto = new LimitTimeActivityDto();
        limitTimeActivityDto.setActivityType(2);
        limitTimeActivityDto.setActivityName("限时活动");

        log.info("创建或更新限时活动");

        return limitTimeActivityDto;
    }

    @Override
    public boolean deleteActivityDetail(Object[] args) {
        log.info("删除限时活动");
    }

    @Override
    public boolean checkActivityInfo(Object[] args) {
        log.info("校验限时活动信息是否有误");
        return false;
    }

    @Override
    public boolean onlineActivity(Object[] args) {
        log.info("上线限时活动");
    }

    @Override
    public boolean offlineActivity(Object[] args) {
        log.info("下线限时活动");
    }
}
