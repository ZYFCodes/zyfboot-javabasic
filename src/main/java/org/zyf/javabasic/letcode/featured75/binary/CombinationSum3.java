package org.zyf.javabasic.letcode.featured75.binary;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 组合总和 III
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:45
 **/
public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int k, int n, int start) {
        // 终止条件
        if (tempList.size() == k && n == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        if (tempList.size() > k || n < 0) {
            return;
        }

        // 递归生成组合
        for (int i = start; i <= 9; i++) {
            tempList.add(i);
            backtrack(result, tempList, k, n - i, i + 1); // 递归选择下一个数字
            tempList.remove(tempList.size() - 1); // 回溯
        }
    }

    public static void main(String[] args) {
        CombinationSum3 solution = new CombinationSum3();

        // 测试用例 1
        System.out.println(solution.combinationSum3(3, 7)); // 预期输出: [[1, 2, 4]]

        // 测试用例 2
        System.out.println(solution.combinationSum3(3, 9)); // 预期输出: [[1, 2, 6], [1, 3, 5], [2, 3, 4]]

        // 测试用例 3
        System.out.println(solution.combinationSum3(4, 1)); // 预期输出: []

        // 测试用例 4：边界条件
        System.out.println(solution.combinationSum3(2, 5)); // 预期输出: [[1, 4], [2, 3]]

        // 测试用例 5：较大输入
        System.out.println(solution.combinationSum3(5, 15)); // 预期输出: [[1, 2, 3, 4, 5]]
    }
}
