package org.zyf.javabasic.letcode.hot100.dynamic;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 最长递增子序列（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 20:21
 **/
public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        // 辅助数组 tail，用于存储递增子序列的最小末尾元素
        int[] tail = new int[nums.length];
        // 当前最长递增子序列的长度
        int size = 0;

        // 遍历数组中的每一个元素
        for (int num : nums) {
            // 使用二分查找确定 num 在 tail 数组中的插入位置
            int i = Arrays.binarySearch(tail, 0, size, num);
            // 如果未找到，则返回插入点 (使用 -1 来补偿数组索引的偏移)
            if (i < 0) {
                i = -(i + 1);
            }
            // 更新 tail 数组中的对应位置
            tail[i] = num;
            // 如果 num 被添加到 tail 数组的末尾，增加最长子序列的长度
            if (i == size) {
                size++;
            }
        }

        // 返回最长递增子序列的长度
        return size;
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();

        // 测试用例1
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("测试用例1结果: " + lis.lengthOfLIS(nums1)); // 输出：4

        // 测试用例2
        int[] nums2 = {0, 1, 0, 3, 2, 3};
        System.out.println("测试用例2结果: " + lis.lengthOfLIS(nums2)); // 输出：4

        // 测试用例3
        int[] nums3 = {7, 7, 7, 7, 7, 7, 7};
        System.out.println("测试用例3结果: " + lis.lengthOfLIS(nums3)); // 输出：1
    }
}
