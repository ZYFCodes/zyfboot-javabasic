package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。返回你可以获得的最大乘积。
 * 示例 1:输入: 2     输出: 1         解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:输入: 10   输出: 36      解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明：你可以假设 n 不小于 2 且不大于 58。
 * @date 2023/5/23  23:07
 */
public class IntegerBreak {
    /**
     * 我们可以定义一个数组 dp，其中 dp[i] 表示将正整数 i 拆分后得到的最大乘积。对于 i >= 2，可以将 i 拆分为两个数 j 和 i-j，然后考虑这两个数的最大乘积。
     * 状态转移方程如下：dp[i] = max(j * (i-j), j * dp[i-j])，其中 1 <= j < i
     * 对于 j * (i-j)，表示拆分为两个数，其中一个为 j，另一个为 i-j。
     * 对于 j * dp[i-j]，表示拆分为两个数，其中一个为 j，另一个为 i-j 的最大乘积。
     * 最终的答案是 dp[n]，其中 n 是给定的正整数。
     */
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // 将 i 拆分为 j 和 i-j，比较 j * (i-j) 和 j * dp[i-j] 的大小
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n1 = 2;
        int maxProduct1 = new IntegerBreak().integerBreak(n1);
        // 输出：最大乘积为：1
        System.out.println("最大乘积为：" + maxProduct1);

        int n2 = 10;
        int maxProduct2 = new IntegerBreak().integerBreak(n2);
        // 输出：最大乘积为：36
        System.out.println("最大乘积为：" + maxProduct2);

        int n3 = 15;
        int maxProduct3 = new IntegerBreak().integerBreak(n3);
        // 输出：最大乘积为：243
        System.out.println("最大乘积为：" + maxProduct3);

        int n4 = 20;
        int maxProduct4 = new IntegerBreak().integerBreak(n4);
        // 输出：最大乘积为：1458
        System.out.println("最大乘积为：" + maxProduct4);
    }

}
