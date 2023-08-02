package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 找到两个字符串中最长的公共子串（Longest Common Substring）。公共子串是指在两个字符串中同时出现的连续字符序列。
 * 示例:
 * 输入: "ABABC", "BABCA"
 * 输出: "BABC"
 * @date 2021/2/2  23:28
 */
public class LongestCommonSubstring {
    /**
     * 这个问题要求我们找到两个字符串中最长的连续公共子串。为了得到最长的公共子串，我们可以使用动态规划来解决。
     * 动态规划的思想是通过构建一个二维数组来存储中间结果，然后根据当前字符是否相同来更新数组的值，从而找到最长的公共子串。具体的动态规划算法如下：
     * 1.创建一个二维数组 dp，其中 dp[i][j] 表示以第一个字符串的第 i 个字符和第二个字符串的第 j 个字符结尾的公共子串的长度。
     * 2.初始化边界条件，即当第一个字符串的第 i 个字符和第二个字符串的第 j 个字符不相同时，dp[i][j] 的值为 0。
     * 3.对于其余情况，如果第一个字符串的第 i 个字符和第二个字符串的第 j 个字符相同，
     * 那么 dp[i][j] 的值等于 dp[i-1][j-1] + 1，表示当前字符构成的公共子串的长度是前一个字符构成的公共子串长度加上当前字符。
     * 4.在动态规划的过程中，记录最长的公共子串的长度和对应的结束位置，从而得到最长的公共子串。
     * 5.最后，根据记录的最长公共子串的结束位置，从第一个字符串中提取出最长公共子串并返回。
     * 这样，我们就可以找到两个字符串中最长的公共子串。
     */
    public static String longestCommonSubstring(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];
        // 最长公共子串的长度
        int maxLength = 0;
        // 最长公共子串在 str1 中的结束索引
        int endIndex = 0;

        // 动态规划，计算公共子串的长度
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        // 更新最长公共子串的结束索引
                        endIndex = i;
                    }
                }
            }
        }

        // 根据最长公共子串的结束索引提取出最长公共子串
        String longestCommon = str1.substring(endIndex - maxLength, endIndex);

        return longestCommon;
    }

    public static void main(String[] args) {
        String str1 = "ABABC";
        String str2 = "BABCA";
        String longestCommon = longestCommonSubstring(str1, str2);
        // 输出结果：Longest Common Substring: BABC
        System.out.println("Longest Common Substring: " + longestCommon);
    }
}
