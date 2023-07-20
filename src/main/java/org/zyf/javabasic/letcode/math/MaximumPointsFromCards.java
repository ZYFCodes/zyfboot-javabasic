package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 当给定一行卡牌，每张卡牌上都有一个正整数代表点数。
 * 你可以选择从行的开头或末尾取任意数量的卡牌，但至少要选择一张。
 * 问题是要求你计算出能够获得的最大总点数。
 * 例如：输入：[1, 2, 3, 4, 5, 6, 1]，K = 3    输出：15（选择点数为6、5和4的卡牌）
 * @date 2023/6/29  23:20
 */
public class MaximumPointsFromCards {
    /**
     * 最优解法是使用动态规划（Dynamic Programming）来解决这个问题。
     * 我们可以通过构建一个二维数组来存储中间结果，并利用状态转移方程来计算最大总点数。
     * 假设给定的卡牌数组为nums，长度为n，我们定义二维数组dp，其中dp[i][j]表示在前i个卡牌中选择j张卡牌时可以获得的最大总点数。
     * 状态转移方程如下：
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-1] + nums[i])
     * 其中dp[i-1][j]表示不选第i张卡牌时的最大总点数，dp[i-1][j-1] + nums[i]表示选第i张卡牌时的最大总点数。
     * 初始化dp数组时，dp[0][0]为0，dp[0][1]到dp[0][n]为nums[0]到nums[n-1]的累加和。
     * 最后，我们需要返回dp[n][k]，其中n为卡牌数组的长度，k为需要选择的卡牌数量。
     * 这种动态规划解法的时间复杂度为O(n*k)，其中n为卡牌数组的长度，k为需要选择的卡牌数量。
     * 这是因为我们需要填充一个大小为n*(k+1)的二维数组。空间复杂度也为O(n*k)。
     */
    public static int maxPoints(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n + 1][k + 1];

        // 初始化第一行，表示前0个卡牌时的最大总点数为0
        for (int i = 0; i <= k; i++) {
            dp[0][i] = 0;
        }

        // 填充dp数组
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                // 不选第i张卡牌时的最大总点数
                int notPickCard = dp[i - 1][j];
                // 选第i张卡牌时的最大总点数
                int pickCard = dp[i - 1][j - 1] + nums[i - 1];
                // 取两者中的较大值作为dp[i][j]的值
                dp[i][j] = Math.max(notPickCard, pickCard);
            }
        }

        // 返回最终结果，即从前n个卡牌中选择k张卡牌时的最大总点数
        return dp[n][k];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 1};
        int k = 3;
        int result = maxPoints(nums, k);
        System.out.println("最大总点数为: " + result);
    }

}
