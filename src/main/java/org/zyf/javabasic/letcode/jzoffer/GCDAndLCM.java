package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 给定两个正整数 a 和 b，求它们的最大公约数和最小公倍数。
 * @date 2023/6/16  23:39
 */
public class GCDAndLCM {
    /**
     * 最大公约数 - Greatest Common Divisor (GCD)
     */
    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * 最小公倍数 - Least Common Multiple (LCM)
     */
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) {
        GCDAndLCM solution = new GCDAndLCM();
        int a = 12;
        int b = 18;
        int gcd = solution.gcd(a, b);
        int lcm = solution.lcm(a, b);
        // 输出: 6
        System.out.println("最大公约数: " + gcd);
        // 输出: 36
        System.out.println("最小公倍数: " + lcm);
    }
}
