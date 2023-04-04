package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 假设一个按照升序排列的数组在预先未知的某个点上进行了旋转（例如，数组[0,1,2,4,5,6,7] 可能变为[4,5,6,7,0,1,2] ）。
 * 请找出其中最小的元素。
 * 你可以假设数组中不存在重复元素。
 * 示例 1：输入: [3,4,5,1,2] 输出: 1
 * 示例 2：输入: [4,5,6,7,0,1,2] 输出: 0
 * <p>
 * 提示：
 * n是数组中的元素数量。
 * 没有重复元素。
 * 时间复杂度O(logn)
 * @date 2023/4/1  23:03
 */
public class MinimumInRotatedSortedArray {

    /**
     * 与搜索旋转排序数组不同的是，本题只需要返回最小值即可，因此没有目标值和边界值的判断。
     * 首先初始化左右指针为数组的首尾位置。在每一次循环中，
     * 先计算出中间位置 mid，然后判断如果 nums[mid] > nums[right]，
     * 则最小值肯定在右半边，因此将左指针移到 mid + 1 处。
     * 否则，最小值可能在左半边或就是 mid 本身，因此将右指针移到 mid 处。
     * 当左指针 left 和右指针 right 相遇时，此时的值就是最小值。
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                /*如果中间元素大于右边元素，则最小值在右半边*/
                left = mid + 1;
            } else {
                /*否则最小值在左半边或就是mid本身*/
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums1 = {3, 4, 5, 1, 2};
        int[] nums2 = {4, 5, 6, 7, 0, 1, 2};
        int[] nums3 = {11, 13, 15, 17};

        System.out.println(new MinimumInRotatedSortedArray().findMin(nums1));
        System.out.println(new MinimumInRotatedSortedArray().findMin(nums2));
        System.out.println(new MinimumInRotatedSortedArray().findMin(nums3));
    }

}
