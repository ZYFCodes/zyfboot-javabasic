package org.zyf.javabasic.letcode.math;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定两个整数 a 和 b，其中 a 是一个正整数，并且 b 是一个非常大的正整数，你需要计算 a 的超级次方。
 * 超级次方是指一个整数的 b 次方的末尾数字。
 * <p>
 * 示例 1：输入：a = 2, b = [3]     输出：8
 * 示例 2：输入：a = 2, b = [1, 0]     输出：1024
 * 示例 3：输入：a = 1, b = [4, 3, 2, 1, 0]     输出：1
 * 示例 4：输入：a = 2147483647, b = [2, 0]     输出：1198
 * <p>
 * 提示：
 * - 1 <= a <= 231 - 1
 * - 1 <= b.length <= 2000
 * - 0 <= b[i] <= 9
 * - b 不含前导 0
 * @date 2023/7/18  23:56
 */
public class SuperPow {

    /**
     * 最优解法可以通过递归和模幂运算来实现超级次方计算。
     * 递归的思想是将超级次方的计算拆分成两部分：
     * 1. 计算 a 的 b 的最后一位次方（也就是 a 的 b[b.length - 1] 次方）。
     * 2. 计算 a 的超级次方除去最后一位的部分。
     * 然后将这两部分相乘，得到最终的超级次方结果。
     * 模幂运算可以用于快速计算 a 的某次方对某个数 p 取模的结果。在本题中，需要将结果对 1337 取模。
     * 具体步骤如下：
     * 1. 定义一个递归函数 `powMod(a, k)`，用于计算 a 的 k 次方对 1337 取模的结果。
     * 2. 在 `powMod` 函数中，使用递归的方式计算 a 的超级次方除去最后一位的部分的 k 次方对 1337 取模的结果，并记为 `x`。
     * 3. 然后计算 a 的最后一位次方（也就是 a 的 b[b.length - 1] 次方）对 1337 取模的结果，并记为 `y`。
     * 4. 最终结果为 `x * y` 对 1337 取模的结果。
     * 该算法的时间复杂度为 O(logn)，其中 n 是数组 b 表示的非常大的正整数。
     * 因为在递归过程中，每次将 b 的长度减半，所以时间复杂度是对数级别。
     * 而空间复杂度为 O(logn)，因为递归的深度是 logn。
     */
    public static int superPow(int a, int[] b) {
        return powMod(a, b, b.length);
    }

    private static int powMod(int a, int[] b, int len) {
        if (len == 0) {
            return 1;
        }
        int lastDigit = b[len - 1];
        // 计算 a 的超级次方除去最后一位的部分的结果
        int x = powMod(a, b, len - 1);
        // 计算 a 的最后一位次方的结果
        int y = powMod(a, 1, lastDigit);
        // 将结果对 1337 取模并返回
        return x * y % 1337;
    }

    private static int powMod(int a, int k, int mod) {
        a %= mod;
        int result = 1;
        for (int i = 0; i < k; i++) {
            result = result * a % mod;
        }
        return result;
    }

    public static void main(String[] args) {
        int a1 = 2;
        int[] b1 = {3};
        int a2 = 2;
        int[] b2 = {1, 0};
        int a3 = 1;
        int[] b3 = {4, 3, 2, 1, 0};
        int a4 = 2147483647;
        int[] b4 = {2, 0};

        System.out.println("Input: a = " + a1 + ", b = " + Arrays.toString(b1) + " Output: " + superPow(a1, b1));
        System.out.println("Input: a = " + a2 + ", b = " + Arrays.toString(b2) + " Output: " + superPow(a2, b2));
        System.out.println("Input: a = " + a3 + ", b = " + Arrays.toString(b3) + " Output: " + superPow(a3, b3));
        System.out.println("Input: a = " + a4 + ", b = " + Arrays.toString(b4) + " Output: " + superPow(a4, b4));
    }
}
