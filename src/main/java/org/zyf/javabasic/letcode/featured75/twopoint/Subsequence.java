package org.zyf.javabasic.letcode.featured75.twopoint;

/**
 * @program: zyfboot-javabasic
 * @description: 判断子序列
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:16
 **/
public class Subsequence {
    public boolean isSubsequence(String s, String t) {
        int n = s.length();  // s 的长度
        int m = t.length();  // t 的长度

        // 预处理：f[i][j] 表示在 t 的位置 i 之后，字符 j 的下一个出现位置
        int[][] f = new int[m + 1][26];

        // 初始化：t 的末尾之后所有字符的下一个位置都是 m
        for (int i = 0; i < 26; i++) {
            f[m][i] = m;
        }

        // 从后向前填充 f 数组
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                if (t.charAt(i) == j + 'a') {
                    // 如果当前字符是 j，则下一个位置是当前位置 i
                    f[i][j] = i;
                } else {
                    // 否则，下一个位置继承自 f[i + 1][j]
                    f[i][j] = f[i + 1][j];
                }
            }
        }

        int add = 0;  // 当前在 t 中的位置
        for (int i = 0; i < n; i++) {
            // 查找 s[i] 在 t 中的下一个出现位置
            if (f[add][s.charAt(i) - 'a'] == m) {
                // 如果找不到，返回 false
                return false;
            }
            // 更新 add 为 s[i] 的下一个出现位置 + 1
            add = f[add][s.charAt(i) - 'a'] + 1;
        }
        return true;  // 如果能遍历完 s，返回 true
    }

    // 测试方法
    public static void main(String[] args) {
        Subsequence subsequence = new Subsequence();
        // 基本解法测试
        System.out.println(subsequence.isSubsequence("abc", "ahbgdc")); // 输出: true
        System.out.println(subsequence.isSubsequence("axc", "ahbgdc")); // 输出: false
    }
}
