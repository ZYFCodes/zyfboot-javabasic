package org.zyf.javabasic.letcode.jd150.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 交错字符串
 * @author: zhangyanfeng
 * @create: 2024-08-25 20:05
 **/
public class Interleave {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        int l = s3.length();

        // 如果 s1 和 s2 的长度之和不等于 s3 的长度，则不能交错组成 s3
        if (m + n != l) {
            return false;
        }

        // 创建 dp 数组
        boolean[][] dp = new boolean[m + 1][n + 1];

        // 初始化 dp 数组
        dp[0][0] = true;

        // 初始化第一行
        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }

        // 初始化第一列
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }

        // 填充 dp 数组
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                        (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }

        // 返回结果
        return dp[m][n];
    }
}
