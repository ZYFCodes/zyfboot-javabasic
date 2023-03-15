package org.zyf.javabasic.dynamicbizvalidator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanfengzhang
 * @description 动态配置组信息
 * @date 2023/3/8  23:34
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicConfigGroup {
    /**
     * 动态配置内容集合
     */
    DynamicConfig[] config() default {};
}
