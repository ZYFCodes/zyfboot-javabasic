package org.zyf.javabasic.letcode.hot100.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 分割等和子集 （中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:29
 **/
public class PartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        // 计算数组的总和
        for (int num : nums) {
            sum += num;
        }

        // 如果总和是奇数，无法分割成两个和相等的子集
        if (sum % 2 != 0) {
            return false;
        }

        int target = sum / 2;
        // 初始化 dp 数组
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        // 动态规划更新 dp 数组
        for (int num : nums) {
            // 从后向前更新 dp 数组，防止重复使用元素
            for (int j = target; j >= num; j--) {
                dp[j] = dp[j] || dp[j - num];
            }
        }

        // 返回是否可以找到和为 target 的子集
        return dp[target];
    }

    public static void main(String[] args) {
        PartitionEqualSubsetSum ps = new PartitionEqualSubsetSum();

        // 测试用例1
        int[] nums1 = {1, 5, 11, 5};
        System.out.println("测试用例1结果: " + ps.canPartition(nums1)); // 输出：true

        // 测试用例2
        int[] nums2 = {1, 2, 3, 5};
        System.out.println("测试用例2结果: " + ps.canPartition(nums2)); // 输出：false
    }
}
