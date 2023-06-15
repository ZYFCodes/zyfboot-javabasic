package org.zyf.javabasic.letcode.dynamicprogramming;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定不同面额的硬币 coins 和一个总金额 amount，
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 你可以认为每种硬币的数量是无限的。
 * 示例 1:输入: coins = [1, 2, 5], amount = 11.      输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:输入: coins = [2], amount = 3      输出: -1
 * @date 2023/5/26  23:41
 */
public class CoinChange {
    /**
     * 我们定义一个一维数组 dp，其中 dp[i] 表示凑成金额 i 所需的最少硬币个数。
     * 初始化数组 dp 为无穷大，除了 dp[0] = 0，表示凑成金额为 0 不需要任何硬币。
     * 接下来，我们遍历金额从 1 到 amount，对于每个金额 i，
     * 我们枚举所有的硬币面额 coin，如果 coin 小于等于 i，则说明可以使用该硬币，
     * 更新 dp[i] 的值为 dp[i - coin] + 1 和当前 dp[i] 的较小值。
     * 最终，dp[amount] 就是凑成总金额所需的最少硬币个数。
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        int[] coins1 = {1, 2, 5};
        int amount1 = 11;
        int minCoins1 = new CoinChange().coinChange(coins1, amount1);
        System.out.println("凑成总金额 " + amount1 + " 所需的最少硬币个数为：" + minCoins1);

        int[] coins2 = {2};
        int amount2 = 3;
        int minCoins2 = new CoinChange().coinChange(coins2, amount2);
        System.out.println("凑成总金额 " + amount2 + " 所需的最少硬币个数为：" + minCoins2);
    }
}
