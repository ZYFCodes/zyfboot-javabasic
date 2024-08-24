package org.zyf.javabasic.letcode.hot100.ordinaryarray;

/**
 * @program: zyfboot-javabasic
 * @description: 最大子数组和
 * @author: zhangyanfeng
 * @create: 2024-08-21 22:04
 **/
public class MaxSubArraySolution {
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        // 初始化动态规划变量
        int globalMax = nums[0];
        int currentMax = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            globalMax = Math.max(globalMax, currentMax);
        }

        return globalMax;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        return maxSubArray(nums, 0, nums.length - 1);
    }

    private int maxSubArray(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }

        int mid = (left + right) / 2;

        // 计算左半部分、右半部分以及跨越中间的最大子数组和
        int leftMax = maxSubArray(nums, left, mid);
        int rightMax = maxSubArray(nums, mid + 1, right);
        int crossMax = maxCrossingSubArray(nums, left, mid, right);

        // 返回三个部分中的最大值
        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private int maxCrossingSubArray(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;

        // 计算跨越中间的最大子数组和（从中间向左）
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;

        // 计算跨越中间的最大子数组和（从中间向右）
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        // 返回跨越中间的最大子数组和
        return leftSum + rightSum;
    }

    public static void main(String[] args) {
        MaxSubArraySolution solution = new MaxSubArraySolution();

        // 测试用例 1
        int[] nums1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(solution.maxSubArray1(nums1));  // 输出: 6

        // 测试用例 2
        int[] nums2 = {1};
        System.out.println(solution.maxSubArray1(nums2));  // 输出: 1

        // 测试用例 3
        int[] nums3 = {5, 4, -1, 7, 8};
        System.out.println(solution.maxSubArray1(nums3));  // 输出: 23
    }
}
