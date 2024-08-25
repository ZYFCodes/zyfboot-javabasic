package org.zyf.javabasic.letcode.jd150.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 买卖股票的最佳时机 IV
 * @author: zhangyanfeng
 * @create: 2024-08-25 20:14
 **/
public class MaxProfit2 {
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0) return 0;

        // 如果交易次数 k 大于等于天数的一半，意味着可以进行无限次交易
        if (k >= n / 2) {
            int maxProfit = 0;
            for (int i = 1; i < n; ++i) {
                if (prices[i] > prices[i - 1]) {
                    maxProfit += prices[i] - prices[i - 1];
                }
            }
            return maxProfit;
        }

        // dp[t][d] 表示第 d 天完成 t 次交易的最大利润
        int[][] dp = new int[k + 1][n];

        // 遍历每次交易
        for (int t = 1; t <= k; ++t) {
            // 在第 t 次交易时，初始化最优利润
            int maxDiff = -prices[0];

            // 遍历每一天
            for (int d = 1; d < n; ++d) {
                // 更新 dp[t][d]，考虑不进行交易和进行交易两种情况
                dp[t][d] = Math.max(dp[t][d - 1], prices[d] + maxDiff);
                // 更新 maxDiff，为下一天的交易准备
                maxDiff = Math.max(maxDiff, dp[t - 1][d] - prices[d]);
            }
        }

        // 返回最多完成 k 次交易的最大利润
        return dp[k][n - 1];
    }
}
