package org.zyf.javabasic.aop.complex.service.activity.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.complex.entity.ActivityDo;
import org.zyf.javabasic.aop.complex.entity.dto.BuyForGetActivityDto;
import org.zyf.javabasic.aop.complex.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.complex.service.activity.ActivityService;

/**
 * @author yanfengzhang
 * @description 买赠活动相关操作
 * @date 2020/11/2  12:08
 */
@Component
@Slf4j
public class BuyForGetActivityServiceImpl implements ActivityService<BuyForGetActivityDto>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register(ActivityBizConstants.ActivityBizType.BUY_FOR_GET, this);
    }

    @Override
    public BuyForGetActivityDto queryActivityDetail(Object[] args) {
        /*按传入信息更新或创建当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        BuyForGetActivityDto buyForGetActivityDto = new BuyForGetActivityDto();
        buyForGetActivityDto.setActivityType(3);
        buyForGetActivityDto.setActivityName("买赠活动");

        log.info("查询买赠活动");

        return buyForGetActivityDto;
    }

    @Override
    public BuyForGetActivityDto createOrUpdateActivityDetail(Object[] args) {
        /*按传入信息查当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        BuyForGetActivityDto buyForGetActivityDto = new BuyForGetActivityDto();
        buyForGetActivityDto.setActivityType(3);
        buyForGetActivityDto.setActivityName("买赠活动");

        log.info("创建或更新买赠活动");

        return buyForGetActivityDto;
    }

    @Override
    public boolean deleteActivityDetail(Object[] args) {
        log.info("删除买赠活动");
    }

    @Override
    public boolean checkActivityInfo(Object[] args) {
        log.info("校验买赠活动信息是否有误");
        return false;
    }

    @Override
    public boolean onlineActivity(Object[] args) {
        log.info("上线买赠活动");
    }

    @Override
    public boolean offlineActivity(Object[] args) {
        log.info("下线买赠活动");
    }
}
