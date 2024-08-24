package org.zyf.javabasic.letcode.hot100.multidimensional;

/**
 * @program: zyfboot-javabasic
 * @description: 最长公共子序列 （中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:54
 **/
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // 创建 dp 数组
        int[][] dp = new int[m + 1][n + 1];

        // 填充 dp 数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // 返回最长公共子序列的长度
        return dp[m][n];
    }

    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();

        // 测试用例1
        String text1_1 = "abcde";
        String text2_1 = "ace";
        System.out.println("测试用例1结果: " + lcs.longestCommonSubsequence(text1_1, text2_1)); // 输出：3

        // 测试用例2
        String text1_2 = "abc";
        String text2_2 = "abc";
        System.out.println("测试用例2结果: " + lcs.longestCommonSubsequence(text1_2, text2_2)); // 输出：3

        // 测试用例3
        String text1_3 = "abc";
        String text2_3 = "def";
        System.out.println("测试用例3结果: " + lcs.longestCommonSubsequence(text1_3, text2_3)); // 输出：0
    }
}
