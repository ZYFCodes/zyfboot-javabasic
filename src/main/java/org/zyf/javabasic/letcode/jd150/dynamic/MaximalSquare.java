package org.zyf.javabasic.letcode.jd150.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 最大正方形
 * @author: zhangyanfeng
 * @create: 2024-08-25 20:18
 **/
public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) return 0;
        int n = matrix[0].length;

        // dp[i][j] 表示以 matrix[i][j] 为右下角的最大正方形的边长
        int[][] dp = new int[m][n];
        int maxSide = 0; // 记录最大正方形的边长

        // 遍历矩阵
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 只有 matrix[i][j] 为 '1' 时才有可能形成正方形
                if (matrix[i][j] == '1') {
                    // 如果在第一行或第一列，dp[i][j] 只能为 1
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1;
                    } else {
                        // 更新 dp[i][j] 的值
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    }
                    // 更新最大边长
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        // 返回最大正方形的面积
        return maxSide * maxSide;
    }
}
