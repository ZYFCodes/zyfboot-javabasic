package org.zyf.javabasic.designpatterns.responsibility.pipeline.distribution.enums;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/26  19:46
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CommonBiz {
    /**
     * 业务描述
     */
    int bizDesc();

    /**
     * 业务编码,具体业务链流程功能
     */
    int bizCode();
}
