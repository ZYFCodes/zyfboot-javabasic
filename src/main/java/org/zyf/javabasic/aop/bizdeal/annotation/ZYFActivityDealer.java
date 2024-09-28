package org.zyf.javabasic.aop.bizdeal.annotation;

import org.zyf.javabasic.aop.bizdeal.constants.ActivityBizMethod;

import java.lang.annotation.*;

/**
 * @author yanfengzhang
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ZYFActivityDealer {

    /**
     * 活动的具体类型
     */
    String activityType() default "";

    /**
     * 活动触发的业务方法
     */
    ActivityBizMethod activityMethod() default ActivityBizMethod.DEFAULT;
}
