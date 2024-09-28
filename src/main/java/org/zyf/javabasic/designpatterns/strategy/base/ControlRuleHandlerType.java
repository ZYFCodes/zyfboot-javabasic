package org.zyf.javabasic.designpatterns.strategy.base;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author yanfengzhang
 * @description 基本控制规则注解
 * @date 2022/3/3  23:02
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface ControlRuleHandlerType {
    int controlRuleType();
}
