package org.zyf.javabasic.letcode.array;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * 示例 1：输入：nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * 输出：[1,2,2,3,5,6]     =====通过将 nums2 合并到 nums1 中，我们得到了 [1,2,2,3,5,6] 。
 * 示例 2：输入：nums1 = [1], m = 1
 * nums2 = [],  n = 0
 * 输出：[1]
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -109 <= nums1[i], nums2[i] <= 109
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * @date 2023/3/31  23:20
 */
public class MergeSortArray {

    /**
     * 最优解法是双指针法，具体思路如下：
     * 1 由于两个数组都是有序的，所以可以使用双指针法，分别从两个数组的头部开始比较大小，将较小的元素插入到新的数组中。
     * 2 然后移动指针，再次比较大小，继续插入。
     * 3 如果某个数组遍历完了，直接将另一个数组剩下的元素全部插入到新的数组中。
     * 4 最后得到的新数组就是合并后的有序数组。
     * <p>
     * 这种方法的时间复杂度是 O(m+n)，其中 m 和 n 分别是两个数组的长度。
     * 空间复杂度为 O(1)，因为只需要常数级别的额外空间。
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        /*定义指针 p1，指向 nums1 的最后一个元素*/
        int p1 = m - 1;
        /*定义指针 p2，指向 nums2 的最后一个元素*/
        int p2 = n - 1;
        /*定义指针 p，指向 nums1 的最后一个位置*/
        int p = m + n - 1;

        /*从后往前遍历数组，比较 nums1 和 nums2 中的元素，将较大的元素插入到 nums1 数组中*/
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        /*将 nums2 中剩余的元素逐一复制到 nums1 的前面*/
        System.arraycopy(nums2, 0, nums1, 0, p2 + 1);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3, n = 3;
        new MergeSortArray().merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));

        int[] nums3 = {0, 0, 0};
        int[] nums4 = {1, 2, 3};
        int p = 0, q = 3;
        new MergeSortArray().merge(nums3, p, nums4, q);
        System.out.println(Arrays.toString(nums3));

        int[] nums5 = {2, 0};
        int[] nums6 = {1};
        int r = 1, s = 1;
        new MergeSortArray().merge(nums5, r, nums6, s);
        System.out.println(Arrays.toString(nums5));
    }

}
