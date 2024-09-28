package org.zyf.javabasic.dynamicbizvalidator.annotation;

import org.zyf.javabasic.dynamicbizvalidator.enums.DynamicConfigType;

import java.lang.annotation.*;

/**
 * @author yanfengzhang
 * @description 动态配置内容
 * @date 2023/3/8  23:33
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicConfig {
    /**
     * 配置名称信息
     */
    String configName() default "";

    /**
     * 配置值信息
     */
    String value() default "";

    /**
     * 配置值默认信息
     */
    String defaultConfigValue() default "";

    /**
     * 是否覆盖
     */
    boolean override() default false;

    /**
     * 配置类型要求
     */
    DynamicConfigType configType() default DynamicConfigType.NOT_BLANK;
}
