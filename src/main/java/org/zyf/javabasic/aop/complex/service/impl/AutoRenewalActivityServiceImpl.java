package org.zyf.javabasic.aop.complex.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.entity.ActivityDO;
import org.zyf.javabasic.aop.complex.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.complex.service.ActivityService;

/**
 * @author yanfengzhang
 * @description 自动续费活动相关方法整理
 * @date 2020/11/2  12:13
 */
@Component
@Slf4j
public class AutoRenewalActivityServiceImpl implements ActivityService<ActivityDO>, InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        ActivityServiceStrategyFactory.register("5", this);
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
