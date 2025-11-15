package org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.validator.base;

import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.dispatcher.StrategyDispatcher;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.generator.GenericCombinationGenerator;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.AType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.BType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.DType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 通用维度（递归笛卡尔积校验）
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:58
 **/
public class GenericStrategyValidator {

    private final StrategyDispatcher dispatcher;

    public GenericStrategyValidator(StrategyDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * 通用启动校验（支持任意维度）
     */
    public void validate() {
        // 1. 准备维度集合
        List<Enum<?>[]> dims = Arrays.asList(
                AType.values(), BType.values(), CType.values(), DType.values()
        );

        // 2. 使用通用笛卡尔积生成所有组合
        List<List<Enum<?>>> allCombos = GenericCombinationGenerator.cartesianProduct(dims);

        // 3. 执行校验
        List<Combination> missing = new ArrayList<>();
        for (List<Enum<?>> row : allCombos) {
            Combination combo = new Combination(
                    (AType) row.get(0),
                    (BType) row.get(1),
                    (CType) row.get(2),
                    (DType) row.get(3)
            );
            try {
                dispatcher.dispatch(combo);
            } catch (Exception e) {
                missing.add(combo);
            }
        }

        // 4. 输出结果
        if (missing.isEmpty()) {
            System.out.println("✅ 策略覆盖完整，共校验组合：" + allCombos.size());
        } else {
            System.out.printf("⚠️ 策略缺失：共 %d 个未覆盖组合%n", missing.size());
            missing.forEach(System.out::println);
        }
    }
}

