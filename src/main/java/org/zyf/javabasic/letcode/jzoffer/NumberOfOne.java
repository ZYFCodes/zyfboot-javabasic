package org.zyf.javabasic.letcode.jzoffer;

/**
 * @author yanfengzhang
 * @description 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。
 * 例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。
 * @date 2023/6/6  22:28
 */
public class NumberOfOne {
    /**
     * 要计算一个整数的二进制表示中 1 的个数，可以利用位运算的特性。
     * 一个整数 n 与 1 进行按位与运算（n & 1），如果结果为 1，则说明 n 的二进制表示最右边一位是 1；
     * 如果结果为 0，则说明 n 的二进制表示最右边一位是 0。然后，将 n 右移一位（n >>= 1），
     * 继续进行上述操作，直到 n 变为 0。统计结果为 1 的次数即为所求。
     *
     * 具体步骤如下：
     * 	1.	初始化计数器 count 为 0。
     * 	2.	循环判断 n 是否为 0，若不为 0，执行以下步骤：
     * 	•	判断 n 的最右位是否为 1：n & 1，若结果为 1，count 加 1。
     * 	•	右移 n 一位：n >>= 1。
     * 	3.	返回计数器 count。
     * 这种解法的时间复杂度为 O(log₂n)，其中 n 为输入的整数。
     * 因为在每一次循环中，n 都会右移一位，循环的次数取决于 n 的二进制位数。
     */
    public int numberOfOne(int n) {
        // 用于记录1的个数
        int count = 0;
        while (n != 0) {
            if ((n & 1) == 1) {
                // n的最右位为1，计数器加1
                count++;
            }
            // 右移一位
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfOne solution = new NumberOfOne();
        // 测试样例
        int n = 9;
        int count = solution.numberOfOne(n);
        System.out.println("Number of 1s in " + n + "'s binary representation: " + count);
    }
}
