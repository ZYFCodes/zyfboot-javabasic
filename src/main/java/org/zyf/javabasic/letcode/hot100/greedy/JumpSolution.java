package org.zyf.javabasic.letcode.hot100.greedy;

/**
 * @program: zyfboot-javabasic
 * @description: 跳跃游戏 II（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 19:07
 **/
public class JumpSolution {
    public int jump(int[] nums) {
        int jumps = 0;         // 记录跳跃次数
        int currentEnd = 0;    // 当前跳跃的结束位置
        int farthest = 0;      // 在当前跳跃内能到达的最远位置

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]); // 更新最远能到达的位置

            if (i == currentEnd) { // 到达当前跳跃的结束位置
                jumps++;            // 增加跳跃次数
                currentEnd = farthest; // 更新跳跃结束位置为最远能到达的位置
            }
        }

        return jumps; // 返回跳跃次数
    }

    public static void main(String[] args) {
        JumpSolution solution = new JumpSolution();
        int[] nums1 = {2, 3, 1, 1, 4};
        int[] nums2 = {2, 3, 0, 1, 4};

        System.out.println(solution.jump(nums1)); // 输出: 2
        System.out.println(solution.jump(nums2)); // 输出: 2
    }
}
