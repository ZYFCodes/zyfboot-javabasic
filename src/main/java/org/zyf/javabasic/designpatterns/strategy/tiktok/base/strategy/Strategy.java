package org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy;

import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

/**
 * @program: zyfboot-javabasic
 * @description: 策略接口
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:25
 **/
public interface Strategy {
    void execute(Combination combination);
}
