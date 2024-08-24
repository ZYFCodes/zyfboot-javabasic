package org.zyf.javabasic.letcode.featured75.twopoint;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: K 和数对的最大数目
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:26
 **/
public class MaxOperations {
    public int maxOperations(int[] nums, int k) {
        int result = 0; // 用于记录最大操作数

        // 排序数组
        Arrays.sort(nums);

        // 初始化两个指针
        int i = 0; // 左指针
        int j = nums.length - 1; // 右指针

        // 使用两指针法
        while (i < j) {
            int sum = nums[i] + nums[j]; // 计算当前两个指针指向的元素和

            if (sum == k) { // 如果和等于目标值k
                result++; // 增加操作计数
                i++; // 移动左指针
                j--; // 移动右指针
            } else if (sum < k) { // 如果和小于目标值k
                i++; // 移动左指针以增大和
            } else { // 如果和大于目标值k
                j--; // 移动右指针以减小和
            }
        }

        return result; // 返回最大操作数
    }

    public static void main(String[] args) {
        MaxOperations solution = new MaxOperations();

        // 测试用例 1
        int[] nums1 = {1, 2, 3, 4};
        int k1 = 5;
        System.out.println("Test Case 1: " + (solution.maxOperations(nums1, k1) == 2 ? "Passed" : "Failed"));

        // 测试用例 2
        int[] nums2 = {3, 1, 3, 4, 3};
        int k2 = 6;
        System.out.println("Test Case 2: " + (solution.maxOperations(nums2, k2) == 1 ? "Passed" : "Failed"));

        // 测试用例 3
        int[] nums3 = {1, 1, 1, 1};
        int k3 = 2;
        System.out.println("Test Case 3: " + (solution.maxOperations(nums3, k3) == 2 ? "Passed" : "Failed"));

        // 测试用例 4
        int[] nums4 = {1, 2, 3, 4, 5, 6};
        int k4 = 7;
        System.out.println("Test Case 4: " + (solution.maxOperations(nums4, k4) == 3 ? "Passed" : "Failed"));

        // 测试用例 5
        int[] nums5 = {2, 2, 2, 2, 2, 2};
        int k5 = 4;
        System.out.println("Test Case 5: " + (solution.maxOperations(nums5, k5) == 3 ? "Passed" : "Failed"));

        // 边界测试用例
        int[] nums6 = {1, 2, 3, 4, 5, 6};
        int k6 = 10;
        System.out.println("Test Case 6: " + (solution.maxOperations(nums6, k6) == 0 ? "Passed" : "Failed"));
    }
}
