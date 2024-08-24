package org.zyf.javabasic.letcode.hot100.twopoint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 三数之和
 * @author: zhangyanfeng
 * @create: 2024-08-21 20:59
 **/
public class ThreeSumSolution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 排序数组

        for (int i = 0; i < nums.length - 2; i++) {
            // 跳过重复的元素
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复的 left 元素
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过重复的 right 元素
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        ThreeSumSolution solution = new ThreeSumSolution();

        int[] nums1 = {-1,0,1,2,-1,-4};
        System.out.println(solution.threeSum(nums1)); // 输出: [[-1,-1,2],[-1,0,1]]

        int[] nums2 = {0,1,1};
        System.out.println(solution.threeSum(nums2)); // 输出: []

        int[] nums3 = {0,0,0};
        System.out.println(solution.threeSum(nums3)); // 输出: [[0,0,0]]
    }
}
