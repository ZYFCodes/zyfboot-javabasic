package org.zyf.javabasic.letcode.featured75.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 字符串的最大公因子
 * @author: zhangyanfeng
 * @create: 2024-08-23 22:18
 **/
public class GreatestCommonDivisorOfStrings {
    public static String gcdOfStrings(String str1, String str2) {
        // 如果 str1 + str2 和 str2 + str1 不相等，则说明不存在公共除数
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // 计算 str1 和 str2 长度的最大公因数
        int gcdLength = gcd(str1.length(), str2.length());

        // 返回最大公因数长度的子串
        return str1.substring(0, gcdLength);
    }

    // 辅助方法：计算两个整数的最大公因数（GCD）
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 测试方法
    public static void main(String[] args) {
        System.out.println(gcdOfStrings("ABCABC", "ABC")); // 输出: "ABC"
        System.out.println(gcdOfStrings("ABABAB", "ABAB")); // 输出: "AB"
        System.out.println(gcdOfStrings("LEET", "CODE"));   // 输出: ""
    }
}
