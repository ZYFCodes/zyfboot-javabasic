package org.zyf.javabasic.letcode.jd150.window;

/**
 * @program: zyfboot-javabasic
 * @description: 长度最小的子数组
 * @author: zhangyanfeng
 * @create: 2024-08-25 10:58
 **/
public class MinSubArrayLen {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0; // 左指针
        int currentSum = 0; // 当前子数组的总和
        int minLength = Integer.MAX_VALUE; // 记录最小长度

        // 遍历数组
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right]; // 扩展窗口

            // 收缩窗口，直到 currentSum 小于 target
            while (currentSum >= target) {
                minLength = Math.min(minLength, right - left + 1); // 更新最小长度
                currentSum -= nums[left++]; // 收缩窗口
            }
        }

        // 如果 minLength 未更新，说明没有找到符合条件的子数组
        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }
}
