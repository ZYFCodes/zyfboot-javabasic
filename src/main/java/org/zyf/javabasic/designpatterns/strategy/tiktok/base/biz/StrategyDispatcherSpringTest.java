package org.zyf.javabasic.designpatterns.strategy.tiktok.base.biz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.dispatcher.StrategyDispatcher;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.AType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.BType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.DType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.Strategy;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.impl.DefaultStrategy;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.impl.StrategyAlpha;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.strategy.impl.StrategyC2D4;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @program: zyfboot-javabasic
 * @description: 业务测试（SpringBootTest）
 * @author: zhangyanfeng
 * @create: 2025-11-02 19:54
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyDispatcherSpringTest {

    @Autowired
    private StrategyDispatcher dispatcher;

    @Test
    public void testAlphaMatch() {
        Combination combo = new Combination(AType.A1, BType.B2, CType.C1, DType.D3);
        Strategy strategy = dispatcher.dispatch(combo);
        strategy.execute(combo);
        assertTrue(strategy instanceof StrategyAlpha);
    }

    @Test
    public void testC2D4Match() {
        Combination combo = new Combination(AType.A2, BType.B3, CType.C2, DType.D4);
        Strategy strategy = dispatcher.dispatch(combo);
        strategy.execute(combo);
        assertTrue(strategy instanceof StrategyC2D4);
    }

    @Test
    public void testDefaultMatch() {
        Combination combo = new Combination(AType.A2, BType.B3, CType.C1, DType.D2);
        Strategy strategy = dispatcher.dispatch(combo);
        strategy.execute(combo);
        assertTrue(strategy instanceof DefaultStrategy);
    }

    @Test
    public void testAll48Combinations() {
        List<String> results = new ArrayList<>();

        // 遍历所有组合
        for (AType a : AType.values()) {
            for (BType b : BType.values()) {
                for (CType c : CType.values()) {
                    for (DType d : DType.values()) {
                        // 构造组合对象
                        Combination combo = new org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination(a, b, c, d);
                        // 分派策略
                        Strategy strategy = dispatcher.dispatch(combo);
                        // 记录结果
                        String strategyName = strategy == null ? "null" : strategy.getClass().getSimpleName();
                        results.add(String.format("%s => %s", combo, strategyName));
                    }
                }
            }
        }

        // 输出结果
        results.forEach(System.out::println);

        // 简单验证默认策略是否存在
        boolean defaultUsed = results.stream().anyMatch(r -> r.endsWith(DefaultStrategy.class.getSimpleName()));
        assert defaultUsed : "至少有组合命中默认策略";
    }
}

