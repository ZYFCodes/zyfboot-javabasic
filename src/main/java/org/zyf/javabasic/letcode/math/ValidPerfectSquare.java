package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个正整数 num，编写一个函数判断是否为有效的完全平方数。
 * 说明：不要使用任何内置的库函数，如 sqrt。
 * <p>
 * 示例 1：输入：num = 16      输出：true
 * 示例 2：输入：num = 14      输出：false
 * <p>
 * 提示：
 * - 1 <= num <= 2^31 - 1
 * @date 2023/7/13  23:14
 */
public class ValidPerfectSquare {

    /**
     * 最优解法可以通过二分查找来判断一个正整数是否为有效的完全平方数。
     * 具体步骤如下：
     * 1. 初始化两个变量 `left` 和 `right`，分别表示二分查找的左边界和右边界，初始值为 1 和 `num`。
     * 2. 在循环中，不断缩小查找范围，直到 `left` 大于等于 `right` 为止：
     * - 计算中间值 `mid`，等于 `left` 和 `right` 的平均值。
     * - 计算 `mid` 的平方值 `midSquared`。
     * - 如果 `midSquared` 等于 `num`，说明 `mid` 是有效的完全平方数，返回 true。
     * - 如果 `midSquared` 小于 `num`，说明完全平方数在右半部分，更新 `left` 为 `mid + 1`。
     * - 如果 `midSquared` 大于 `num`，说明完全平方数在左半部分，更新 `right` 为 `mid - 1`。
     * 3. 如果循环结束时仍未找到有效的完全平方数，返回 false。
     * 该算法的时间复杂度为 O(log(num))，其中 num 是给定的正整数。
     * 因为每次查找都会将查找范围缩小一半。而空间复杂度为 O(1)，只需要常数级的额外空间来保存变量。
     */
    public static boolean isPerfectSquare(int num) {
        // 二分查找的左边界
        long left = 1;
        // 二分查找的右边界
        long right = num;

        while (left <= right) {
            // 计算中间值
            long mid = left + (right - left) / 2;
            // 计算中间值的平方值
            long midSquared = mid * mid;

            if (midSquared == num) {
                // 如果平方值等于 num，说明找到了有效的完全平方数
                return true;
            } else if (midSquared < num) {
                // 如果平方值小于 num，说明完全平方数在右半部分
                left = mid + 1;
            } else {
                // 如果平方值大于 num，说明完全平方数在左半部分
                right = mid - 1;
            }
        }

        // 循环结束时仍未找到有效的完全平方数，返回 false
        return false;
    }

    public static void main(String[] args) {
        int num1 = 16;
        int num2 = 14;

        System.out.println("Input: " + num1 + " Output: " + isPerfectSquare(num1));
        System.out.println("Input: " + num2 + " Output: " + isPerfectSquare(num2));
    }
}
