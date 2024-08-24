package org.zyf.javabasic.letcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 子集（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:17
 **/
public class SubsetsSolution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        // 将当前子集加入结果集中
        result.add(new ArrayList<>(current));

        // 从当前位置开始遍历所有元素
        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]); // 选择当前元素
            backtrack(nums, i + 1, current, result); // 递归处理下一个元素
            current.remove(current.size() - 1); // 撤销选择（即回溯）
        }
    }

    public static void main(String[] args) {
        SubsetsSolution solution = new SubsetsSolution();
        int[] nums1 = {1, 2, 3};
        System.out.println(solution.subsets(nums1)); // [[], [1], [1, 2], [1, 2, 3], [1, 3], [2], [2, 3], [3]]

        int[] nums2 = {0};
        System.out.println(solution.subsets(nums2)); // [[], [0]]
    }
}
