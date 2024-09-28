package org.zyf.javabasic.letcode.featured75.slidingwindow;

/**
 * @program: zyfboot-javabasic
 * @description: 最大连续1的个数 III
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:43
 **/
public class LongestOnes {
    public int longestOnes(int[] nums, int k) {
        int left = 0; // 滑动窗口的左边界
        int zeroCount = 0; // 当前窗口内 0 的数量
        int maxLength = 0; // 最大子数组长度

        // 使用右指针扩展窗口
        for (int right = 0; right < nums.length; right++) {
            // 如果当前元素是 0，增加 0 的计数
            if (nums[right] == 0) {
                zeroCount++;
            }

            // 当窗口内的 0 的数量超过 k 时，移动左边界
            while (zeroCount > k) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestOnes solution = new LongestOnes();

        // 测试用例 1
        int[] nums1 = {1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k1 = 2;
        System.out.println("Test Case 1: " + (solution.longestOnes(nums1, k1) == 6 ? "Passed" : "Failed"));

        // 测试用例 2
        int[] nums2 = {0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1};
        int k2 = 3;
        System.out.println("Test Case 2: " + (solution.longestOnes(nums2, k2) == 10 ? "Passed" : "Failed"));

        // 测试用例 3
        int[] nums3 = {1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1};
        int k3 = 2;
        System.out.println("Test Case 3: " + (solution.longestOnes(nums3, k3) == 6 ? "Passed" : "Failed"));

        // 测试用例 4
        int[] nums4 = {0, 0, 0, 0, 0, 0, 0, 0};
        int k4 = 5;
        System.out.println("Test Case 4: " + (solution.longestOnes(nums4, k4) == 5 ? "Passed" : "Failed"));

        // 测试用例 5
        int[] nums5 = {1, 1, 1, 1, 1, 1, 1};
        int k5 = 0;
        System.out.println("Test Case 5: " + (solution.longestOnes(nums5, k5) == 7 ? "Passed" : "Failed"));
    }
}
