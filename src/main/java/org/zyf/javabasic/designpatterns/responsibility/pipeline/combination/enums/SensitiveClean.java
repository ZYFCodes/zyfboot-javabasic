package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums;

import java.lang.annotation.*;

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
