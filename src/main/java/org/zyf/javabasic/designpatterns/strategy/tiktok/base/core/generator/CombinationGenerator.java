package org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.generator;

import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.AType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.BType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.CType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.enums.DType;
import org.zyf.javabasic.designpatterns.strategy.tiktok.base.types.Combination;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 组合生成工具：简单实现（四重循环）
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:42
 **/
public class CombinationGenerator {
    public static List<Combination> allCombinations() {
        List<Combination> list = new ArrayList<>();
        for (AType a : AType.values()) {
            for (BType b : BType.values()) {
                for (CType c : CType.values()) {
                    for (DType d : DType.values()) {
                        list.add(new Combination(a, b, c, d));
                    }
                }
            }
        }
        return list;
    }
}
