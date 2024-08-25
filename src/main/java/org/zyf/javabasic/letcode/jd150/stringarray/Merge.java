package org.zyf.javabasic.letcode.jd150.stringarray;

/**
 * @program: zyfboot-javabasic
 * @description: 合并两个有序数组
 * @author: zhangyanfeng
 * @create: 2024-08-24 19:43
 **/
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 初始化三个指针
        int p1 = m - 1; // 指向nums1的有效元素末尾
        int p2 = n - 1; // 指向nums2的末尾
        int p = m + n - 1; // 指向合并后数组的末尾

        // 从后向前遍历合并
        while (p1 >= 0 && p2 >= 0) {
            // 比较nums1[p1]和nums2[p2]，将较大值放在nums1[p]
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--; // 移动指针p1
            } else {
                nums1[p] = nums2[p2];
                p2--; // 移动指针p2
            }
            p--; // 移动指针p
        }

        // 如果nums2还有剩余元素，则将其复制到nums1前部
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }

    public static void main(String[] args) {
        Merge solution = new Merge();
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;

        solution.merge(nums1, m, nums2, n);

        // 输出合并后的nums1
        for (int num : nums1) {
            System.out.print(num + " ");
        }
        // 输出结果应为: 1 2 2 3 5 6
    }
}
