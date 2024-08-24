package org.zyf.javabasic.letcode.hot100.greedy;

/**
 * @program: zyfboot-javabasic
 * @description: 跳跃游戏（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 19:01
 **/
public class CanJumpSolution {
    public boolean canJump(int[] nums) {
        int maxReach = 0; // 初始化最远可达位置

        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) {
                // 当前下标i大于最远可达位置，返回false
                return false;
            }
            // 更新最远可达位置
            maxReach = Math.max(maxReach, i + nums[i]);

            // 如果最远可达位置已经覆盖最后一个下标，直接返回true
            if (maxReach >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        CanJumpSolution solution = new CanJumpSolution();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {3, 2, 1, 0, 4};

        System.out.println(solution.canJump(nums1)); // 输出: true
        System.out.println(solution.canJump(nums2)); // 输出: false
    }
}
