package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 一个机器人位于一个 m x n 网格的左上角。机器人每次只能向下或向右移动一步。
 * 机器人试图达到网格的右下角。问总共有多少条不同的路径？
 * 例如，一个 3 x 7 的网格，机器人从左上角到右下角总共有 28 条不同的路径。
 * 注意：m 和 n 的值均不超过 100。
 * 示例 1:输入: m = 3, n = 2.       输出: 3
 * 解释: 从左上角开始，总共有三条路径可以到达右下角。
 * 1. 向右 -> 向右 -> 向下
 * 2. 向右 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向右
 * 示例 2:输入: m = 7, n = 3       输出: 28
 * @date 2023/5/12  23:24
 */
public class UniquePaths {
    /**
     * 这道题可以使用动态规划来解决。
     * 我们定义一个二维数组 dp，其中 dp[i][j] 表示从起始位置到达网格 (i, j) 的不同路径数。
     * 根据题目要求，机器人每次只能向下或向右移动，
     * 因此对于网格中的每个位置 (i, j)，可以从上方的位置 (i-1, j) 或左方的位置 (i, j-1) 到达。
     * <p>
     * 根据上述分析，可以得到状态转移方程：
     * dp[i][j] = dp[i-1][j] + dp[i][j-1]
     * 初始条件为：当 i=0 或 j=0 时，只有一条路径可以到达，因此 dp[i][j] = 1。
     * 最终要求的答案是 dp[m-1][n-1]，其中 m 和 n 分别是网格的行数和列数。
     * <p>
     * 该算法的时间复杂度为 O(m * n)，空间复杂度为 O(m * n)，其中 m 和 n 分别是网格的行数和列数。
     * 通过动态规划的思想，我们可以高效地计算出不同路径的数量。
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // 初始化第一行和第一列的路径数为1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 计算其他位置的路径数
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int m1 = 3, n1 = 2;
        int paths1 = new UniquePaths().uniquePaths(m1, n1);
        // 输出：不同路径的数量：3
        System.out.println("不同路径的数量：" + paths1);

        int m2 = 7, n2 = 3;
        int paths2 = new UniquePaths().uniquePaths(m2, n2);
        // 输出：不同路径的数量：28
        System.out.println("不同路径的数量：" + paths2);
    }

}
