package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 找到一个字符串中最长的回文子串（Longest Palindromic Substring）。回文子串是指正着读和倒着读都一样的连续字符序列。
 * 示例:
 * 输入: "babad"
 * 输出: "bab" 或 "aba"
 * @date 2021/1/2  23:53
 */
public class LongestPalindromicSubstring {

    /**
     * * 动态规划解法：
     * 创建一个二维数组 dp，其中 dp[i][j] 表示从第 i 个字符到第 j 个字符是否构成回文子串。
     * 初始化边界条件，即单个字符和相邻两个字符都是回文子串，即 dp[i][i] = true 和 dp[i][i+1] = true。
     * 对于长度大于 2 的子串，dp[i][j] 的值取决于 dp[i+1][j-1] 和 s.charAt(i) == s.charAt(j)。
     * 在动态规划的过程中，记录最长回文子串的起始位置和长度，从而得到最长的回文子串。
     */
    public static String longestPalindrome(String s) {
        int n = s.length();
        // 二维数组用于存储回文子串的信息
        boolean[][] dp = new boolean[n][n];
        // 最长回文子串的长度
        int maxLength = 1;
        // 最长回文子串的起始位置
        int start = 0;

        // 单个字符是回文子串
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // 相邻两个字符相同的话是回文子串
        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                maxLength = 2;
                start = i;
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
                    // 更新最长回文子串的长度
                    maxLength = len;
                    // 更新最长回文子串的起始位置
                    start = i;
                }
            }
        }

        // 根据最长回文子串的起始位置和长度提取出最长回文子串
        String longestPalindrome = s.substring(start, start + maxLength);

        return longestPalindrome;
    }

    public static void main(String[] args) {
        String s = "babad";
        String longestPalindrome = longestPalindrome(s);
        // 输出结果：Longest Palindromic Substring: bab
        System.out.println("Longest Palindromic Substring: " + longestPalindrome);
    }
}
