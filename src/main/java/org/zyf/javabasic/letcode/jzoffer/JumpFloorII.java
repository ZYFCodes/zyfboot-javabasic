package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 一个青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶，或者跳上 m 级台阶。
 * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * @date 2023/6/8  22:41
 */
public class JumpFloorII {
    /**
     * 设跳上第 n 级台阶的跳法总数为 f(n)。青蛙最后一步可以跳上第 n-1 级台阶，也可以跳上第 n-2 级台阶，或者跳上第 n-m 级台阶。
     * •	如果青蛙最后一步跳上第 n-1 级台阶，那么前面的跳法总数为 f(n-1)。
     * •	如果青蛙最后一步跳上第 n-2 级台阶，那么前面的跳法总数为 f(n-2)。
     * •	如果青蛙最后一步跳上第 n-m 级台阶，那么前面的跳法总数为 f(n-m)。
     * 所以，跳上第 n 级台阶的总跳法数为 f(n) = f(n-1) + f(n-2) + … + f(n-m)。其中，初始条件为 f(1) = 1，f(2) = 2，…，f(m) = m。
     * <p>
     * 具体步骤如下：
     * 1.	定义一个数组 dp，dp[i] 表示跳上第 i 级台阶的跳法总数。
     * 2.	初始化 dp[1] = 1，dp[2] = 2，…，dp[m] = m。
     * 3.	从 i = m+1 开始遍历到 n，根据状态转移方程 dp[i] = dp[i-1] + dp[i-2] + … + dp[i-m] 计算 dp[i] 的值。
     * 4.	返回 dp[n]，即跳上第 n 级台阶的跳法总数。
     */
    public int jumpFloorII(int n, int m) {
        if (n <= m) {
            return n;
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i] = i;
        }

        for (int i = m + 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i] += dp[i - j];
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        JumpFloorII solution = new JumpFloorII();
        int n = 5;
        int m = 2;
        // 输出结果为 8
        System.out.println(solution.jumpFloorII(n, m));
    }
}
