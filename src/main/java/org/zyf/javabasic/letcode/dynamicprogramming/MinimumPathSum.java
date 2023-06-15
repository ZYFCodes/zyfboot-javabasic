package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和最小。
 * 每次只能向下或向右移动一步。
 * 示例 1:
 * 输入:
 * [
 * [1,3,1],
 * [1,5,1],
 * [4,2,1]
 * ]
 * 输出: 7
 * 解释: 路径 1→3→1→1→1 的总和最小，为 7。
 * @date 2023/5/12  23:31
 */
public class MinimumPathSum {
    /**
     * 最优解法采用动态规划的思路来解决最小路径和问题。
     * 定义一个二维数组 dp，其中 dp[i][j] 表示从起点到网格中 (i, j) 位置的最小路径和。
     * 根据题目要求，每次只能向下或向右移动一步，
     * 因此对于位置 (i, j)，可以从上方 (i-1, j) 或左边 (i, j-1) 这两个位置移动过来。
     * 因此，可以得到状态转移方程：
     * dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]
     * 其中 grid[i][j] 表示网格中 (i, j) 位置的数字。
     * 接下来，可以使用动态规划的方式计算出 dp 数组的值。
     * 首先初始化边界条件，即起点 (0, 0) 的最小路径和为 grid[0][0]，
     * 然后从左上角开始遍历每个位置 (i, j)，根据状态转移方程更新 dp[i][j] 的值。
     * 最后，返回右下角 (m-1, n-1) 位置的值 dp[m-1][n-1]，即为从左上角到右下角的最小路径和。
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        // 初始化边界条件
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // 计算dp数组的值
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid1 = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int minSum1 = new MinimumPathSum().minPathSum(grid1);
        // 输出：最小路径和为：7
        System.out.println("最小路径和为：" + minSum1);

        int[][] grid2 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int minSum2 = new MinimumPathSum().minPathSum(grid2);
        // 输出：最小路径和为：21
        System.out.println("最小路径和为：" + minSum2);

        int[][] grid3 = {
                {1, 2},
                {3, 4},
                {5, 6}
        };
        int minSum3 = new MinimumPathSum().minPathSum(grid3);
        // 输出：最小路径和为：13
        System.out.println("最小路径和为：" + minSum3);
    }
}
