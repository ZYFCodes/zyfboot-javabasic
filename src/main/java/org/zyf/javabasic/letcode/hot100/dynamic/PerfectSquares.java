package org.zyf.javabasic.letcode.hot100.dynamic;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 完全平方数（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 19:54
 **/
public class PerfectSquares {
    public int numSquares(int n) {
        // 定义一个数组 dp，其中 dp[i] 表示和为 i 的最少完全平方数的数量
        int[] dp = new int[n + 1];
        // 初始化 dp 数组为最大值，表示初始状态下还没有计算出结果
        Arrays.fill(dp, Integer.MAX_VALUE);
        // 当 n=0 时，最少的完全平方数数量为 0
        dp[0] = 0;

        // 外层循环，遍历从 1 到 n 的所有值，计算每个 i 的最小完全平方数数量
        for (int i = 1; i <= n; i++) {
            // 内层循环，遍历所有的完全平方数 j*j
            for (int j = 1; j * j <= i; j++) {
                // 更新 dp[i] 为当前最小的完全平方数数量
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        // 返回 dp[n]，即和为 n 的最少完全平方数数量
        return dp[n];
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        // 输出 3，表示 12 可以表示为 4+4+4，最少需要 3 个完全平方数
        System.out.println(ps.numSquares(12));
        // 输出 2，表示 13 可以表示为 4+9，最少需要 2 个完全平方数
        System.out.println(ps.numSquares(13));
    }
}
