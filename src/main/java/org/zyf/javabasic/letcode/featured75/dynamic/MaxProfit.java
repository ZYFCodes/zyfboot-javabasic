package org.zyf.javabasic.letcode.featured75.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 买卖股票的最佳时机含手续费
 * @author: zhangyanfeng
 * @create: 2024-08-24 13:14
 **/
public class MaxProfit {
    public int maxProfit(int[] prices, int fee) {
        int cash = 0;  // 不持有股票时的最大利润
        int hold = Integer.MIN_VALUE;  // 持有股票时的最大利润，初始为负无穷

        for (int price : prices) {
            // 更新持有状态：当前持有股票的最大利润，可能是之前持有的状态或者从现金状态买入
            hold = Math.max(hold, cash - price);

            // 更新现金状态：当前不持有股票的最大利润，可能是之前现金的状态或者从持有状态卖出
            cash = Math.max(cash, hold + price - fee);
        }

        // 返回最终的现金状态，即没有持有股票时的最大利润
        return cash;
    }

    public static void main(String[] args) {
        MaxProfit solution = new MaxProfit();

        // 测试用例 1
        int[] prices1 = {1, 3, 2, 8, 4, 9};
        int fee1 = 2;
        System.out.println(solution.maxProfit(prices1, fee1)); // 预期输出: 8

        // 测试用例 2
        int[] prices2 = {1, 3, 7, 5, 10, 3};
        int fee2 = 3;
        System.out.println(solution.maxProfit(prices2, fee2)); // 预期输出: 6

        // 测试用例 3: 边界情况
        int[] prices3 = {1, 2};
        int fee3 = 1;
        System.out.println(solution.maxProfit(prices3, fee3)); // 预期输出: 0

        // 测试用例 4: 较长输入
        int[] prices4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int fee4 = 1;
        System.out.println(solution.maxProfit(prices4, fee4)); // 预期输出: 8
    }
}
