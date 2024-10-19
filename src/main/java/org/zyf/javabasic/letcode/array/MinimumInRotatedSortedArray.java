package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给定一个升序排列的数组，该数组在某个未知点上进行了旋转，
 * 需要找出数组中的最小值，数组可能包含重复元素。
 *
 * 示例 1：输入: [3,4,5,1,2] 输出: 1
 * 示例 2：输入: [2,2,2,0,1] 输出: 0
 *
 * 本题要求在包含重复元素的旋转排序数组中找到最小值，
 * 我们可以使用二分查找的思路进行求解。
 * 时间复杂度：O(log n) （最坏情况退化为 O(n)）
 */
public class MinimumInRotatedSortedArray {

    /**
     * 使用二分查找寻找旋转数组中的最小元素，允许数组中存在重复元素。
     * @param nums 旋转后的数组
     * @return 数组中的最小值
     */
    public int findMin(int[] nums) {
        // 初始化左右指针，left 指向数组开头，right 指向数组末尾
        int left = 0, right = nums.length - 1;

        // 当 left 和 right 没有相遇时，继续进行搜索
        while (left < right) {
            // 计算中间位置，避免溢出：mid = (left + right) / 2
            int mid = left + (right - left) / 2;

            // 解题思路：
            // 如果中间元素大于右边界元素，说明最小值一定在右半部分
            // 因为数组在旋转后，右边部分可能是未旋转的较小部分
            if (nums[mid] > nums[right]) {
                // 因此，我们将左边界 left 移动到 mid + 1
                left = mid + 1;
            }
            // 如果中间元素小于右边界元素，说明最小值在左半部分或就是 mid 本身
            else if (nums[mid] < nums[right]) {
                // 缩小右边界到 mid
                right = mid;
            }
            // 当 nums[mid] 等于 nums[right] 时，无法确定最小值在左边还是右边
            // 由于存在重复元素，我们可以安全地将 right 左移一位来缩小搜索范围
            else {
                right--; // 缩小右边界
            }
        }

        // 当 left 和 right 相遇时，最小值就是 nums[left] 或 nums[right]（相等）
        return nums[left];
    }

    public static void main(String[] args) {
        // 测试用例 1：无重复元素
        int[] nums1 = {3, 4, 5, 1, 2};
        // 测试用例 2：有重复元素
        int[] nums2 = {2, 2, 2, 0, 1};
        // 测试用例 3：无旋转的有序数组
        int[] nums3 = {11, 13, 15, 17};

        // 输出结果：最小值
        System.out.println(new MinimumInRotatedSortedArray().findMin(nums1)); // 输出: 1
        System.out.println(new MinimumInRotatedSortedArray().findMin(nums2)); // 输出: 0
        System.out.println(new MinimumInRotatedSortedArray().findMin(nums3)); // 输出: 11
    }
}