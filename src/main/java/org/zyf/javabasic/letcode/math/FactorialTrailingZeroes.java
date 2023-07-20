package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * <p>
 * 示例 1：输入：3.    输出：0
 * 解释：3! = 6，尾数中没有零。
 * 示例 2：输入：5      输出：1
 * 解释：5! = 120，尾数中有一个零。
 * <p>
 * 提示：
 * - 0 <= n <= 10^4
 * @date 2023/7/12  23:11
 */
public class FactorialTrailingZeroes {

    /**
     * 最优解法可以通过统计质因数 5 的个数来计算 n! 结果尾数中零的数量。
     * 在 n! 中，尾数中的零是由质因数 2 和质因数 5 相乘得到的。
     * 而在阶乘的过程中，2 的个数一定多于 5 的个数，因为偶数比 5 的倍数多得多。
     * 因此，计算 n! 结果尾数中零的数量，只需要计算质因数 5 的个数即可。
     * 具体步骤如下：
     * 1. 初始化一个变量 `count` 为 0，用于保存质因数 5 的个数。
     * 2. 遍历从 1 到 n 的每个整数 i，对每个 i 进行如下操作：
     * - 计算 i 的因数中 5 的个数，方法为将 i 除以 5，得到的结果为质因数 5 的个数。
     * - 将质因数 5 的个数累加到 `count` 中。
     * 3. 遍历完成后，`count` 即为 n! 结果尾数中零的数量。
     * 该算法的时间复杂度为 O(log(n))，其中 n 是给定的整数。
     * 因为需要遍历从 1 到 n 的每个整数 i 进行计算。
     * 而空间复杂度为 O(1)，只需要常数级的额外空间来保存计数器。
     */
    public static int trailingZeroes(int n) {
        // 初始化质因数 5 的个数
        int count = 0;

        // 遍历从 1 到 n 的每个整数 i
        for (int i = 1; i <= n; i++) {
            int num = i;
            // 计算 i 的因数中 5 的个数
            while (num % 5 == 0) {
                count++;
                num /= 5;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int n1 = 3;
        int n2 = 5;
        int n3 = 10;

        System.out.println("Input: " + n1 + " Output: " + trailingZeroes(n1));
        System.out.println("Input: " + n2 + " Output: " + trailingZeroes(n2));
        System.out.println("Input: " + n3 + " Output: " + trailingZeroes(n3));
    }
}
