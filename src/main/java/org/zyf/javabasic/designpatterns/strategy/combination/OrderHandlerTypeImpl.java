package org.zyf.javabasic.designpatterns.strategy.combination;

import java.lang.annotation.Annotation;

/**
 * @author yanfengzhang
 * @description 实现注解语法糖的实现类
 * @date 2022/3/8  23:33
 */
public class OrderHandlerTypeImpl implements OrderHandlerType {

    private OrderSourceEnum source;
    private OrderPayMethodEnum payMethod;
    private MemberTypeEnum memberType;

    OrderHandlerTypeImpl(OrderSourceEnum source, OrderPayMethodEnum payMethod, MemberTypeEnum memberType) {
        this.source = source;
        this.payMethod = payMethod;
        this.memberType = memberType;
    }

    @Override
    public OrderSourceEnum source() {
        return source;
    }

    @Override
    public OrderPayMethodEnum payMethod() {
        return payMethod;
    }

    @Override
    public MemberTypeEnum memberType() {
        return memberType;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return OrderHandlerType.class;
    }

    @Override
    public int hashCode() {
        int hashCode = 0;
        hashCode += (127 * "source".hashCode()) ^ source.hashCode();
        hashCode += (127 * "payMethod".hashCode()) ^ payMethod.hashCode();
        hashCode += (127 * "memberType".hashCode()) ^ memberType.hashCode();
        return hashCode;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof OrderHandlerType)) {
            return false;
        }
        OrderHandlerType other = (OrderHandlerType) obj;
        return source == other.source()
                && payMethod == other.payMethod()
                && memberType == other.memberType();
    }
}
