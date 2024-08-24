package org.zyf.javabasic.letcode.featured75.prefix;

/**
 * @program: zyfboot-javabasic
 * @description: 寻找数组的中心下标
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:59
 **/
public class PivotIndex {
    public int pivotIndex(int[] nums) {
        int totalSum = 0; // 计算数组的总和
        int leftSum = 0;  // 当前下标左侧元素的和

        // 计算总和
        for (int num : nums) {
            totalSum += num;
        }

        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            // 右侧元素的和 = 总和 - 左侧元素的和 - 当前元素
            int rightSum = totalSum - leftSum - nums[i];

            // 检查当前下标是否是中心下标
            if (leftSum == rightSum) {
                return i;
            }

            // 更新左侧元素的和
            leftSum += nums[i];
        }

        // 没有找到中心下标
        return -1;
    }

    public static void main(String[] args) {
        PivotIndex solution = new PivotIndex();

        // 测试用例 1
        int[] nums1 = {1, 7, 3, 6, 5, 6};
        System.out.println("Test Case 1: " + (solution.pivotIndex(nums1) == 3 ? "Passed" : "Failed"));

        // 测试用例 2
        int[] nums2 = {1, 2, 3};
        System.out.println("Test Case 2: " + (solution.pivotIndex(nums2) == -1 ? "Passed" : "Failed"));

        // 测试用例 3
        int[] nums3 = {2, 1, -1};
        System.out.println("Test Case 3: " + (solution.pivotIndex(nums3) == 0 ? "Passed" : "Failed"));

        // 测试用例 4
        int[] nums4 = {1, 1, 1, 1, 1, 1, 1};
        System.out.println("Test Case 4: " + (solution.pivotIndex(nums4) == 3 ? "Passed" : "Failed"));

        // 测试用例 5
        int[] nums5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Test Case 5: " + (solution.pivotIndex(nums5) == -1 ? "Passed" : "Failed"));
    }
}
