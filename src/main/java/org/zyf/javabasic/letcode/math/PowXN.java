package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1：输入：x = 2.00000, n = 10     输出：1024.00000
 * 示例 2：输入：x = 2.10000, n = 3.      输出：9.26100
 * 示例 3：输入：x = 2.00000, n = -2     输出：0.25000
 * 解释：2^-2 = 1/2^2 = 1/4 = 0.25
 * <p>
 * 提示：
 * - -100.0 < x < 100.0
 * - -2^31 <= n <= 2^31-1
 * - -10^4 <= x^n <= 10^4
 * 注意：解决此问题时，请不要使用库函数来实现 pow(x, n) 。
 * @date 2023/7/7  23:48
 */
public class PowXN {

    public static double myPow(double x, int n) {
        // 处理 n 为负数的情况
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }

        double result = 1.0;
        double currentProduct = x;

        // 迭代计算 x 的 N 次幂
        while (N > 0) {
            // 如果当前指数为奇数，则将当前 x 乘入结果中
            if ((N % 2) == 1) {
                result *= currentProduct;
            }
            // 将指数减半，x 平方
            currentProduct *= currentProduct;
            N /= 2;
        }

        return result;
    }

    public static void main(String[] args) {
        double x1 = 2.00000;
        int n1 = 10;

        double x2 = 2.10000;
        int n2 = 3;

        double x3 = 2.00000;
        int n3 = -2;

        System.out.println("Input: " + x1 + ", " + n1 + " Output: " + myPow(x1, n1));
        System.out.println("Input: " + x2 + ", " + n2 + " Output: " + myPow(x2, n2));
        System.out.println("Input: " + x3 + ", " + n3 + " Output: " + myPow(x3, n3));
    }
}
