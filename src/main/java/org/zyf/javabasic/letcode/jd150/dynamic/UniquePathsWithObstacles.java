package org.zyf.javabasic.letcode.jd150.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 不同路径 II
 * @author: zhangyanfeng
 * @create: 2024-08-25 20:00
 **/
public class UniquePathsWithObstacles {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length; // 行数
        int n = obstacleGrid[0].length; // 列数

        // 创建一个二维数组 dp，用于存储从 (0,0) 到 (i,j) 的路径数量
        int[][] dp = new int[m][n];

        // 初始化 dp 数组
        // 如果起点有障碍物，则直接返回 0
        if (obstacleGrid[0][0] == 1) {
            return 0;
        }
        dp[0][0] = 1; // 起点到起点的路径数为 1

        // 填充第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = (obstacleGrid[0][j] == 0 && dp[0][j - 1] == 1) ? 1 : 0;
        }

        // 填充第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = (obstacleGrid[i][0] == 0 && dp[i - 1][0] == 1) ? 1 : 0;
        }

        // 填充其余的 dp 数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                } else {
                    dp[i][j] = 0; // 如果有障碍物，路径数为 0
                }
            }
        }

        // 返回右下角的路径数量
        return dp[m - 1][n - 1];
    }
}
