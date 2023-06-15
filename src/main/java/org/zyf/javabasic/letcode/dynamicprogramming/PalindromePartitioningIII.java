package org.zyf.javabasic.letcode.dynamicprogramming;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个字符串 s，将 s 分割成若干个非空子串，使得每个子串都是回文串。
 * 计算将字符串 s 分割成回文分割所需的最小分割次数。
 * 示例 1:输入: s = "aab”.   输出: 1
 * 解释: 将字符串 "aab" 分割成 ["aa","b"]，其中 "aa" 是回文串，所以只需要进行一次分割。
 * 示例 2:输入: s = "leetcode"    输出: 2
 * 解释: 将字符串 "leetcode" 分割成 ["leet","code"]，其中 "leet" 和 "code" 都是回文串，所以需要进行两次分割。
 * @date 2023/5/17  23:17
 */
public class PalindromePartitioningIII {
    /**
     * 这个问题可以使用动态规划来解决。首先，我们可以使用动态规划来计算任意子串是否是回文串，以便我们可以在后续的计算中使用。
     * 我们定义一个二维数组 dp，其中 dp[i][j] 表示将子串 s[i:j] 分割成回文子串所需的最小分割次数。
     * 初始化数组 dp 为无穷大，除了 dp[i][i] = 0，表示单个字符是回文串，不需要进行分割。
     * 然后，我们从长度为 2 的子串开始，逐步增加子串的长度，进行动态规划计算。
     * 对于每个子串 s[i:j]，我们枚举一个分割点 k，将子串分割成两部分 s[i:k] 和 s[k+1:j]，如果 s[i:k] 是回文串，则可以将其分割为一个回文子串，
     * 然后在 dp[i][j] 的基础上加 1，得到 dp[i][k] + dp[k+1][j] + 1。
     * 最后，dp[0][n-1] 就是将整个字符串分割成回文子串所需的最小分割次数，其中 n 是字符串的长度。
     */
    public int palindromePartition(String s, int k) {
        int n = s.length();
        int[][] dp = new int[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = getPalindromeCount(s, i, j);
            }
        }

        int[][] dp2 = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp2[i], Integer.MAX_VALUE / 2);
        }
        dp2[0][0] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= Math.min(i, k); j++) {
                for (int p = 0; p < i; p++) {
                    dp2[i][j] = Math.min(dp2[i][j], dp2[p][j - 1] + dp[p][i - 1]);
                }
            }
        }

        return dp2[n][k];
    }

    private int getPalindromeCount(String s, int i, int j) {
        int count = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                count++;
            }
            i++;
            j--;
        }
        return count;
    }

    public static void main(String[] args) {
        String s1 = "aab";
        int k1 = 1;
        int minCuts1 = new PalindromePartitioningIII().palindromePartition(s1, k1);
        System.out.println("将字符串 " + s1 + " 分割成回文子串所需的最小分割次数为：" + minCuts1);

        String s2 = "leetcode";
        int k2 = 2;
        int minCuts2 = new PalindromePartitioningIII().palindromePartition(s2, k2);
        System.out.println("将字符串 " + s2 + " 分割成回文子串所需的最小分割次数为：" + minCuts2);
    }
}
