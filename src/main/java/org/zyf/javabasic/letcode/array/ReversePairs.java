package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 翻转对指的是对于数组中的两个元素 nums[i]和 nums[j]，
 * 如果 i<j 且 nums[i] > 2*nums[j]，那么就称其为一个翻转对。
 * <p>
 * 给定一个数组 nums，求出该数组中的翻转对数量。
 * 示例 1：输入: [1,3,2,3,1]  输出: 2
 * 示例 2：输入: [2,4,3,5,1]  输出: 3
 * <p>
 * 注意：
 * 给定数组的长度不超过 50000。
 * 输入数组中的所有数字都在 32 位整数的表示范围内。
 * @date 2023/4/2  00:41
 */
public class ReversePairs {

    /**
     * 翻转对问题可以使用归并排序算法来解决，具体方法如下：
     * 1 将给定数组分成左右两个部分，分别递归地求出左右两个部分的翻转对数量。
     * 2 对于左右两个部分，按照从小到大的顺序合并起来，并统计出左右两个部分之间的翻转对数量。
     * 3 将步骤 2 中合并后的数组返回。
     * 整个过程中，每次递归的数组长度都会缩小一半，因此该算法的时间复杂度为 O(nlogn)。
     */
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return mergeSort(nums, 0, nums.length - 1);
    }

    private int mergeSort(int[] nums, int left, int right) {
        if (left >= right) {
            return 0;
        }
        int mid = left + (right - left) / 2;
        /*统计左右两侧的翻转对数目*/
        int count = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        int[] cache = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0, p = mid + 1;
        while (i <= mid) {
            while (p <= right && nums[i] > 2L * nums[p]) {
                /*统计左半部分的数与右半部分的数之间的翻转对数目*/
                p++;
            }
            count += p - (mid + 1);
            while (j <= right && nums[i] >= nums[j]) {
                /*合并左半部分和右半部分的元素*/
                cache[k++] = nums[j++];
            }
            cache[k++] = nums[i++];
        }
        while (j <= right) {
            /*右半部分有剩余元素*/
            cache[k++] = nums[j++];
        }
        /*将排序后的结果复制回原数组*/
        System.arraycopy(cache, 0, nums, left, right - left + 1);
        return count;
    }

    public static void main(String[] args) {
        int[] nums1 = {5, 2, 6, 1};
        System.out.println(new ReversePairs().reversePairs(nums1));

        int[] nums2 = {1, 3, 2, 3, 1};
        System.out.println(new ReversePairs().reversePairs(nums2));

        int[] nums3 = {2, 4, 3, 5, 1};
        System.out.println(new ReversePairs().reversePairs(nums3));
    }

}
