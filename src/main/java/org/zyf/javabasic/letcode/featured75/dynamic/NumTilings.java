package org.zyf.javabasic.letcode.featured75.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 多米诺和托米诺平铺
 * @author: zhangyanfeng
 * @create: 2024-08-24 13:07
 **/
public class NumTilings {
    static final int MOD = 1000000007;

    public int numTilings(int n) {
        // dp 数组定义：dp[i][j] 代表填充 2x i 面板的状态 j
        int[][] dp = new int[n + 1][4];
        dp[0][3] = 1; // 初始状态，空面板的填充方式

        for (int i = 1; i <= n; i++) {
            // 当前列的状态 0 是前一列为状态 3
            dp[i][0] = dp[i - 1][3];

            // 当前列的状态 1 是前一列为状态 0 和 2
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;

            // 当前列的状态 2 是前一列为状态 0 和 1
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;

            // 当前列的状态 3 是前一列的所有状态的和
            dp[i][3] = (((dp[i - 1][0] + dp[i - 1][1]) % MOD + dp[i - 1][2]) % MOD + dp[i - 1][3]) % MOD;
        }

        // 返回填充 2x n 面板的所有有效方法数量
        return dp[n][3];
    }

    public static void main(String[] args) {
        NumTilings solution = new NumTilings();

        // 测试用例 1
        int n1 = 3;
        System.out.println(solution.numTilings(n1)); // 预期输出: 5

        // 测试用例 2
        int n2 = 1;
        System.out.println(solution.numTilings(n2)); // 预期输出: 1

        // 测试用例 3: 边界情况
        int n3 = 2;
        System.out.println(solution.numTilings(n3)); // 预期输出: 3

        // 测试用例 4: 较长输入
        int n4 = 5;
        System.out.println(solution.numTilings(n4)); // 预期输出: 21
    }
}
