package org.zyf.javabasic.letcode.hot100.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 乘积最大子数组 （中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:25
 **/
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        // 边界条件
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // 初始化最大和最小乘积为数组第一个元素
        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        // 从数组的第二个元素开始遍历
        for (int i = 1; i < nums.length; i++) {
            // 如果当前元素是负数，交换最大和最小乘积
            if (nums[i] < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            // 更新最大和最小乘积
            maxProduct = Math.max(nums[i], nums[i] * maxProduct);
            minProduct = Math.min(nums[i], nums[i] * minProduct);

            // 更新结果
            result = Math.max(result, maxProduct);
        }

        return result;
    }

    public static void main(String[] args) {
        MaximumProductSubarray mps = new MaximumProductSubarray();

        // 测试用例1
        int[] nums1 = {2, 3, -2, 4};
        System.out.println("测试用例1结果: " + mps.maxProduct(nums1)); // 输出：6

        // 测试用例2
        int[] nums2 = {-2, 0, -1};
        System.out.println("测试用例2结果: " + mps.maxProduct(nums2)); // 输出：0
    }
}
