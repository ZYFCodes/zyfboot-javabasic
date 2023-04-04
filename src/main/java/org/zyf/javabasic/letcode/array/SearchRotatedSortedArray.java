package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组 nums ，按升序排列，数组中的元素各不相同。
 * nums 可以在预先未知的某个点上进行旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标从 0 开始计数）。
 * 例如，数组 [0,1,2,4,5,6,7] 在变化后可能变为 [4,5,6,7,0,1,2] 。
 * <p>
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1。
 * 示例1:输入：nums = [4,5,6,7,0,1,2], target = 0 输出：4
 * 示例2：输入：nums = [4,5,6,7,0,1,2], target = 3 输出：-1
 * 示例3：输入：nums = [1], target = 0 输出：-1
 * <p>
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二`
 * 题目数据保证 nums 在预先未知的某个点上进行了旋转
 * -10^4 <= target <= 10^4
 * @date 2023/4/1  22:54
 */
public class SearchRotatedSortedArray {

    /**
     * 搜索旋转排序数组问题的最优解法是二分查找算法，时间复杂度为 O(log n)。
     * 该算法的关键在于确定哪一部分是有序的。我们可以将数组分为两部分，左半部分 [left, mid] 和右半部分 [mid + 1, right]，根据这个分界点，数组中必定有一半是有序的。
     * 我们首先通过比较 nums[mid] 和 nums[left] 的值，可以确定左半部分 [left, mid] 是否有序。
     * 如果 nums[mid] >= nums[left]，说明左半部分有序，否则右半部分 [mid + 1, right] 有序。
     * 如果左半部分有序，那么我们可以根据 nums[left] <= target < nums[mid]，将目标值 target 限制在左半部分。否则，将目标值 target 限制在右半部分。
     * 同样的，如果右半部分有序，那么我们可以根据 nums[mid] < target <= nums[right]，将目标值 target 限制在右半部分。否则，将目标值 target 限制在左半部分。
     * 接下来，我们只需要在限制范围内继续执行二分查找即可。如果在查找过程中始终没有找到目标值，则返回 -1。
     * <p>
     * 这里使用二分查找的思想，每次取中间点 mid，并判断左半边是否有序，
     * 如果左半边有序，则判断目标值是否在左半边有序区间内，如果在，则向左搜索，否则向右搜索。
     * 如果左半边不是有序的，则右半边一定有序，判断目标值是否在右半边有序区间内，如果在，则向右搜索，否则向左搜索。
     * 最终如果没找到目标值，返回 -1。
     * <p>
     * 这个算法的时间复杂度是 O(log n)，空间复杂度是 O(1)。
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            /*判断左半边是否有序*/
            if (nums[mid] >= nums[left]) {
                /*如果在左半边有序区间内*/
                if (target >= nums[left] && target < nums[mid]) {
                    /*向左搜索*/
                    right = mid - 1;
                } else {
                    //*否则向右搜索*/
                    left = mid + 1;
                }
            }
            /*否则右半边有序*/
            else {
                /*如果在右半边有序区间内*/
                if (target > nums[mid] && target <= nums[right]) {
                    /*向右搜索*/
                    left = mid + 1;
                } else {
                    /*否则向左搜索*/
                    right = mid - 1;
                }
            }
        }
        /*如果没找到，返回 -1*/
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;
        int index = new SearchRotatedSortedArray().search(nums, target);
        System.out.println(index);
    }
}
