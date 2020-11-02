package org.zyf.javabasic.aop.complex.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.entity.ActivityDO;
import org.zyf.javabasic.aop.complex.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.complex.service.ActivityService;

/**
 * @author yanfengzhang
 * @description 降价活动相关方法整理
 * @date 2020/10/30  20:59
 */
@Component
@Slf4j
public class PriceCutActivityServiceImpl implements ActivityService<ActivityDO>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register("1", this);
    }

    @Override
    public ActivityDO queryActivityDetail(Object[] args) {
        return null;
    }

    @Override
    public ActivityDO createOrUpdateActivityDetail(Object[] args) {
        return null;
    }

    @Override
    public void deleteActivityDetail(Object[] args) {

    }

    @Override
    public boolean checkActivityInfo(Object[] args) {
        return false;
    }

    @Override
    public void onlineActivity(Object[] args) {

    }

    @Override
    public void offlineActivity(Object[] args) {

    }
}
