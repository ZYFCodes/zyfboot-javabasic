package org.zyf.javabasic.letcode.hot100.multidimensional;

/**
 * @program: zyfboot-javabasic
 * @description: 不同路径（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:41
 **/
public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // 初始化第一行和第一列
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // 填充 dp 数组
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {
        UniquePaths up = new UniquePaths();

        // 测试用例1
        System.out.println("测试用例1结果: " + up.uniquePaths(3, 7)); // 输出：28

        // 测试用例2
        System.out.println("测试用例2结果: " + up.uniquePaths(3, 2)); // 输出：3

        // 测试用例3
        System.out.println("测试用例3结果: " + up.uniquePaths(7, 3)); // 输出：28

        // 测试用例4
        System.out.println("测试用例4结果: " + up.uniquePaths(3, 3)); // 输出：6
    }
}
