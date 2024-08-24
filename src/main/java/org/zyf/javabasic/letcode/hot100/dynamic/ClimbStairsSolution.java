package org.zyf.javabasic.letcode.hot100.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 爬楼梯（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 19:23
 **/
public class ClimbStairsSolution {
    public int climbStairs(int n) {
        if (n <= 1) return 1; // 如果楼梯只有1级或0级，则只有一种爬法

        int[] dp = new int[n + 1];
        dp[0] = 1; // 起点
        dp[1] = 1; // 第一个台阶

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // 爬到第 i 阶的方法数
        }

        return dp[n];
    }

    public static void main(String[] args) {
        ClimbStairsSolution solution = new ClimbStairsSolution();
        System.out.println(solution.climbStairs(2)); // 输出: 2
        System.out.println(solution.climbStairs(3)); // 输出: 3
    }
}
