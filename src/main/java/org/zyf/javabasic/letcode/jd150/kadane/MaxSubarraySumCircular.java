package org.zyf.javabasic.letcode.jd150.kadane;

/**
 * @program: zyfboot-javabasic
 * @description: 环形子数组的最大和
 * @author: zhangyanfeng
 * @create: 2024-08-25 18:29
 **/
public class MaxSubarraySumCircular {
    public int maxSubarraySumCircular(int[] nums) {
        // 计算数组的总和
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        // 求普通最大子数组和
        int maxSum = kadane(nums, true);

        // 求最小子数组和
        int minSum = kadane(nums, false);

        // 最大环形子数组和
        int maxCircularSum = totalSum - minSum;

        // 如果数组中所有元素都为负数，则 maxCircularSum 会是 0，这种情况需要特殊处理
        if (maxCircularSum == 0) {
            return maxSum;
        }

        // 返回最大值
        return Math.max(maxSum, maxCircularSum);
    }

    // Kadane 算法变种，用于计算最大子数组和或最小子数组和
    private int kadane(int[] nums, boolean findMax) {
        int currentSum = nums[0];
        int extremumSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (findMax) {
                currentSum = Math.max(nums[i], currentSum + nums[i]);
                extremumSum = Math.max(extremumSum, currentSum);
            } else {
                currentSum = Math.min(nums[i], currentSum + nums[i]);
                extremumSum = Math.min(extremumSum, currentSum);
            }
        }

        return extremumSum;
    }
}
