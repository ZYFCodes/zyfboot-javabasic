package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择某一天买入这只股票，并选择在未来的某一个不同的日子卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 * 示例 1:输入: prices = [7,1,5,3,6,4]. 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * 示例 2:输入: prices = [7,6,4,3,1]  输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * @date 2023/5/28  23:31
 */
public class BestTimeBuyAndSellStock {
    /**
     * 这道题可以使用动态规划来解决。
     * 我们可以定义两个变量 minPrice 和 maxProfit，分别表示当前遍历过的最低价格和当前的最大利润。
     * 在遍历股票价格数组时，我们可以更新 minPrice 为当前价格和 minPrice 中的较小值。
     * 然后，我们可以计算当前价格与 minPrice 的差值，并将其与 maxProfit 进行比较，更新 maxProfit 为较大值。
     * 最终，maxProfit 即为所能获取的最大利润。
     * <p>
     * 该算法的时间复杂度为 O(n)，其中 n 是股票价格数组的长度。
     * 通过动态规划的思想，我们可以在一次遍历中找到最低价格和最大利润，从而解决股票买卖问题。
     */
    public int maxProfit(int[] prices) {
        // 初始化最低价格为正无穷大
        int minPrice = Integer.MAX_VALUE;
        // 初始化最大利润为0
        int maxProfit = 0;

        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int maxProfit1 = new BestTimeBuyAndSellStock().maxProfit(prices1);
        // 输出：最大利润：5
        System.out.println("最大利润：" + maxProfit1);

        int[] prices2 = {7, 6, 4, 3, 1};
        int maxProfit2 = new BestTimeBuyAndSellStock().maxProfit(prices2);
        // 输出：最大利润：0
        System.out.println("最大利润：" + maxProfit2);
    }
}
