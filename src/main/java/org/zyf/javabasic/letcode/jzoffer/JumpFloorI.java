package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 一个青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶。
 * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * @date 2023/6/8  22:38
 */
public class JumpFloorI {
    /**
     * 设跳上第 n 级台阶的跳法总数为 f(n)。青蛙最后一步可以跳上第 n-1 级台阶，也可以跳上第 n-2 级台阶。
     * •	如果青蛙最后一步跳上第 n-1 级台阶，那么前面的跳法总数为 f(n-1)。
     * •	如果青蛙最后一步跳上第 n-2 级台阶，那么前面的跳法总数为 f(n-2)。
     * 所以，跳上第 n 级台阶的总跳法数为 f(n) = f(n-1) + f(n-2)。其中，初始条件为 f(1) = 1，f(2) = 2。
     * 具体步骤如下：
     * 1.	定义一个数组 dp，dp[i] 表示跳上第 i 级台阶的跳法总数。
     * 2.	初始化 dp[1] = 1，dp[2] = 2。
     * 3.	从 i = 3 开始遍历到 n，根据状态转移方程 dp[i] = dp[i-1] + dp[i-2] 计算 dp[i] 的值。
     * 4.	返回 dp[n]，即跳上第 n 级台阶的跳法总数。
     */
    public int jumpFloor(int n) {
        if (n <= 2) {
            return n;
        }

        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    public static void main(String[] args) {
        JumpFloorI solution = new JumpFloorI();
        int n = 5;
        // 输出结果为 8
        System.out.println(solution.jumpFloor(n));
    }

}
