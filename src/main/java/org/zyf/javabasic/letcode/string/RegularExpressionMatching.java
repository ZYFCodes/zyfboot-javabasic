package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 实现字符串的正则表达式匹配，支持通配符和正则符号的处理，要求高效且正确。
 * 正则表达式中通常包含特殊符号和通配符，
 * 例如 . 表示匹配任意单个字符，* 表示匹配前一个字符零次或多次，+ 表示匹配前一个字符一次或多次等
 * 要求实现一个高效且正确的算法来实现字符串的正则表达式匹配。
 * @date 2020/2/1  23:22
 */
public class RegularExpressionMatching {

    /**
     * 假设原始字符串为 s，正则表达式为 p，
     * 我们可以定义一个二维数组 dp 来表示匹配状态，其中 dp[i][j] 表示原始字符串的前 i 个字符和正则表达式的前 j 个字符是否匹配。
     * 根据题目要求，我们需要考虑以下几种情况来更新 dp 数组的值：
     * 如果 s[i-1] 和 p[j-1] 相同，或者 p[j-1] 是通配符 .，则 dp[i][j] 的值取决于 dp[i-1][j-1] 的值。
     * 如果 p[j-1] 是通配符 *，则有两种情况：
     * a. * 表示前一个字符可以匹配零次，即 dp[i][j] 取决于 dp[i][j-2] 的值。
     * b. * 表示前一个字符至少匹配一次，即 dp[i][j] 取决于 dp[i-1][j] 的值，前提是 s[i-1] 和 p[j-2] 匹配。
     * 基于以上分析，我们可以得到动态规划的转移方程：
     * if s[i-1] == p[j-1] or p[j-1] == '.':
     * dp[i][j] = dp[i-1][j-1]
     * elif p[j-1] == '*':
     * dp[i][j] = dp[i][j-2] or (dp[i-1][j] and (s[i-1] == p[j-2] or p[j-2] == '.'))
     * 边界条件为 dp[0][0] = True，表示空字符串和空正则表达式是匹配的
     * 此外，空字符串和正则表达式的匹配状态可以根据特定规则计算出，作为初始化。
     * 最终，当 dp[s.length()][p.length()] 为 True 时，表示原始字符串和正则表达式完全匹配。
     * 这个动态规划解法的时间复杂度是 O(mn)，其中 m 和 n 分别是原始字符串和正则表达式的长度。
     * 由于使用了二维数组来存储中间结果，空间复杂度也是 O(mn)。
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 空字符串和空正则表达式是匹配的
        dp[0][0] = true;

        // 初始化第一行，空正则表达式与非空字符串的匹配状态
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        // 动态规划，计算匹配状态
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if (sc == pc || pc == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    dp[i][j] = dp[i][j - 2] || (dp[i - 1][j] && (sc == p.charAt(j - 2) || p.charAt(j - 2) == '.'));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "aa";
        String p1 = "a*";
        boolean result1 = isMatch(s1, p1);
        // 输出结果：true
        System.out.println("Result for s1 and p1: " + result1);

        String s2 = "mississippi";
        String p2 = "mis*is*p*.";
        boolean result2 = isMatch(s2, p2);
        // 输出结果：false
        System.out.println("Result for s2 and p2: " + result2);
    }
}
