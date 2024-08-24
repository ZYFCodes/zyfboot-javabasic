package org.zyf.javabasic.letcode.hot100.heap;

import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: 数组中的第K个最大元素（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 14:50
 **/
public class KthLargestElement {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        return quickSelect(nums, 0, n - 1, n - k);
    }

    private int quickSelect(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }

        // 随机选择一个pivot，避免最坏情况
        Random random = new Random();
        int pivotIndex = left + random.nextInt(right - left + 1);

        // 分区操作，返回pivot的最终位置
        pivotIndex = partition(nums, left, right, pivotIndex);

        // 根据k的位置，选择递归方向
        if (k == pivotIndex) {
            return nums[k];
        } else if (k < pivotIndex) {
            return quickSelect(nums, left, pivotIndex - 1, k);
        } else {
            return quickSelect(nums, pivotIndex + 1, right, k);
        }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        // 先将pivot放到最后
        swap(nums, pivotIndex, right);
        int storeIndex = left;

        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }

        // 将pivot放回到它最终的位置
        swap(nums, storeIndex, right);

        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        KthLargestElement solver = new KthLargestElement();
        int[] nums1 = {3, 2, 1, 5, 6, 4};
        int k1 = 2;
        System.out.println(solver.findKthLargest(nums1, k1)); // 输出: 5

        int[] nums2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k2 = 4;
        System.out.println(solver.findKthLargest(nums2, k2)); // 输出: 4
    }
}
