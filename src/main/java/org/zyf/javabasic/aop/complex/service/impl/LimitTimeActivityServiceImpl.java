package org.zyf.javabasic.aop.complex.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.entity.ActivityDO;
import org.zyf.javabasic.aop.complex.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.complex.service.ActivityService;

/**
 * @author yanfengzhang
 * @description 限时活动相关操作
 * @date 2020/11/2  12:06
 */
@Component
@Slf4j
public class LimitTimeActivityServiceImpl implements ActivityService<ActivityDO>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register("2", this);
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
