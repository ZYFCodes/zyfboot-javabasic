package org.zyf.javabasic.letcode.string;

/**
 * @author yanfengzhang
 * @description 将一个字符串分割成一些子串，使得每个子串都是回文字符串，求最小的分割次数（Minimum Cuts）。
 * 即找到最少分割次数，将原始字符串划分为若干个回文子串。
 * 示例：
 * 输入: "aab"
 * 输出: 1
 * 解释: 将字符串划分为 "aa" 和 "b" 两个回文子串，最少分割次数为 1。
 * @date 2021/1/24  14:01
 */
public class PalindromePartitioning {

    /**
     * 这个问题属于动态规划问题。要求最小分割次数，我们可以使用动态规划来解决。
     * 动态规划的思路是：
     * * 创建一个一维数组 dp，其中 dp[i] 表示从字符串的第 i 个字符到末尾的最小分割次数。
     * * 初始化数组 dp，dp[i] 的初始值为从第 i 个字符到末尾的最大分割次数，即 dp[i] = len - i - 1，其中 len 是字符串的长度。
     * * 从右往左遍历字符串的每个字符，对于每个字符，从该字符开始向右遍历，
     * 判断以该字符为起点到右边任意字符位置的子串是否为回文字符串，
     * 如果是回文字符串，更新 dp[i] 为 dp[j+1] + 1，其中 j 是回文字符串右边界的索引。
     * * 最终，dp[0] 就是最小分割次数。
     */
    public static int minCut(String s) {
        int len = s.length();
        int[] dp = new int[len];

        // 初始化 dp 数组，dp[i] 表示从第 i 个字符到末尾的最大分割次数
        for (int i = 0; i < len; i++) {
            dp[i] = len - i - 1;
        }

        // 从右往左遍历字符串的每个字符
        for (int i = len - 1; i >= 0; i--) {
            // 从该字符开始向右遍历，判断以该字符为起点到右边任意字符位置的子串是否为回文字符串
            for (int j = i; j < len; j++) {
                if (isPalindrome(s, i, j)) {
                    // 如果是回文字符串，更新 dp[i] 为 dp[j+1] + 1，其中 j 是回文字符串右边界的索引
                    if (j == len - 1) {
                        // 如果整个子串是回文字符串，不需要分割
                        dp[i] = 0;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j + 1] + 1);
                    }
                }
            }
        }

        // 最终，dp[0] 就是最小分割次数
        return dp[0];
    }

    /**
     * 判断字符串 s 中从 left 到 right 的子串是否是回文字符串
     */
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        int minCut = minCut(s);
        // 输出结果：Minimum Cuts: 1
        System.out.println("Minimum Cuts: " + minCut);
    }
}
