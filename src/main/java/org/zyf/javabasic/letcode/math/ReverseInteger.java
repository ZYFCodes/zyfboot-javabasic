package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个 32 位有符号整数 x，将整数 x 反转。
 * 如果反转后的整数超出了 32 位有符号整数的范围 [-2^31, 2^31 - 1]，则返回 0。
 * 注意：假设我们的环境只能存储得下这个 32 位整数的范围，故请不要使用 64 位整数类型。
 * <p>
 * 示例 1：输入：x = 123      输出：321
 * 示例 2：输入：x = -123    输出：-321
 * 示例 3：输入：x = 120      输出：21
 * 示例 4：输入：x = 0          输出：0
 * <p>
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 * 最优解法分析
 * @date 2023/7/10  23:27
 */
public class ReverseInteger {
    /**
     * 最优解法可以通过以下步骤来实现整数反转：
     * 1. 初始化一个变量 `result` 用于保存反转后的结果，初始值为0。
     * 2. 使用循环，将输入整数 `x` 的每一位依次取出，然后加入到 `result` 中。可以使用取模运算和除法运算来获取每一位的值。
     * 3. 在每一步中，需要检查 `result` 是否会溢出 32 位有符号整数的范围。
     * 即检查 `result` 是否小于最小值 `-2^31` 或大于最大值 `2^31 - 1`，如果是，则返回0。
     * 4. 循环结束后，`result` 就是反转后的整数，将其返回。
     * 在此算法中，不需要使用额外的数据结构，只用了常数级的额外空间，因此是最优解法。
     * 同时，算法的时间复杂度为 O(log10(x))，其中 x 是输入整数的位数。
     * 这是因为算法的循环次数与输入整数的位数相关。
     */
    public static int reverse(int x) {
        int result = 0;
        while (x != 0) {
            // 获取当前x的最后一位
            int digit = x % 10;
            // 判断反转后是否会溢出范围
            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            if (result < Integer.MIN_VALUE / 10
                    || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }
            // 将当前位加入到结果中
            result = result * 10 + digit;
            // 去掉x的最后一位
            x /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
        int x1 = 123;
        int x2 = -123;
        int x3 = 120;
        int x4 = 0;

        System.out.println("Input: " + x1 + " Output: " + reverse(x1));
        System.out.println("Input: " + x2 + " Output: " + reverse(x2));
        System.out.println("Input: " + x3 + " Output: " + reverse(x3));
        System.out.println("Input: " + x4 + " Output: " + reverse(x4));
    }
}
