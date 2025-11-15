package org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.impl;

import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.DType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.Strategy;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.annotation.StrategyFor;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

/**
 * @program: zyfboot-javabasic
 * @description: StrategyC2D4：专门处理 C2 且 D4 的组合，无视 A/B；
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:32
 **/
@StrategyFor(c = {CType.C2}, d = {DType.D4})
@Component
public class StrategyC2D4 implements Strategy {
    @Override
    public void execute(Combination combination) {
        System.out.println("StrategyC2D4 handling: " + combination);
    }
}
