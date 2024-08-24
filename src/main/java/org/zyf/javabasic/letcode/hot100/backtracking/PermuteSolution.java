package org.zyf.javabasic.letcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 全排列（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:12
 **/
public class PermuteSolution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result, new boolean[nums.length]);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> current, List<List<Integer>> result, boolean[] used) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // Skip used elements
            used[i] = true; // Mark as used
            current.add(nums[i]); // Add current number to the permutation
            backtrack(nums, current, result, used); // Recur
            current.remove(current.size() - 1); // Backtrack
            used[i] = false; // Unmark as used
        }
    }

    public static void main(String[] args) {
        PermuteSolution solution = new PermuteSolution();
        int[] nums1 = {1, 2, 3};
        System.out.println(solution.permute(nums1)); // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]

        int[] nums2 = {0, 1};
        System.out.println(solution.permute(nums2)); // [[0, 1], [1, 0]]

        int[] nums3 = {1};
        System.out.println(solution.permute(nums3)); // [[1]]
    }
}
