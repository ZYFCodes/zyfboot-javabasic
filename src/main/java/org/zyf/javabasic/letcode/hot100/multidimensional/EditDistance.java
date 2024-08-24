package org.zyf.javabasic.letcode.hot100.multidimensional;

/**
 * @program: zyfboot-javabasic
 * @description: 编辑距离（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:59
 **/
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        // 创建 dp 数组
        int[][] dp = new int[m + 1][n + 1];

        // 初始化 dp 数组
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // 将 word1 的前 i 个字符转换为空字符串
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // 将空字符串转换为 word2 的前 j 个字符
        }

        // 填充 dp 数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(
                            Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }

        // 返回将 word1 转换为 word2 所需的最少操作数
        return dp[m][n];
    }

    public static void main(String[] args) {
        EditDistance ed = new EditDistance();

        // 测试用例1
        String word1_1 = "horse";
        String word2_1 = "ros";
        System.out.println("测试用例1结果: " + ed.minDistance(word1_1, word2_1)); // 输出：3

        // 测试用例2
        String word1_2 = "intention";
        String word2_2 = "execution";
        System.out.println("测试用例2结果: " + ed.minDistance(word1_2, word2_2)); // 输出：5
    }
}
