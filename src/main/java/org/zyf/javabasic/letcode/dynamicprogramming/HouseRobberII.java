package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 你是一个专业的强盗，计划抢劫街区的房屋。每个房屋都存放着特定金额的钱。
 * 你面临的唯一约束条件是，由于安全系统的存在，相邻的房屋装有连接的警报系统。
 * 换句话说，如果两个相邻的房屋在同一晚上被打劫，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报系统的情况下，能够抢劫到的最高金额。
 * 注意：这是一个环形街区，意味着第一个房屋和最后一个房屋是相邻的。
 * 示例 1:输入: [2,3,2].  输出: 3
 * 解释: 你可以选择从第一间房屋抢劫 2 元，然后跳过第二间房屋，接着抢劫第三间房屋 1 元。因此，抢劫到的最高金额为 3 元。
 * 示例 2:输入: [1,2,3,1]  输出: 4
 * 解释: 你可以选择抢劫第一间房屋 1 元和第三间房屋 3 元，抢劫到的最高金额为 4 元。
 * 注意，你不能选择抢劫第一间房屋和最后一间房屋，因为它们是相邻的。
 * @date 2023/5/27  23:25
 */
public class HouseRobberII {
    /**
     * 对于这两个子问题，可以使用类似于"打家劫舍"问题的动态规划方法来求解。
     * 具体步骤如下：
     * 1. 对于子问题1，创建一个动态规划数组 dp1，其中 dp1[i] 表示从第一间房屋到第 i 间房屋（不包括最后一间房屋）能够抢劫到的最高金额。
     * 初始化 dp1[0] = nums[0]，dp1[1] = max(nums[0], nums[1])。
     * 2. 对于子问题2，创建一个动态规划数组 dp2，其中 dp2[i] 表示从第二间房屋到第 i 间房屋能够抢劫到的最高金额。
     * 初始化 dp2[0] = 0，dp2[1] = nums[1]。
     * 3. 对于子问题1，从第三间房屋开始遍历，计算每个位置的最高金额：dp1[i] = max(dp1[i-2] + nums[i], dp1[i-1])。
     * 4. 对于子问题2，从第三间房屋开始遍历，计算每个位置的最高金额：dp2[i] = max(dp2[i-2] + nums[i+1], dp2[i-1])。
     * 5. 最后的结果为 max(dp1[n-2], dp2[n-1])，其中 n 为房屋数量。
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }

        // 分为两个子问题：不抢劫最后一间房屋和不抢劫第一间房屋
        int max1 = robHelper(nums, 0, n - 2);
        int max2 = robHelper(nums, 1, n - 1);

        // 返回两个子问题的较大值
        return Math.max(max1, max2);
    }

    // 求解单个子问题的最大金额
    private int robHelper(int[] nums, int start, int end) {
        int n = end - start + 1;
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return nums[start];
        }

        int[] dp = new int[n];
        dp[0] = nums[start];
        dp[1] = Math.max(nums[start], nums[start + 1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[start + i], dp[i - 1]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, 2};
        int maxAmount1 = new HouseRobberII().rob(nums1);
        // 输出：最大金额为：3
        System.out.println("最大金额为：" + maxAmount1);

        int[] nums2 = {1, 2, 3, 1};
        int maxAmount2 = new HouseRobberII().rob(nums2);
        // 输出：最大金额为：4
        System.out.println("最大金额为：" + maxAmount2);

        int[] nums3 = {1, 2, 3, 4, 5, 1, 2, 3, 4, 5};
        int maxAmount3 = new HouseRobberII().rob(nums3);
        // 输出：最大金额为：16
        System.out.println("最大金额为：" + maxAmount3);
    }

}
