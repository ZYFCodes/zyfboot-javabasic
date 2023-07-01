package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 写一个函数，输入 n，求斐波那契数列的第 n 项。
 * @date 2023/6/3  23:02
 */
public class Fibonacci {
    /***
     * 动态规划法：
     * 	•	使用一个数组 dp[] 来保存已经计算过的斐波那契数列的结果，初始将 dp[0] 置为 0，dp[1] 置为 1。
     * 	•	从 dp[2] 开始迭代计算，dp[i] = dp[i-1] + dp[i-2]。
     * 	•	最后返回 dp[n] 即为第 n 项的结果。
     * 	•	动态规划法避免了重复计算，时间复杂度为 O(n)，空间复杂度为 O(n)。
     */
    public int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Fibonacci solution = new Fibonacci();

        // 测试案例1：n = 0
        int result1 = solution.fibonacci(0);
        System.out.println("Fibonacci(0) = " + result1);

        // 测试案例2：n = 1
        int result2 = solution.fibonacci(1);
        System.out.println("Fibonacci(1) = " + result2);

        // 测试案例3：n = 5
        int result3 = solution.fibonacci(5);
        System.out.println("Fibonacci(5) = " + result3);

        // 测试案例4：n = 10
        int result4 = solution.fibonacci(10);
        System.out.println("Fibonacci(10) = " + result4);
    }
}
