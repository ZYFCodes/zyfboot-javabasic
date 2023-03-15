package org.zyf.javabasic.dynamicbizvalidator.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanfengzhang
 * @description 校验项内容校验结果
 * @date 2023/3/8  23:35
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VerifyMessage {
    /**
     * 校验内容结论
     */
    String value() default "";

    /**
     * 覆盖信息
     */
    boolean override() default false;
}
