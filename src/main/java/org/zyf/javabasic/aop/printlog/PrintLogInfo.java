package org.zyf.javabasic.aop.printlog;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yanfengzhang
 * @description 方法日志打印信息处理
 * @date 2022/6/13  23:54
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PrintLogInfo {
    /**
     * 自定义日志描述信息文案
     */
    String description() default "";
}
