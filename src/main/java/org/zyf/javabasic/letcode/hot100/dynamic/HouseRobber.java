package org.zyf.javabasic.letcode.hot100.dynamic;

/**
 * @program: zyfboot-javabasic
 * @description: 打家劫舍（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 19:45
 **/
public class HouseRobber {
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;
        if (n == 1) return nums[0];

        // dp数组
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        HouseRobber robber = new HouseRobber();
        int[] nums1 = {1, 2, 3, 1};
        System.out.println(robber.rob(nums1)); // 输出: 4

        int[] nums2 = {2, 7, 9, 3, 1};
        System.out.println(robber.rob(nums2)); // 输出: 12
    }
}
