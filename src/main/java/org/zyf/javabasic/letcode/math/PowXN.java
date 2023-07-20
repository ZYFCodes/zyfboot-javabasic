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

    /**
     * 最优解法可以通过快速幂算法来实现计算 x 的 n 次幂。
     * 快速幂算法是一种高效的计算幂的方法，基本思想是将幂 n 分解成二进制形式，然后利用二进制幂的特性来进行计算。具体步骤如下：
     * 1. 初始化一个变量 `result` 用于保存最终的计算结果，初始值为 1。
     * 2. 将 n 表示为二进制形式，例如 n = 10 的二进制为 1010。
     * 3. 从低位到高位遍历 n 的二进制位，如果某一位为1，则将 x 的幂乘到 `result` 中，否则继续处理下一位。
     * 4. 在每一步中，将 x 的幂平方，以便在下一位中使用。
     * 举例来说，计算 x 的 10 次幂时，n = 10 的二进制为 1010，可以看作 2^3 * 2^1，所以可以通过以下步骤计算：
     * 1. x = x^1，得到 x = x^2
     * 2. x = x^2，得到 x = x^4
     * 3. x = x^4，得到 x = x^8
     * 4. x = x^8 * x^2，得到 x = x^10
     * 该算法的时间复杂度为 O(log(n))
     */
    public static double zyfPow(double x, int n) {
        // 边界情况，当 n = 0 时，任何数的 0 次幂均为 1
        if (n == 0) {
            return 1;
        }

        // 处理 n 为负数的情况
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }

        double result = 1;
        double temp = x;

        // 快速幂算法，将 n 表示为二进制形式，从低位到高位遍历
        while (n > 0) {
            // 如果当前位是1，将 x 的幂乘到 result 中
            if (n % 2 == 1) {
                result *= temp;
            }
            // 将 x 的幂平方，以便在下一位中使用
            temp *= temp;
            // 右移一位，相当于 n = n / 2
            n /= 2;
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

        System.out.println("Input: " + x1 + ", " + n1 + " Output: " + zyfPow(x1, n1));
        System.out.println("Input: " + x2 + ", " + n2 + " Output: " + zyfPow(x2, n2));
        System.out.println("Input: " + x3 + ", " + n3 + " Output: " + zyfPow(x3, n3));
    }
}
