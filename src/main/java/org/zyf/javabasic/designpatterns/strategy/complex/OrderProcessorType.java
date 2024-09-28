package org.zyf.javabasic.designpatterns.strategy.complex;

import org.springframework.stereotype.Service;
import org.zyf.javabasic.designpatterns.strategy.combination.MemberTypeEnum;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderPayMethodEnum;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderSourceEnum;

import java.lang.annotation.*;

/**
 * @author yanfengzhang
 * @description 订单处理基本注解
 * @date 2022/3/11  23:41
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface OrderProcessorType {
    /**
     * 指明来源
     */
    OrderSourceEnum source();

    /**
     * 支付方式
     */
    OrderPayMethodEnum[] payMethod();

    /**
     * 会员类型
     */
    MemberTypeEnum memberType();
}
