package org.zyf.javabasic.letcode.dynamicprogramming;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组 nums，
 * 找到数组中乘积最大的连续子数组（该子数组中至少包含一个数字），返回其乘积。
 * 示例 1:输入: [2,3,-2,4]  输出: 6  解释: 子数组 [2,3] 有最大乘积 6。
 * 示例 2:输入: [-2,0,-1]   输出: 0  解释: 结果不能为负数，乘积为 0。
 * @date 2023/5/20  23:30
 */
public class MaximumProductSubarray {
    /**
     * 要找到乘积最大的连续子数组，我们需要考虑乘积的正负性质。
     * 由于乘积可能存在负数，负数乘以负数会变成正数，因此我们需要同时记录最大值和最小值。
     * <p>
     * 我们可以使用动态规划的方法来解决问题。
     * 定义两个变量 maxProduct 和 minProduct，分别表示当前乘积最大值和最小值。
     * 初始时，将 maxProduct 和 minProduct 都设置为数组的第一个元素 nums[0]。
     * 然后，我们遍历数组，对于每个元素 nums[i]，更新 maxProduct 和 minProduct 的值。
     * 如果当前元素为正数，则乘以 maxProduct 会得到更大的值；
     * 如果当前元素为负数，则乘以 minProduct 会得到更大的值。
     * 同时，还需要考虑当前元素自身是否大于 maxProduct，或者小于 minProduct，以保证连续性。
     * 最后，我们遍历完整个数组后，maxProduct 中存储的就是乘积最大的连续子数组的乘积。
     */
    public int maxProduct(int[] nums) {
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int tempMax = maxProduct;
            int tempMin = minProduct;

            maxProduct = Math.max(Math.max(tempMax * nums[i], tempMin * nums[i]), nums[i]);
            minProduct = Math.min(Math.min(tempMax * nums[i], tempMin * nums[i]), nums[i]);

            result = Math.max(result, maxProduct);
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 3, -2, 4};
        int maxProduct1 = new MaximumProductSubarray().maxProduct(nums1);
        System.out.println("最大乘积为：" + maxProduct1);

        int[] nums2 = {-2, 0, -1};
        int maxProduct2 = new MaximumProductSubarray().maxProduct(nums2);
        System.out.println("最大乘积为：" + maxProduct2);
    }
}
