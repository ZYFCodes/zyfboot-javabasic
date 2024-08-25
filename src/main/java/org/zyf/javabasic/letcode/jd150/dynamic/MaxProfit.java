package org.zyf.javabasic.letcode.jd150.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 买卖股票的最佳时机 III
 * @author: zhangyanfeng
 * @create: 2024-08-25 20:11
 **/
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int n = prices.length; // 获取价格数组的长度

        // 初始化动态规划状态变量
        int buy1 = -prices[0], sell1 = 0; // 第一次购买和出售的初始状态
        int buy2 = -prices[0], sell2 = 0; // 第二次购买和出售的初始状态

        // 遍历每一天的价格
        for (int i = 1; i < n; ++i) {
            // 更新第一次购买的最大利润
            buy1 = Math.max(buy1, -prices[i]);
            // 更新第一次出售的最大利润
            sell1 = Math.max(sell1, buy1 + prices[i]);
            // 更新第二次购买的最大利润
            buy2 = Math.max(buy2, sell1 - prices[i]);
            // 更新第二次出售的最大利润
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }

        // 返回最多完成两笔交易的最大利润
        return sell2;
    }
}
