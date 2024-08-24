package org.zyf.javabasic.letcode.hot100.greedy;

/**
 * @program: zyfboot-javabasic
 * @description: 买卖股票的最佳时机（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 15:06
 **/
public class MaxProfitSolution {
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE; // 初始化最小价格为正无穷
        int maxProfit = 0; // 初始化最大利润为0

        // 遍历每一天的股票价格
        for (int price : prices) {
            if (price < minPrice) {
                // 更新最小价格
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                // 计算利润，并更新最大利润
                maxProfit = price - minPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        MaxProfitSolution solution = new MaxProfitSolution();
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};

        System.out.println(solution.maxProfit(prices1)); // 输出: 5
        System.out.println(solution.maxProfit(prices2)); // 输出: 0
    }
}
