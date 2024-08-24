package org.zyf.javabasic.letcode.hot100.dynamic;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 零钱兑换（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:04
 **/
public class CoinChangeSolution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;  // 初始化一个超过amount的值，用于填充dp数组
        int[] dp = new int[amount + 1];  // dp数组，存储每个金额的最少硬币数
        Arrays.fill(dp, max);  // 将dp数组初始化为max
        dp[0] = 0;  // 金额为0时，所需硬币数为0

        // 遍历每个金额，从1到amount
        for (int i = 1; i <= amount; i++) {
            // 遍历每个硬币
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {  // 只有当硬币面值小于等于当前金额时，才考虑该硬币
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);  // 状态转移方程
                }
            }
        }

        // 如果dp[amount]仍然是初始值max，说明无法凑成该金额，返回-1
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        CoinChangeSolution solution = new CoinChangeSolution();
        int[] coins = {1, 2, 5};
        int amount = 11;
        int result = solution.coinChange(coins, amount);
        System.out.println(result); // 输出3
    }
}
