package org.zyf.javabasic.letcode.featured75.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 第 N 个泰波那契数
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:53
 **/
public class Tribonacci {
    public int tribonacci(int n) {
        // 特殊情况处理
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        // 创建一个数组来存储泰波那契数
        int[] dp = new int[n + 1];
        // 初始化基础值
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        // 计算从第3个泰波那契数到第n个泰波那契数
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        // 返回第n个泰波那契数
        return dp[n];
    }

    public static void main(String[] args) {
        Tribonacci solution = new Tribonacci();

        // 测试用例 1
        System.out.println(solution.tribonacci(4)); // 预期输出: 4

        // 测试用例 2
        System.out.println(solution.tribonacci(25)); // 预期输出: 1389537

        // 测试用例 3: 边界情况
        System.out.println(solution.tribonacci(0)); // 预期输出: 0
        System.out.println(solution.tribonacci(1)); // 预期输出: 1
        System.out.println(solution.tribonacci(2)); // 预期输出: 1

        // 测试用例 4: 较大输入
        System.out.println(solution.tribonacci(37)); // 预期输出: 2082876103
    }
}
