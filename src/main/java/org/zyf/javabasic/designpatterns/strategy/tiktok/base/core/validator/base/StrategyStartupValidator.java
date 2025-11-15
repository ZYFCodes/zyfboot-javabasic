package org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.validator.base;

import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.dispatcher.StrategyDispatcher;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.AType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.BType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.DType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 固定维度（四重循环校验）
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:55
 **/
public class StrategyStartupValidator {

    private final StrategyDispatcher dispatcher;

    public StrategyStartupValidator(StrategyDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * 启动时执行策略覆盖校验（固定维度）
     */
    public void validate() {
        List<Combination> missing = new ArrayList<>();

        for (AType a : AType.values()) {
            for (BType b : BType.values()) {
                for (CType c : CType.values()) {
                    for (DType d : DType.values()) {
                        Combination combo = new Combination(a, b, c, d);
                        try {
                            dispatcher.dispatch(combo);
                        } catch (Exception e) {
                            missing.add(combo);
                        }
                    }
                }
            }
        }

        if (missing.isEmpty()) {
            System.out.println("✅ 策略覆盖完整，所有组合均匹配成功。");
        } else {
            System.out.println("⚠️ 策略缺失，以下组合未匹配到策略：");
            missing.forEach(System.out::println);
        }
    }
}

