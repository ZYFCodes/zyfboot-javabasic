package org.zyf.javabasic.aop.bizdeal.service.activity.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.bizdeal.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.bizdeal.entity.ActivityDo;
import org.zyf.javabasic.aop.bizdeal.entity.dto.PriceCutActivityDto;
import org.zyf.javabasic.aop.bizdeal.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.bizdeal.service.activity.ActivityService;

/**
 * @author yanfengzhang
 * @description 降价活动相关方法整理
 * @date 2020/10/30  20:59
 */
@Component
@Slf4j
public class PriceCutActivityServiceImpl implements ActivityService<PriceCutActivityDto>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register(ActivityBizConstants.ActivityBizType.PRICE_CUT, this);
    }

    @Override
    public PriceCutActivityDto queryActivityDetail(Object[] args) {
        /*按传入信息查当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        PriceCutActivityDto priceCutActivityDto =PriceCutActivityDto.builder().activityType(1).activityName("降价活动").build();

        log.info("查询降价活动:{}",priceCutActivityDto);

        return priceCutActivityDto;
    }

    @Override
    public PriceCutActivityDto createOrUpdateActivityDetail(Object[] args) {
        /*按传入信息创建或更新当前数据库对应活动信息 只是测试*/
        ActivityDo activityDo = new ActivityDo();
        activityDo.setActivityName("数据库中的活动信息");

        /*转换为对应活动信息*/
        PriceCutActivityDto priceCutActivityDto = PriceCutActivityDto.builder().activityType(1).activityName("降价活动").build();

        log.info("创建或更新降价活动:{}",priceCutActivityDto);

        return priceCutActivityDto;
    }

    @Override
    public void deleteActivityDetail(Object[] args) {
        log.info("删除降价活动");
    }

    @Override
    public boolean checkActivityInfo(Object[] args) {
        log.info("校验降价活动信息是否有误");
        return false;
    }

    @Override
    public void onlineActivity(Object[] args) {
        log.info("上线降价活动");
    }

    @Override
    public void offlineActivity(Object[] args) {
        log.info("下线降价活动");
    }
}
