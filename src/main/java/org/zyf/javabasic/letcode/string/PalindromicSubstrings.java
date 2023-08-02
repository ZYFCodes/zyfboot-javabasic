package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 给定一个字符串，计算其中有多少个回文子串（Palindromic Substrings）。
 * 回文子串是指正着读和倒着读都相同的连续字符序列。
 * 示例：
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串分别是 "a", "b", "c"。
 * @date 2021/1/25  23:16
 */
public class PalindromicSubstrings {

    /**
     * * 动态规划解法：
     * 创建一个二维数组 dp，其中 dp[i][j] 表示从第 i 个字符到第 j 个字符是否构成回文子串。
     * 初始化边界条件，即单个字符和相邻两个字符都是回文子串，即 dp[i][i] = true 和 dp[i][i+1] = true。
     * 对于长度大于 2 的子串，dp[i][j] 的值取决于 dp[i+1][j-1] 和 s.charAt(i) == s.charAt(j)。
     * 在动态规划的过程中，每次找到 dp[i][j] 为 true 时，说明从第 i 个字符到第 j 个字符构成回文子串，回文子串的个数加 1。
     */
    public static int countSubstrings(String s) {
        int n = s.length();
        // 二维数组用于存储回文子串的信息
        boolean[][] dp = new boolean[n][n];
        // 回文子串的个数
        int count = 0;

        // 单个字符是回文子串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        // 相邻两个字符相同的话是回文子串
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }

        // 动态规划，从长度为 3 的子串开始判断是否为回文子串
        for (int len = 3; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                // 子串的结束位置
                int j = i + len - 1;

                // 判断子串是否为回文子串
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    // 更新回文子串的个数
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String s = "abc";
        int count = countSubstrings(s);
        // 输出结果：Number of Palindromic Substrings: 3
        System.out.println("Number of Palindromic Substrings: " + count);
    }
}
