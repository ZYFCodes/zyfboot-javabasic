package org.zyf.javabasic.letcode.math;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 如果一个数列至少有三个元素，并且任意相邻元素之间的差值相同，则称其为等差数列。
 * 给定一个由 N 个元素组成的数列 A，返回 A 中所有等差数列的个数。
 * <p>
 * 示例 1：输入：[2, 4, 6, 8, 10]     输出：7
 * 解释：所有的等差数列为：[2, 4, 6]，[4, 6, 8]，[6, 8, 10]，[2, 4, 6, 8]，[4, 6, 8, 10]，[2, 4, 6, 8, 10]，[2, 6, 10]。
 * 示例 2：输入：[7, 7, 7, 7]     输出：3
 * 解释：所有的等差数列为：[7, 7, 7]，[7, 7, 7, 7]，[7, 7, 7, 7, 7]。
 * <p>
 * 提示：
 * - 1 <= A.length <= 1000
 * - -10^9 <= A[i] <= 10^9
 * @date 2023/7/19  23:00
 */
public class ArithmeticSlices {
    /**
     * 最优解法可以通过动态规划来实现等差数列划分的计算。
     * 动态规划的思想是从小规模的子问题开始，逐步推导到大规模的问题。
     * 在本题中，可以定义一个动态规划数组 dp，其中 dp[i] 表示以第 i 个元素为结尾的等差数列的个数。
     * 状态转移方程为：
     * - 如果 A[i] - A[i-1] == A[i-1] - A[i-2]，说明前三个元素可以组成一个等差数列，dp[i] = dp[i-1] + 1。
     * - 否则，dp[i] = 0，因为不能构成等差数列。
     * 最终的结果就是 dp 数组中所有元素的和，即所有等差数列的个数。
     * 具体步骤如下：
     * 1. 初始化动态规划数组 dp，长度为 N，并将所有元素初始化为 0。
     * 2. 从 i = 2 开始遍历数组 A，根据状态转移方程更新 dp 数组。
     * 3. 将 dp 数组中所有元素相加，得到最终结果。
     * 该算法的时间复杂度为 O(N)，其中 N 是数组 A 的长度。
     * 因为只需要遍历数组一次即可计算 dp 数组。而空间复杂度为 O(N)，需要额外的数组来保存 dp 数组。
     */
    public static int numberOfArithmeticSlices(int[] A) {
        int n = A.length;
        // 初始化动态规划数组 dp
        int[] dp = new int[n];
        // 初始化等差数列个数
        int count = 0;

        for (int i = 2; i < n; i++) {
            // 判断是否构成等差数列
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                // 更新 dp 数组
                dp[i] = dp[i - 1] + 1;
                // 更新等差数列个数
                count += dp[i];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] A1 = {2, 4, 6, 8, 10};
        int[] A2 = {7, 7, 7, 7};

        System.out.println("Input: " + Arrays.toString(A1) + " Output: " + numberOfArithmeticSlices(A1));
        System.out.println("Input: " + Arrays.toString(A2) + " Output: " + numberOfArithmeticSlices(A2));
    }
}
