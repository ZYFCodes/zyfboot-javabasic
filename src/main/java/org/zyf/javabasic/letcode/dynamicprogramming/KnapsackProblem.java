package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 给定一个背包的容量 capacity和一组物品，
 * 每个物品有对应的重量 weights 和价值 values，
 * 要求选择一些物品放入背包中，使得放入的物品总重量不超过背包容量，并且物品的总价值最大。
 * 注意：每个物品只能选择放入背包一次（0-1属性）。
 * 示例：
 * 输入:
 * capacity = 10
 * weights = [2, 3, 4, 5]
 * values = [3, 4, 5, 6]
 * 输出：13
 * 解释：选择重量为 2, 3 , 5 的物品，它们的总重量为 10，总价值为 13，符合背包容量限制且价值最大。
 * @date 2023/5/24  23:44
 */
public class KnapsackProblem {
    /**
     * 0-1背包问题可以使用动态规划来解决。我们可以使用一个二维数组 dp，其中 dp[i][j] 表示在前 i 个物品中选择，且背包容量为 j 时的最大总价值。
     * 对于每个物品，我们有两种选择：
     * 1. 不选择该物品，即背包容量保持不变，总价值也不变，即 dp[i][j] = dp[i-1][j]。
     * 2. 选择该物品，即背包容量减去当前物品的重量，并将当前物品的价值加到总价值中，即 dp[i][j] = dp[i-1][j-weights[i]] + values[i]。
     * 综合考虑这两种选择，可以得到状态转移方程：
     * dp[i][j] = max(dp[i-1][j], dp[i-1][j-weights[i]] + values[i])
     * 初始条件为当物品数量为 0 或背包容量为 0 时，总价值为 0。
     * 最终要求的答案是 dp[n][capacity]，其中 n 是物品的数量。
     */
    public int knapsack(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // 初始化第一行和第一列为0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= capacity; j++) {
            dp[0][j] = 0;
        }

        // 计算其他位置的最大总价值
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (weights[i - 1] <= j) {
                    // 当前物品可以放入背包
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                } else {
                    // 当前物品放不进背包
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][capacity];
    }

    public static void main(String[] args) {
        int capacity = 10;
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};

        int maxTotalValue = new KnapsackProblem().knapsack(capacity, weights, values);
        // 输出：最大总价值：13
        System.out.println("最大总价值：" + maxTotalValue);
    }
}
