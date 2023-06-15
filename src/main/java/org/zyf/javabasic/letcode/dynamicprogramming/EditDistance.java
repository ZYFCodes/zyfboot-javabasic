package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 给定两个单词 word1 和 word2，计算出将 word1 转换成 word2 所使用的最少操作数。
 * 可以对一个单词执行以下三种操作：
 * 1. 插入一个字符
 * 2. 删除一个字符
 * 3. 替换一个字符
 * 示例 1:输入: word1 = "horse", word2 = "ros”.   输出: 3
 * 解释:
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * 示例 2:输入: word1 = "intention", word2 = "execution”.  输出: 5
 * 解释:
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * @date 2023/5/26  23:50
 */
public class EditDistance {
    /**
     * 这道题可以使用动态规划来解决。我们定义一个二维数组 dp，其中 dp[i][j] 表示将 word1 的前 i 个字符转换为 word2 的前 j 个字符所需要的最少操作数。
     * 考虑到转换过程中的三种操作：插入、删除和替换。我们可以分别考虑这三种操作对应的子问题：
     * 1. 插入操作：将 word1 的前 i 个字符转换为 word2 的前 j-1 个字符，然后插入一个字符得到 word2 的前 j 个字符。
     * 2. 删除操作：将 word1 的前 i-1 个字符转换为 word2 的前 j 个字符，然后删除 word1 的第 i 个字符。
     * 3. 替换操作：将 word1 的前 i-1 个字符转换为 word2 的前 j-1 个字符，然后将 word1 的第 i 个字符替换为 word2 的第 j 个字符。
     * 根据上述分析，可以得到状态转移方程：
     * dp[i][j] = min(dp[i][j-1] + 1,   // 插入操作
     * dp[i-1][j] + 1,   // 删除操作
     * dp[i-1][j-1] + cost)  // 替换操作
     * 其中，cost 表示 word1 的第 i 个字符和 word2 的第 j 个字符是否相等。如果相等，则 cost 为 0，否则为 1。
     * 初始条件为：当其中一个字符串为空时，转换的操作数为另一个字符串的长度。
     * 最终要求的答案是 dp[m][n]，其中 m 和 n 分别是 word1 和 word2 的长度。
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        // 初始化第一行和第一列
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        // 计算其他位置的最小操作数
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (word1.charAt(i - 1) == word2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(dp[i][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i - 1][j - 1] + cost));
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        int minOps1 = new EditDistance().minDistance(word1, word2);
        // 输出：将 "horse" 转换为 "ros" 的最少操作数：3
        System.out.println("将 \"" + word1 + "\" 转换为 \"" + word2 + "\" 的最少操作数：" + minOps1);

        String word3 = "intention";
        String word4 = "execution";
        int minOps2 = new EditDistance().minDistance(word3, word4);
        // 输出：将 "intention" 转换为 "execution" 的最少操作数：5
        System.out.println("将 \"" + word3 + "\" 转换为 \"" + word4 + "\" 的最少操作数：" + minOps2);
    }
}
