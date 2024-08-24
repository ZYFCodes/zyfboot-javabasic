package org.zyf.javabasic.letcode.hot100.multidimensional;

/**
 * @program: zyfboot-javabasic
 * @description: 最长回文子串（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:50
 **/
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        if (n == 0) return "";

        boolean[][] dp = new boolean[n][n];
        String longest = "";
        int maxLength = 0;

        for (int length = 1; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                if (length == 1) {
                    dp[i][j] = true;
                } else if (length == 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                }
                if (dp[i][j] && length > maxLength) {
                    maxLength = length;
                    longest = s.substring(i, j + 1);
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring lps = new LongestPalindromicSubstring();

        // 测试用例1
        String s1 = "babad";
        System.out.println("测试用例1结果: " + lps.longestPalindrome(s1)); // 输出："bab" 或 "aba"

        // 测试用例2
        String s2 = "cbbd";
        System.out.println("测试用例2结果: " + lps.longestPalindrome(s2)); // 输出："bb"
    }
}
