package org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 通用实现（支持任意维度）— 递归笛卡尔积
 * @author: zhangyanfeng
 * @create: 2025-11-02 18:45
 **/
public class GenericCombinationGenerator {
    public static List<List<Enum<?>>> cartesianProduct(List<? extends Enum<?>[]> lists) {
        List<List<Enum<?>>> result = new ArrayList<>();
        if (lists == null || lists.isEmpty()) {
            return result;
        }
        backtrack(lists, 0, new ArrayList<Enum<?>>(), result);
        return result;
    }


    private static void backtrack(List<? extends Enum<?>[]> lists, int idx, List<Enum<?>> cur, List<List<Enum<?>>> result) {
        if (idx == lists.size()) {
            result.add(new ArrayList<>(cur));
            return;
        }
        Enum<?>[] arr = lists.get(idx);
        for (Enum<?> e : arr) {
            cur.add(e);
            backtrack(lists, idx + 1, cur, result);
            cur.remove(cur.size() - 1);
        }
    }
}
