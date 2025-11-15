package org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.validator.impl;

import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.dispatcher.StrategyDispatcher;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.generator.GenericCombinationGenerator;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.validator.StrategyValidationService;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.AType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.BType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.DType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 默认实现
 * @author: zhangyanfeng
 * @create: 2025-11-02 19:02
 **/
public class DefaultStrategyValidationService implements StrategyValidationService {

    @Override
    public void validateCoverage(StrategyDispatcher dispatcher, List<? extends Enum<?>[]> dimensions) {
        List<List<Enum<?>>> combos = GenericCombinationGenerator.cartesianProduct(dimensions);
        List<List<Enum<?>>> missing = new ArrayList<>();

        for (List<Enum<?>> row : combos) {
            try {
                dispatcher.dispatch(new Combination(
                        (AType) row.get(0),
                        (BType) row.get(1),
                        (CType) row.get(2),
                        (DType) row.get(3)
                ));
            } catch (Exception e) {
                missing.add(row);
            }
        }

        if (missing.isEmpty()) {
            System.out.printf("✅ 所有策略覆盖完整，共 %d 组合。%n", combos.size());
        } else {
            System.out.printf("⚠️ 缺失策略 %d 个：%n", missing.size());
            missing.forEach(System.out::println);
        }
    }
}

