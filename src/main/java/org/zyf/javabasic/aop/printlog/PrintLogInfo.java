package org.zyf.javabasic.aop.printlog;

import java.lang.annotation.*;

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
