package org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.impl;

import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.AType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.BType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.Strategy;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.annotation.StrategyFor;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

/**
 * @program: zyfboot-javabasic
 * @description: 覆盖 A1 + {B1,B2} + C1 下的所有 D 情况；
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:28
 **/
@StrategyFor(a = {AType.A1}, b = {BType.B1, BType.B2}, c = {CType.C1})
@Component
public class StrategyAlpha implements Strategy {
    @Override
    public void execute(Combination combination) {
        System.out.println("StrategyAlpha handling: " + combination);
    }
}
