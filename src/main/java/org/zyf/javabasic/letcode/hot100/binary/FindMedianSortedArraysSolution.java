package org.zyf.javabasic.letcode.hot100.binary;

/**
 * @program: zyfboot-javabasic
 * @description: 寻找两个正序数组的中位数 （困难）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:19
 **/
public class FindMedianSortedArraysSolution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array
        if (nums1.length > nums2.length) {
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;

        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;

            if (i < m && nums2[j - 1] > nums1[i]) {
                // i is too small
                iMin = i + 1;
            } else if (i > 0 && nums1[i - 1] > nums2[j]) {
                // i is too big
                iMax = i - 1;
            } else {
                // i is perfect
                int maxLeft = 0;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                if ((m + n) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = 0;
                if (i == m) {
                    minRight = nums2[j];
                } else if (j == n) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }

        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }

    public static void main(String[] args) {
        FindMedianSortedArraysSolution solution = new FindMedianSortedArraysSolution();

        // 示例 1
        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println("Output: " + solution.findMedianSortedArrays(nums1, nums2)); // 输出: 2.0

        // 示例 2
        int[] nums3 = {1, 2};
        int[] nums4 = {3, 4};
        System.out.println("Output: " + solution.findMedianSortedArrays(nums3, nums4)); // 输出: 2.5
    }
}
