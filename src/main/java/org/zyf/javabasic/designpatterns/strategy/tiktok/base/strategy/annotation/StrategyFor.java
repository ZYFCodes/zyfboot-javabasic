package org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.annotation;

import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.AType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.BType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.DType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: zyfboot-javabasic
 * @description: 注解策略接口
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:23
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface StrategyFor {
    AType[] a() default {};

    BType[] b() default {};

    CType[] c() default {};

    DType[] d() default {};
}
