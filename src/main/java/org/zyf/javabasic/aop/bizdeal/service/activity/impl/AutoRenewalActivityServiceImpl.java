package org.zyf.javabasic.aop.bizdeal.service.activity.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.bizdeal.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.bizdeal.entity.ActivityDo;
import org.zyf.javabasic.aop.bizdeal.entity.dto.AutoRenewalActivityDto;
import org.zyf.javabasic.aop.bizdeal.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.bizdeal.service.activity.ActivityService;

/**
 * @author yanfengzhang
 * @description 自动续费活动相关方法整理
 * @date 2020/11/2  12:13
 */
@Component
@Slf4j
public class AutoRenewalActivityServiceImpl implements ActivityService<AutoRenewalActivityDto>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register(ActivityBizConstants.ActivityBizType.AUTO_RENEMAL, this);
    }

    @Override
    public AutoRenewalActivityDto queryActivityDetail(Object[] args) {
        /*按传入信息查当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        AutoRenewalActivityDto autoRenewalActivityDto = new AutoRenewalActivityDto();
        autoRenewalActivityDto.setActivityType(5);
        autoRenewalActivityDto.setActivityName("自动续费活动");

        log.info("查询自动续费活动:{}",autoRenewalActivityDto);

        return autoRenewalActivityDto;
    }

    @Override
    public AutoRenewalActivityDto createOrUpdateActivityDetail(Object[] args) {
        /*按传入信息更新或创建当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        AutoRenewalActivityDto autoRenewalActivityDto = new AutoRenewalActivityDto();
        autoRenewalActivityDto.setActivityType(5);
        autoRenewalActivityDto.setActivityName("自动续费活动");

        log.info("创建或更新自动续费活动:{}",autoRenewalActivityDto);

        return autoRenewalActivityDto;
    }

    @Override
    public void deleteActivityDetail(Object[] args) {
        log.info("删除自动续费活动");
    }

    @Override
    public boolean checkActivityInfo(Object[] args) {
        log.info("校验自动续费活动信息是否有误");
        return false;
    }

    @Override
    public void onlineActivity(Object[] args) {
        log.info("上线自动续费活动");
    }

    @Override
    public void offlineActivity(Object[] args) {
        log.info("下线自动续费活动");
    }

    public String test() {
        return "sss";
    }

}
