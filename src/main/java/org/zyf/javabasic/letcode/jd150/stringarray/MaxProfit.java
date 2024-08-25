package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 买卖股票的最佳时机 II
 * @author: zhangyanfeng
 * @create: 2024-08-25 08:54
 **/
public class MaxProfit {
    public int maxProfit(int[] prices) {
        // 初始化最大利润为0
        int maxProfit = 0;

        // 遍历价格数组，从第二天开始计算
        for (int i = 1; i < prices.length; i++) {
            // 如果今天的价格比昨天的高，计算利润
            if (prices[i] > prices[i - 1]) {
                // 累加利润
                maxProfit += prices[i] - prices[i - 1];
            }
        }

        // 返回计算的最大利润
        return maxProfit;
    }

    // 测试用例
    public static void main(String[] args) {
        MaxProfit solution = new MaxProfit();

        // 示例1
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("最大利润: " + solution.maxProfit(prices1));  // 输出应为7

        // 示例2
        int[] prices2 = {1, 2, 3, 4, 5};
        System.out.println("最大利润: " + solution.maxProfit(prices2));  // 输出应为4

        // 示例3
        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println("最大利润: " + solution.maxProfit(prices3));  // 输出应为0
    }
}
