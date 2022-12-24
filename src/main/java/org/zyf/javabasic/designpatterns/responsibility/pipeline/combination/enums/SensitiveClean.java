package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/23  18:05
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveClean {
    /**
     * 清洗编码
     */
    int cleanCode();
}
