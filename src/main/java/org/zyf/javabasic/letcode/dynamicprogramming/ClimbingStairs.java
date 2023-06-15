package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 假设你正在爬楼梯。需要 n 阶才能到达楼顶。每次你可以爬 1 或 2 个台阶。
 * 编写一个函数，计算你到达楼顶的不同方式的数量。
 * 注意：给定的 n 是一个正整数。
 * 示例 1:输入: 2   输出: 2
 * 解释: 有两种方式可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * 示例 2:输入: 3   输出: 3
 * 解释: 有三种方式可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * @date 2023/5/27  23:36
 */
public class ClimbingStairs {
    /**
     * 这道题可以使用动态规划来解决。我们定义一个数组 dp，其中 dp[i] 表示爬到第 i 阶楼梯的不同方式的数量。
     * 根据题目要求，爬到第 i 阶楼梯的方式只有两种：
     * 1. 从第 i-1 阶楼梯爬一步到达：dp[i-1]
     * 2. 从第 i-2 阶楼梯爬两步到达：dp[i-2]
     * 所以，dp[i] = dp[i-1] + dp[i-2]，即爬到第 i 阶楼梯的不同方式数量等于爬到第 i-1 阶和第 i-2 阶楼梯方式数量之和。
     * 初始条件是 dp[0] = 1（表示不爬任何台阶）和 dp[1] = 1（表示爬一阶台阶）。
     * 最终要求的答案是 dp[n]，其中 n 表示楼梯的总阶数。
     */
    public int climbStairs(int n) {
        if (n <= 1) {
            return 1;
        }

        // 创建一个数组来保存每个楼梯阶数对应的不同爬法数量
        int[] dp = new int[n + 1];

        // 初始条件：第0阶和第1阶都只有一种爬法
        dp[0] = 1;
        dp[1] = 1;

        // 通过动态规划计算每个楼梯阶数对应的不同爬法数量
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回爬到第n阶楼梯的不同爬法数量作为结果
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println("楼顶数为2" + "，你到达楼顶的不同方式的数量为" + new ClimbingStairs().climbStairs(2));
        System.out.println("楼顶数为3" + "，你到达楼顶的不同方式的数量为" + new ClimbingStairs().climbStairs(3));
        System.out.println("楼顶数为4" + "，你到达楼顶的不同方式的数量为" + new ClimbingStairs().climbStairs(4));
        System.out.println("楼顶数为5" + "，你到达楼顶的不同方式的数量为" + new ClimbingStairs().climbStairs(5));
        System.out.println("楼顶数为6" + "，你到达楼顶的不同方式的数量为" + new ClimbingStairs().climbStairs(6));
        System.out.println("楼顶数为34" + "，你到达楼顶的不同方式的数量为" + new ClimbingStairs().climbStairs(34));
    }
}
