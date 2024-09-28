package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums;

import java.lang.annotation.*;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/23  18:06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SensitiveValidate {
    /**
     * 生效编码
     */
    int validateCode();
}
