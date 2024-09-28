package org.zyf.javabasic.dynamicbizvalidator.annotation;

import java.lang.annotation.*;

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
