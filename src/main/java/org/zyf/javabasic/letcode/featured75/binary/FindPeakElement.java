package org.zyf.javabasic.letcode.featured75.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 寻找峰值
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:34
 **/
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        // 二分查找
        while (left < right) {
            int mid = left + (right - left) / 2;

            // 比较中间位置和右侧位置的值
            if (nums[mid] < nums[mid + 1]) {
                // 峰值在右侧
                left = mid + 1;
            } else {
                // 峰值在左侧或就是中间位置
                right = mid;
            }
        }

        // 此时 left == right，返回任何一个位置作为峰值位置
        return left;
    }

    public static void main(String[] args) {
        FindPeakElement sol = new FindPeakElement();

        // 示例 1
        int[] nums1 = {1, 2, 3, 1};
        int peak1 = sol.findPeakElement(nums1);
        System.out.println("示例 1 - 峰值元素的索引: " + peak1);
        // 应输出 2，因为 nums[2] = 3 是一个峰值

        // 示例 2
        int[] nums2 = {1, 2, 1, 3, 5, 6, 4};
        int peak2 = sol.findPeakElement(nums2);
        System.out.println("示例 2 - 峰值元素的索引: " + peak2);
        // 应输出 1 或 5，因为 nums[1] = 2 和 nums[5] = 6 都是峰值
    }
}
