package org.zyf.javabasic.letcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 组合总和（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:26
 **/
public class CombinationSumSolution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candidates, target, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int target, int start) {
        // 如果目标值为0，则当前组合是一个有效解
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        // 如果目标值小于0，当前组合无效，返回
        if (target < 0) {
            return;
        }

        // 从当前索引开始遍历候选数组
        for (int i = start; i < candidates.length; i++) {
            // 选择当前元素
            current.add(candidates[i]);
            // 递归，注意传递 i 而不是 i + 1，因为可以重复使用当前元素
            backtrack(result, current, candidates, target - candidates[i], i);
            // 撤销选择
            current.remove(current.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSumSolution solution = new CombinationSumSolution();
        System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7)); // [[2,2,3],[7]]
        System.out.println(solution.combinationSum(new int[]{2, 3, 5}, 8)); // [[2,2,2,2],[2,3,3],[3,5]]
        System.out.println(solution.combinationSum(new int[]{2}, 1)); // []
    }
}
