package org.zyf.javabasic.designpatterns.strategy.combination;

import org.springframework.stereotype.Service;

import java.lang.annotation.*;

/**
 * @author yanfengzhang
 * @description 表示订单处理类型
 * @date 2022/3/8  23:27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface OrderHandlerType {
    /**
     * 指明来源
     */
    OrderSourceEnum source();

    /**
     * 支付方式
     */
    OrderPayMethodEnum payMethod();

    /**
     * 会员类型
     */
    MemberTypeEnum memberType();
}
