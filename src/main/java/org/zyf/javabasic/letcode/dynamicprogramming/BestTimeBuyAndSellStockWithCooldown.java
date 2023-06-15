package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:输入: [1,2,3,0,2]  输出: 3
 * 解释: 在第 2 天（股票价格 = 2）的时候买入，在第 3 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-2 = 1 。
 * 随后，在第 4 天（股票价格 = 0）的时候买入，在第 5 天（股票价格 = 2）的时候卖出，这笔交易所能获得利润 = 2-0 = 2 。
 * @date 2023/5/29  23:37
 */
public class BestTimeBuyAndSellStockWithCooldown {
    /**
     * 最优解法采用动态规划来解决含有冷冻期的买卖股票问题。
     * 假设有三个状态：
     * 1. hold[i]：表示第 i 天持有股票时的最大利润。
     * 2. notHold[i]：表示第 i 天不持有股票时的最大利润。
     * 3. cooldown[i]：表示第 i 天处于冷冻期时的最大利润。
     * 对于第 i 天的状态转移，可以根据前一天的状态计算得出：
     * * 如果第 i 天持有股票，则可能是前一天已经持有股票，或者是前一天不持有股票但在冷冻期之前买入股票。
     * * 如果第 i 天不持有股票，则可能是前一天已经不持有股票，或者是前一天在冷冻期之前卖出了股票。
     * * 如果第 i 天处于冷冻期，则前一天必须卖出股票。
     * 具体状态转移方程如下：
     * hold[i] = max(hold[i-1], notHold[i-1] - prices[i])
     * notHold[i] = max(notHold[i-1], cooldown[i-1])
     * cooldown[i] = hold[i-1] + prices[i]
     * 最终的答案是 max(notHold[n-1], cooldown[n-1])，其中 n 是数组 prices 的长度。
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[] hold = new int[n];
        int[] notHold = new int[n];
        int[] cooldown = new int[n];

        hold[0] = -prices[0];

        for (int i = 1; i < n; i++) {
            hold[i] = Math.max(hold[i - 1], notHold[i - 1] - prices[i]);
            notHold[i] = Math.max(notHold[i - 1], cooldown[i - 1]);
            cooldown[i] = hold[i - 1] + prices[i];
        }

        return Math.max(notHold[n - 1], cooldown[n - 1]);
    }

    public static void main(String[] args) {
        int[] prices1 = {1, 2, 3, 0, 2};
        int maxProfit1 = new BestTimeBuyAndSellStockWithCooldown().maxProfit(prices1);
        // 输出：最大利润为：3
        System.out.println("最大利润为：" + maxProfit1);

        int[] prices2 = {7, 1, 5, 3, 6, 4};
        int maxProfit2 = new BestTimeBuyAndSellStockWithCooldown().maxProfit(prices2);
        // 输出：最大利润为：5
        System.out.println("最大利润为：" + maxProfit2);

        int[] prices3 = {1, 2, 3, 4, 5};
        int maxProfit3 = new BestTimeBuyAndSellStockWithCooldown().maxProfit(prices3);
        // 输出：最大利润为：4
        System.out.println("最大利润为：" + maxProfit3);

        int[] prices4 = {7, 6, 4, 3, 1};
        int maxProfit4 = new BestTimeBuyAndSellStockWithCooldown().maxProfit(prices4);
        // 输出：最大利润为：0
        System.out.println("最大利润为：" + maxProfit4);
    }
}
