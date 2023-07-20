package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定两个整数，被除数和除数，将被除数除以除数并返回商，不能使用除法、乘法和取模运算。如果结果溢出，则返回最大的有符号32位整数（2^31 - 1）。
 * 例如：输入：被除数 dividend = 10，除数 divisor = 3    输出：商为 3
 * @date 2023/7/16  23:48
 */
public class DivideTwoIntegers {
    /**
     * 最优解法是使用二进制搜索（Binary Search）来解决这个问题。通过使用位运算和二进制搜索，我们可以高效地找到两数相除的商。
     * 首先，我们将被除数和除数都转换成绝对值，这是因为符号只影响最终的结果而不影响计算过程。然后，我们用二进制搜索的方式来逐步逼近商的值。
     * 我们从被除数中减去除数的倍数，每次将除数左移一位，直到减去除数的倍数小于除数。
     * 这样可以确保每次找到最大的倍数。然后，我们记录下找到的倍数，并将剩余的被除数继续进行二进制搜索。直到被除数小于除数时，搜索结束。
     * 最后，我们根据输入的符号来确定最终的商的符号，并根据题目要求检查结果是否溢出。
     * 这种二进制搜索解法的时间复杂度为O(log N)，其中N是被除数的绝对值，
     * 因为在每次搜索中，我们每次都能将被除数减少一半。空间复杂度为O(1)，因为只使用了有限的变量来保存结果。
     */
    public static int divide(int dividend, int divisor) {
        // 处理特殊情况：除数为0或者被除数为最小值且除数为-1（会导致溢出）
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            // 返回最大有符号32位整数表示无效
            return Integer.MAX_VALUE;
        }

        // 确定商的符号
        boolean negative = (dividend < 0) ^ (divisor < 0);

        // 将被除数和除数都转换为正数处理
        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        int result = 0;
        while (absDividend >= absDivisor) {
            long temp = absDivisor;
            long multiple = 1;
            // 通过二进制搜索，每次找到最大的倍数
            while (absDividend >= (temp << 1)) {
                temp <<= 1;
                multiple <<= 1;
            }
            absDividend -= temp;
            result += (int) multiple;
        }

        // 根据符号确定最终的商的符号
        return negative ? -result : result;
    }

    public static void main(String[] args) {
        int dividend = 10;
        int divisor = 3;
        int result = divide(dividend, divisor);
        // 输出：3
        System.out.println("商为: " + result);
    }
}
