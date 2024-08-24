package org.zyf.javabasic.letcode.hot100.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 寻找旋转排序数组中的最小值（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:13
 **/
public class FindMinSolution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            // 如果中间元素小于右端元素，则说明最小值在左侧或就是中间元素
            if (nums[mid] < nums[right]) {
                right = mid;
            } else {
                // 如果中间元素大于右端元素，则最小值在右侧
                left = mid + 1;
            }
        }

        return nums[left];
    }

    public static void main(String[] args) {
        FindMinSolution solution = new FindMinSolution();

        // 示例 1
        int[] nums1 = {3, 4, 5, 1, 2};
        System.out.println("Output: " + solution.findMin(nums1)); // 输出: 1

        // 示例 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Output: " + solution.findMin(nums2)); // 输出: 0

        // 示例 3
        int[] nums3 = {11, 13, 15, 17};
        System.out.println("Output: " + solution.findMin(nums3)); // 输出: 11
    }
}
