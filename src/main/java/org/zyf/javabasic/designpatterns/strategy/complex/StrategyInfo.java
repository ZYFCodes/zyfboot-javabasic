package org.zyf.javabasic.designpatterns.strategy.complex;

import lombok.Builder;
import lombok.Data;
import org.zyf.javabasic.designpatterns.strategy.combination.MemberTypeEnum;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderPayMethodEnum;
import org.zyf.javabasic.designpatterns.strategy.combination.OrderSourceEnum;

import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 策略的基本组合体
 * @date 2022/3/11  23:59
 */
@Data
@Builder
public class StrategyInfo {
    /**
     * 指明来源
     */
    OrderSourceEnum source;
    /**
     * 支付方式
     */
    OrderPayMethodEnum payMethod;
    /**
     * 会员类型
     */
    MemberTypeEnum memberType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StrategyInfo)) {
            return false;
        }
        StrategyInfo that = (StrategyInfo) o;
        return source == that.source &&
                payMethod == that.payMethod &&
                memberType == that.memberType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, payMethod, memberType);
    }
}
