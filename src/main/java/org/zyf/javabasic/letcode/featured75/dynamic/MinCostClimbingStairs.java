package org.zyf.javabasic.letcode.featured75.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 使用最小花费爬楼梯
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:58
 **/
public class MinCostClimbingStairs {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;

        // 特殊情况处理
        if (n == 2) return Math.min(cost[0], cost[1]);

        // 创建 dp 数组
        int[] dp = new int[n];

        // 初始化 dp 数组的前两个元素
        dp[0] = cost[0];
        dp[1] = cost[1];

        // 填充 dp 数组
        for (int i = 2; i < n; i++) {
            dp[i] = cost[i] + Math.min(dp[i - 1], dp[i - 2]);
        }

        // 返回到达顶部的最低花费
        return Math.min(dp[n - 1], dp[n - 2]);
    }

    public static void main(String[] args) {
        MinCostClimbingStairs solution = new MinCostClimbingStairs();

        // 测试用例 1
        int[] cost1 = {10, 15, 20};
        System.out.println(solution.minCostClimbingStairs(cost1)); // 预期输出: 15

        // 测试用例 2
        int[] cost2 = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(solution.minCostClimbingStairs(cost2)); // 预期输出: 6

        // 测试用例 3: 边界情况
        int[] cost3 = {0, 0};
        System.out.println(solution.minCostClimbingStairs(cost3)); // 预期输出: 0

        // 测试用例 4: 较长输入
        int[] cost4 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(solution.minCostClimbingStairs(cost4)); // 预期输出: 15
    }
}
