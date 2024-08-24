package org.zyf.javabasic.letcode.hot100.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 在排序数组中查找元素的第一个和最后一个位置（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:04
 **/
public class SearchRangeSolution {
    public int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) {
            return result;
        }

        // 找到目标值的左边界
        int left = findLeft(nums, target);
        if (left == -1) {
            return result; // 如果左边界都找不到，说明目标值不在数组中
        }

        // 找到目标值的右边界
        int right = findRight(nums, target);
        return new int[]{left, right};
    }

    private int findLeft(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                if (nums[mid] == target) {
                    index = mid;
                }
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return index;
    }

    private int findRight(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int index = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] <= target) {
                if (nums[mid] == target) {
                    index = mid;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return index;
    }

    public static void main(String[] args) {
        SearchRangeSolution solution = new SearchRangeSolution();

        // 示例 1
        int[] nums1 = {5, 7, 7, 8, 8, 10};
        int target1 = 8;
        int[] result1 = solution.searchRange(nums1, target1);
        System.out.println("Output: [" + result1[0] + "," + result1[1] + "]"); // 输出: [3,4]

        // 示例 2
        int[] nums2 = {5, 7, 7, 8, 8, 10};
        int target2 = 6;
        int[] result2 = solution.searchRange(nums2, target2);
        System.out.println("Output: [" + result2[0] + "," + result2[1] + "]"); // 输出: [-1,-1]

        // 示例 3
        int[] nums3 = {};
        int target3 = 0;
        int[] result3 = solution.searchRange(nums3, target3);
        System.out.println("Output: [" + result3[0] + "," + result3[1] + "]"); // 输出: [-1,-1]
    }
}
