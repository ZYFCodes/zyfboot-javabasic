package org.zyf.javabasic.letcode.jd150.blacktracing;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 组合
 * @author: zhangyanfeng
 * @create: 2024-08-25 18:04
 **/
public class Combine {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();  // 存储所有组合
        List<Integer> combination = new ArrayList<>();    // 当前组合
        backtrack(result, combination, n, k, 1);  // 从数字1开始递归
        return result;
    }

    // 回溯函数
    private void backtrack(List<List<Integer>> result, List<Integer> combination, int n, int k, int start) {
        // 如果当前组合的大小等于k，添加到结果列表中
        if (combination.size() == k) {
            result.add(new ArrayList<>(combination));  // 添加一份当前组合的副本
            return;
        }

        // 从start开始尝试选择每个数字
        for (int i = start; i <= n; i++) {
            combination.add(i);  // 选择当前数字
            backtrack(result, combination, n, k, i + 1);  // 递归选择下一个数字
            combination.remove(combination.size() - 1);  // 撤销选择，回溯
        }
    }
}
