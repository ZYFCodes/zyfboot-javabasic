package org.zyf.javabasic.letcode.featured75.slidingwindow;

/**
 * @program: zyfboot-javabasic
 * @description: 删掉一个元素以后全为 1 的最长子数组
 * @author: zhangyanfeng
 * @create: 2024-08-23 23:47
 **/
public class LongestSubarray {
    public int longestSubarray(int[] nums) {
        int left = 0; // 滑动窗口的左边界
        int zeroCount = 0; // 当前窗口内 0 的数量
        int maxLength = 0; // 最大子数组长度

        // 使用右指针扩展窗口
        for (int right = 0; right < nums.length; right++) {
            // 如果当前元素是 0，增加 0 的计数
            if (nums[right] == 0) {
                zeroCount++;
            }

            // 当窗口内的 0 的数量超过 1 时，移动左边界
            while (zeroCount > 1) {
                if (nums[left] == 0) {
                    zeroCount--;
                }
                left++;
            }

            // 更新最大长度，注意删除一个元素，所以需要减去 1
            maxLength = Math.max(maxLength, right - left);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubarray solution = new LongestSubarray();

        // 测试用例 1
        int[] nums1 = {1,1,0,1};
        System.out.println("Test Case 1: " + (solution.longestSubarray(nums1) == 3 ? "Passed" : "Failed"));

        // 测试用例 2
        int[] nums2 = {0,1,1,1,0,1,1,0,1};
        System.out.println("Test Case 2: " + (solution.longestSubarray(nums2) == 5 ? "Passed" : "Failed"));

        // 测试用例 3
        int[] nums3 = {1,1,1};
        System.out.println("Test Case 3: " + (solution.longestSubarray(nums3) == 2 ? "Passed" : "Failed"));

        // 测试用例 4
        int[] nums4 = {1,0,1,0,1,0,1};
        System.out.println("Test Case 4: " + (solution.longestSubarray(nums4) == 4 ? "Passed" : "Failed"));

        // 测试用例 5
        int[] nums5 = {0,0,0,0,0};
        System.out.println("Test Case 5: " + (solution.longestSubarray(nums5) == 0 ? "Passed" : "Failed"));
    }
}
