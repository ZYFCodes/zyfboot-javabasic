package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 请实现一个函数用来匹配包含’.‘和’‘的正则表达式。
 * 模式中的字符’.‘表示任意一个字符，而’’表示它前面的字符可以出现任意次（包含0次）。
 * 本题中，匹配是指字符串的所有字符匹配整个模式。
 * @date 2023/6/4  00:43
 */
public class StrMatch {
    /**
     * 我们可以使用动态规划的方法来解决这个问题。
     * 定义一个二维布尔数组dp，其中dp[i][j]表示字符串的前i个字符和模式的前j个字符是否匹配。
     * 初始化dp[0][0]为true，表示空字符串和空模式是匹配的。然后进行状态转移，分为以下几种情况：
     * 	1.	当模式的第j个字符不是’*’时，如果字符串的第i个字符和模式的第j个字符匹配，
     * 	    即s[i] == p[j]或p[j] == ‘.’，那么只需判断前i-1个字符和前j-1个字符是否匹配，即dp[i][j] = dp[i-1][j-1]。
     * 	2.	当模式的第j个字符是’*’时，表示它前面的字符可以出现任意次数（包括0次）。此时有两种情况：
     * 	•	’‘前面的字符不匹配字符串的当前字符，即s[i] != p[j-1]，那么只能让’’前面的字符出现0次，
     * 	    所以将模式向后移动两个字符，即dp[i][j] = dp[i][j-2]。
     * 	•	’’前面的字符匹配字符串的当前字符，即s[i] == p[j-1]或p[j-1] == ‘.’。
     * 	    那么有两种选择：一种是让’‘前面的字符出现0次，所以将模式向后移动两个字符，即dp[i][j] = dp[i][j-2]；
     * 	    另一种是让’*’前面的字符出现多次，所以将字符串向后移动一个字符，即dp[i][j] = dp[i-1][j]。
     * 最终结果保存在dp[m][n]中，其中m和n分别是字符串和模式的长度。
     */
    public boolean match(char[] str, char[] pattern) {
        // 字符串的长度
        int m = str.length;
        // 模式的长度
        int n = pattern.length;
        // dp数组用于保存匹配结果，默认值为false
        boolean[][] dp = new boolean[m + 1][n + 1];
        // 空字符串和空模式匹配为true
        dp[0][0] = true;

        for (int i = 0; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (pattern[j - 1] != '*') {
                    // 当前模式字符不是'*'
                    if (i > 0 && (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.')) {
                        // 当前字符串字符和模式字符匹配，则结果取决于前一个字符的匹配结果
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                } else {
                    // 当前模式字符是'*'
                    if (j >= 2) {
                        // 忽略当前模式字符和'*'，即跳过'*'
                        dp[i][j] |= dp[i][j - 2];
                    }
                    if (i >= 1 && j >= 2 && (str[i - 1] == pattern[j - 2] || pattern[j - 2] == '.')) {
                        // 字符串字符和模式'*'前的字符匹配，则结果取决于前一个字符串的匹配结果
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        // 返回字符串和模式是否匹配的结果
        return dp[m][n];
    }

    public static void main(String[] args) {
        StrMatch solution = new StrMatch();
        // 测试用例字符串
        char[] str = "aab".toCharArray();
        // 测试用例模式
        char[] pattern = "c*a*b".toCharArray();
        // 调用match函数进行匹配
        boolean result = solution.match(str, pattern);
        // 输出匹配结果，true表示匹配成功，false表示匹配失败
        System.out.println(result);
    }

}
