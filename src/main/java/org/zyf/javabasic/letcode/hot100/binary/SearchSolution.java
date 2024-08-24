package org.zyf.javabasic.letcode.hot100.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 搜索旋转排序数组（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:09
 **/
public class SearchSolution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            if (nums[left] <= nums[mid]) { // 左半部分是有序的
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1; // 目标在左半部分
                } else {
                    left = mid + 1; // 目标在右半部分
                }
            } else { // 右半部分是有序的
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1; // 目标在右半部分
                } else {
                    right = mid - 1; // 目标在左半部分
                }
            }
        }

        return -1; // 目标值不在数组中
    }

    public static void main(String[] args) {
        SearchSolution solution = new SearchSolution();

        // 示例 1
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        int target1 = 0;
        System.out.println("Output: " + solution.search(nums1, target1)); // 输出: 4

        // 示例 2
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int target2 = 3;
        System.out.println("Output: " + solution.search(nums2, target2)); // 输出: -1

        // 示例 3
        int[] nums3 = {1};
        int target3 = 0;
        System.out.println("Output: " + solution.search(nums3, target3)); // 输出: -1
    }
}
