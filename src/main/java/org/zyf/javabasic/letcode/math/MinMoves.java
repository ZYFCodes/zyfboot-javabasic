package org.zyf.javabasic.letcode.math;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给定一个非空整数数组，找出使所有数组元素相等所需的最小移动次数。
 * 在每次移动中，你可以选择任意一个数组元素，将它增加 1 或减少 1。
 * 你可以假设数组的长度不超过 10000。
 * <p>
 * 示例 1：输入：[1, 2, 3]     输出：2
 * 解释：只需要将 2 减少 1，即可使数组元素都相等，最小移动次数为 2。
 * 示例 2：输入：[1, 10, 2, 9]     输出：16
 * 解释：只需要将 10 减少 8，2 增加 8，9 减少 8，即可使数组元素都相等，最小移动次数为 16。
 * <p>
 * 提示：
 * - 数组中的整数范围为 [-10^9, 10^9]。
 * - 数组的长度不超过 10000。
 * @date 2023/6/25  23:17
 */
public class MinMoves {

    /**
     * 最优解法可以通过对数组进行排序来实现。
     * 假设数组为 nums，排序后的数组为 sortedNums。
     * 要使所有数组元素相等，可以选择将数组元素调整到中位数的值，即 sortedNums[(n-1)/2]，其中 n 是数组长度。
     * 为什么选择中位数呢？
     * 因为中位数是一组数据中居中位置的数，可以保证使得数组元素相等时，移动次数最少。
     * 如果选择其他的数，比如最小值或最大值，那么需要更多的移动次数。
     * 具体步骤如下：
     * 1. 对数组 nums 进行排序，得到 sortedNums。
     * 2. 计算中位数值 median = sortedNums[(n-1)/2]。
     * 3. 遍历数组 nums，计算每个元素与 median 的差值的绝对值，并累加得到最小移动次数。
     * 该算法的时间复杂度为 O(nlogn)，其中 n 是数组长度。因为对数组进行排序需要 O(nlogn) 的时间。
     * 而空间复杂度为 O(n)，需要额外的数组来保存 sortedNums。
     */
    public static int minMoves(int[] nums) {
        // 对数组进行排序
        Arrays.sort(nums);

        int n = nums.length;
        // 计算中位数
        int median = nums[(n - 1) / 2];

        int minMoves = 0;
        // 遍历数组，计算每个元素与中位数的差值的绝对值，并累加得到最小移动次数
        for (int num : nums) {
            minMoves += Math.abs(num - median);
        }

        return minMoves;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {1, 10, 2, 9};

        System.out.println("Input: " + Arrays.toString(nums1) + " Output: " + minMoves(nums1));
        System.out.println("Input: " + Arrays.toString(nums2) + " Output: " + minMoves(nums2));
    }
}
