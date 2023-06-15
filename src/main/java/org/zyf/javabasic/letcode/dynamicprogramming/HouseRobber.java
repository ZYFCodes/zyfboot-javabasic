package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 你是一个专业的小偷，计划偷窃一条街道上的房屋。
 * 每间房屋都存放着特定金额的财物，但相邻的房屋装有相互连通的防盗系统，
 * 如果同时选中了相邻的两间房屋进行偷窃，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，你需要计算在不触发警报的情况下，能够偷窃到的最高金额。
 * 示例 1:输入: [1,2,3,1]   输出: 4
 * 解释: 偷窃第1间和第3间房屋，金额总计为 1 + 3 = 4。
 * 示例 2:输入: [2,7,9,3,1]   输出: 12
 * 解释: 偷窃第1间、第3间和第5间房屋，金额总计为 2 + 9 + 1 = 12。
 * @date 2023/5/27  23:17
 */
public class HouseRobber {
    /**
     * 这道题可以使用动态规划来解决。
     * 我们定义一个数组 dp，其中 dp[i] 表示偷窃到第 i 间房屋时能够获得的最高金额。
     * 根据题目要求，我们不能选择相邻的房屋进行偷窃，因此对于第 i 间房屋，我们有两种选择：
     * 1. 偷窃第 i 间房屋：那么最高金额为 dp[i-2] + nums[i]，即第 i-2 间房屋的最高金额加上当前房屋的金额。
     * 2. 不偷窃第 i 间房屋：那么最高金额为 dp[i-1]，即前一间房屋的最高金额。
     * 综上，我们可以得到状态转移方程：
     * dp[i] = max(dp[i-2] + nums[i], dp[i-1])
     * 初始条件是 dp[0] = nums[0] 和 dp[1] = max(nums[0], nums[1])。
     * 最终要求的答案是 dp[n-1]，其中 n 是房屋的数量。
     * <p>
     * 该算法的时间复杂度为 O(n)，空间复杂度为 O(n)，其中 n 是房屋的数量。
     * 通过动态规划的思想，我们可以高效地计算出能够偷窃到的最高金额。
     */
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // 创建一个数组来保存偷窃到每个房屋时能够获得的最高金额
        int[] dp = new int[n];

        // 初始条件：只有一间房屋时，能够偷窃的最高金额为该房屋的金额
        dp[0] = nums[0];

        // 当有两间房屋时，偷窃其中金额较大的房屋
        dp[1] = Math.max(nums[0], nums[1]);

        // 通过动态规划计算每个房屋对应的最高金额
        for (int i = 2; i < n; i++) {
            // 在偷窃第 i 间房屋时，有两种选择：偷窃第 i-2 间房屋+当前房屋金额 或 不偷窃第 i-1 间房屋，取较大值
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }

        // 返回偷窃到最后一间房屋时能够获得的最高金额
        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2, 3, 1};
        System.out.println(new HouseRobber().rob(nums1));

        int[] nums2 = new int[]{2, 7, 9, 3, 1};
        System.out.println(new HouseRobber().rob(nums2));
    }
}
