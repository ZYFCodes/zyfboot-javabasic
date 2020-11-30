package org.zyf.javabasic.aop.complex.factory;

import org.springframework.util.Assert;
import org.zyf.javabasic.aop.complex.service.activity.ActivityService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yanfengzhang
 * @description 活动相关类型业务工厂类
 * @date 2020/10/30  17:59
 */
public class ActivityServiceStrategyFactory {
    private static Map<String, ActivityService> activityServices = new ConcurrentHashMap<String, ActivityService>();

    public static ActivityService getActivityBytype(String type) {
        return activityServices.get(type);
    }

    public static void register(String activityType, ActivityService activityService) {
        Assert.notNull(activityType, "activityType can't be null");
        activityServices.put(activityType, activityService);
    }
}
