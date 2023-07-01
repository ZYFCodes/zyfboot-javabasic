package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 在一个 m × n 的棋盘上，
 * 每个格子中放着一定数量的礼物，你可以从棋盘的左上角开始，
 * 每次向右或向下移动一格，直到到达棋盘的右下角。
 * 请计算出经过的路径所能拿到的礼物的最大价值。
 * @date 2023/6/3  22:45
 */
public class GridMaxValue {
    /**
     * 这是一个典型的动态规划问题。我们可以定义一个二维数组 dp[m][n]，其中 dp[i][j] 表示到达棋盘上第 (i,j) 个格子时能拿到的最大礼物价值。根据题目要求，我们可以得到状态转移方程：
     * dp[i][j] = max(dp[i-1][j], dp[i][j-1]) + grid[i][j]
     * 其中，dp[i-1][j] 表示从上方格子到达当前格子的最大价值，dp[i][j-1] 表示从左方格子到达当前格子的最大价值，grid[i][j] 表示当前格子中的礼物价值。
     * <p>
     * 具体步骤如下：
     * 1.	创建一个大小为 (m+1) × (n+1) 的二维数组 dp，其中 dp[i][j] 表示到达棋盘上第 (i,j) 个格子时能拿到的最大礼物价值。
     * 2.	初始化边界条件，即第一行和第一列的最大礼物价值，dp[0][j] = dp[0][j-1] + grid[0][j] 和 dp[i][0] = dp[i-1][0] + grid[i][0]。
     * 3.	从第二行第二列开始遍历棋盘，根据状态转移方程更新 dp[i][j] 的值。
     * 4.	遍历完整个棋盘后，dp[m][n] 即为到达右下角格子时能拿到的最大礼物价值。
     * 最终，返回 dp[m][n] 即可得到最大礼物价值。
     * 该方法的时间复杂度为 O(m × n)，其中 m 和 n 分别为棋盘的行数和列数。
     * 【注】这里假设棋盘上的礼物价值都为非负数。如果存在负数礼物，需要进行额外的处理。id
     */
    public int getMaxValue(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        // 创建dp数组
        int[][] dp = new int[m + 1][n + 1];

        // 初始化边界条件
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i - 1][0];
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j - 1];
        }

        // 动态规划计算最大礼物价值
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        GridMaxValue solution = new GridMaxValue();

        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        int maxValue = solution.getMaxValue(grid);
        // 预期输出: 15
        System.out.println("Max value: " + maxValue);
    }

}
