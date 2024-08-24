package org.zyf.javabasic.letcode.featured75.slidingwindow;

/**
 * @program: zyfboot-javabasic
 * @description: 子数组最大平均数 I
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:34
 **/
public class MaxAverage {
    public double findMaxAverage(int[] nums, int k) {
        // 计算初始窗口的和
        double windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        // 初始化最大平均数为初始窗口的平均数
        double maxAverage = windowSum / k;

        // 滑动窗口，计算每个窗口的和并更新最大平均数
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k]; // 滑动窗口的更新
            double currentAverage = windowSum / k; // 当前窗口的平均数
            maxAverage = Math.max(maxAverage, currentAverage); // 更新最大平均数
        }

        return maxAverage;
    }

    public static void main(String[] args) {
        MaxAverage solution = new MaxAverage();

        // 测试用例 1
        int[] nums1 = {1, 12, -5, -6, 50, 3};
        int k1 = 4;
        System.out.println("Test Case 1: " + (Math.abs(solution.findMaxAverage(nums1, k1) - 12.75) < 1e-5 ? "Passed" : "Failed"));

        // 测试用例 2
        int[] nums2 = {5};
        int k2 = 1;
        System.out.println("Test Case 2: " + (Math.abs(solution.findMaxAverage(nums2, k2) - 5.0) < 1e-5 ? "Passed" : "Failed"));

        // 测试用例 3
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int k3 = 3;
        System.out.println("Test Case 3: " + (Math.abs(solution.findMaxAverage(nums3, k3) - 8.0) < 1e-5 ? "Passed" : "Failed"));

        // 测试用例 4
        int[] nums4 = {1, 1, 1, 1, 1, 1, 1, 1, 1};
        int k4 = 5;
        System.out.println("Test Case 4: " + (Math.abs(solution.findMaxAverage(nums4, k4) - 1.0) < 1e-5 ? "Passed" : "Failed"));

        // 测试用例 5
        int[] nums5 = {-1, -2, -3, -4, -5, -6, -7, -8, -9, -10};
        int k5 = 4;
        System.out.println("Test Case 5: " + (Math.abs(solution.findMaxAverage(nums5, k5) - (-5.5)) < 1e-5 ? "Passed" : "Failed"));
    }
}
