package org.zyf.javabasic.dynamicbizvalidator.annotation;

import java.lang.annotation.*;

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
