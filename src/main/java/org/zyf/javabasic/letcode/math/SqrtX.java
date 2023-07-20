package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数部分，小数部分将被舍去。
 * <p>
 * 示例 1：输入：x = 4    输出：2
 * 示例 2：输入：x = 8    输出：2
 * 解释：8 的平方根是 2.82842...，返回整数部分 2。
 * <p>
 * 提示：
 * - 0 <= x <= 2^31 - 1
 * @date 2023/7/6  23:45
 */
public class SqrtX {

    /**
     * 最优解法可以通过二分查找法来实现平方根的计算。
     * 1. 初始化两个变量 `left` 和 `right`，分别表示平方根的搜索范围，初始值为 0 和 `x`。
     * 2. 使用二分查找法在范围内查找平方根，不断更新 `left` 和 `right` 直到它们相邻或重合。
     * 3. 在每次二分查找中，计算 `mid` 作为 `left` 和 `right` 的中间值，然后计算 `mid` 的平方与 `x` 进行比较。
     * 4. 如果 `mid` 的平方小于等于 `x`，则将 `left` 更新为 `mid + 1`，继续向右搜索。
     * 5. 如果 `mid` 的平方大于 `x`，则将 `right` 更新为 `mid - 1`，继续向左搜索。
     * 6. 当二分查找结束时，返回 `right`，它即为平方根的整数部分。
     * 该算法的时间复杂度为 O(log(x))，其中 x 是输入的非负整数。
     * 因为在每次二分查找中，搜索范围将减少一半，所以时间复杂度是对数级别的。
     * 而空间复杂度为 O(1)，只使用了常数级的额外空间。
     */
    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int left = 0;
        int right = x;

        while (left <= right) {
            // 防止整型溢出
            int mid = left + (right - left) / 2;
            // 使用 long 类型防止平方时整型溢出
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        // 当循环结束时，right 即为平方根的整数部分
        return right;
    }

    public static void main(String[] args) {
        int x1 = 4;
        int x2 = 8;

        System.out.println("Input: " + x1 + " Output: " + mySqrt(x1));
        System.out.println("Input: " + x2 + " Output: " + mySqrt(x2));
    }
}
