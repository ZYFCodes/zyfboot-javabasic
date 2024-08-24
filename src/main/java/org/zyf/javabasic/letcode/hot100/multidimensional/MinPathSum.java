package org.zyf.javabasic.letcode.hot100.multidimensional;

/**
 * @program: zyfboot-javabasic
 * @description: 最小路径和（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:46
 **/
public class MinPathSum {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // 创建 dp 数组，和 grid 数组大小相同
        int[][] dp = new int[m][n];

        // 初始化 dp 数组的起点
        dp[0][0] = grid[0][0];

        // 初始化第一行
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j-1] + grid[0][j];
        }

        // 初始化第一列
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i-1][0] + grid[i][0];
        }

        // 填充 dp 数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] + Math.min(dp[i-1][j], dp[i][j-1]);
            }
        }

        // 返回右下角的最小路径和
        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        MinPathSum mps = new MinPathSum();

        // 测试用例1
        int[][] grid1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println("测试用例1结果: " + mps.minPathSum(grid1)); // 输出：7

        // 测试用例2
        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6}
        };
        System.out.println("测试用例2结果: " + mps.minPathSum(grid2)); // 输出：12
    }
}
