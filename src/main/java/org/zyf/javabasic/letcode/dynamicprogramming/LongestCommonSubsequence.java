package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 给定两个字符串 text1 和 text2，返回这两个字符串的最长公共子序列的长度。
 * 一个字符串的子序列是指这样一个新的字符串：
 * 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的公共子序列是这两个字符串所共同拥有的子序列。
 * 示例：
 * 输入：text1 = "abcde", text2 = "ace"    输出：3
 * 解释：最长公共子序列是 "ace"，其长度为 3。
 * @date 2023/5/18  23:23
 */
public class LongestCommonSubsequence {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        int result = new LongestCommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println("Longest Common Subsequence: " + result);

        text1 = "abc";
        text2 = "def";
        result = new LongestCommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println("Longest Common Subsequence: " + result);

        text1 = "AGGTAB";
        text2 = "GXTXAYB";
        result = new LongestCommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println("Longest Common Subsequence: " + result);

        text1 = "";
        text2 = "abcdef";
        result = new LongestCommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println("Longest Common Subsequence: " + result);

        text1 = "ABC";
        text2 = "";
        result = new LongestCommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println("Longest Common Subsequence: " + result);

        text1 = "";
        text2 = "";
        result = new LongestCommonSubsequence().longestCommonSubsequence(text1, text2);
        System.out.println("Longest Common Subsequence: " + result);
    }
}
