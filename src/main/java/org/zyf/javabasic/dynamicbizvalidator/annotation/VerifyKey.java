package org.zyf.javabasic.dynamicbizvalidator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanfengzhang
 * @description 校验项内容
 * @date 2023/3/8  23:35
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerifyKey {
    /**
     * 校验项内容
     */
    String value() default "";
}
