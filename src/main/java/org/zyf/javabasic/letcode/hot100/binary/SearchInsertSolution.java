package org.zyf.javabasic.letcode.hot100.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 搜索插入位置（简单）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:54
 **/
public class SearchInsertSolution {
    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
        SearchInsertSolution solution = new SearchInsertSolution();

        // 示例 1
        int[] nums1 = {1, 3, 5, 6};
        int target1 = 5;
        System.out.println(solution.searchInsert(nums1, target1)); // 输出: 2

        // 示例 2
        int[] nums2 = {1, 3, 5, 6};
        int target2 = 2;
        System.out.println(solution.searchInsert(nums2, target2)); // 输出: 1

        // 示例 3
        int[] nums3 = {1, 3, 5, 6};
        int target3 = 7;
        System.out.println(solution.searchInsert(nums3, target3)); // 输出: 4
    }
}
