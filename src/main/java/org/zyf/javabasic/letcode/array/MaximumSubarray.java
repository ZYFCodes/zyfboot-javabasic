package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 经典的动态规划问题，给定一个整数数组nums，找到具有最大和的连续子数组（至少包含一个元素），返回其最大和。
 * 例如，给定数组 nums = [-2,1,-3,4,-1,2,1,-5,4]，连续子数组 [4,-1,2,1] 的和最大，为6，因此返回6。
 * <p>
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -10^5 <= nums[i] <= 10^5
 * @date 2023/4/2  18:16
 */
public class MaximumSubarray {

    /**
     * 解决这个问题时，我们需要找到一个子数组，其和最大。这个子数组可以是原始数组的一个连续子数组。因此，我们可以使用动态规划来解决此问题。
     * 假设 dp[i] 表示以第 i 个元素结尾的最大子数组和，则状态转移方程为：
     * dp[i] = max(dp[i-1] + nums[i], nums[i])
     * 其中，nums[i] 表示第 i 个元素的值。也就是说，如果前 i-1 个元素的子数组和加上 nums[i] 的值大于 nums[i]，
     * 那么 dp[i] 的值就为 dp[i-1] 加上 nums[i] 的值，否则 dp[i] 的值就为 nums[i]。
     * <p>
     * 最终的答案为 dp 数组中的最大值。时间复杂度为 O(n)，空间复杂度为 O(1)。
     * 另外，还有一种贪心算法，称为 Kadane 算法。该
     * 算法从左到右遍历数组，并在每个位置上计算当前的最大子数组和。
     * 时间复杂度同样为 O(n)，空间复杂度为 O(1)。
     */
    public int maxSubArray(int[] nums) {
        /*最大子序和的初始值设为整型最小值*/
        int maxSum = Integer.MIN_VALUE;
        /*当前子序和的初始值为0*/
        int curSum = 0;

        /*遍历数组*/
        for (int num : nums) {
            /*将当前数字加入当前子序和*/
            curSum += num;
            /*更新最大子序和*/
            maxSum = Math.max(maxSum, curSum);
            if (curSum < 0) {
                /*如果当前子序和为负数，重置当前子序和*/
                curSum = 0;
            }
        }
        /*返回最大子序和*/
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int maxSum = new MaximumSubarray().maxSubArray(nums);
        System.out.println(maxSum);
    }
}
