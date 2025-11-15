package org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.impl;

import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.Strategy;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.annotation.StrategyFor;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

/**
 * @program: zyfboot-javabasic
 * @description: 不限制任何条件，作为兜底策略。
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:32
 **/
@StrategyFor
@Component
public class DefaultStrategy implements Strategy {
    @Override
    public void execute(Combination combination) {
        System.out.println("DefaultStrategy handling: " + combination);
    }
}
