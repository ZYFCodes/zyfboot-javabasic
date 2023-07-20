package org.zyf.javabasic.letcode.math;

/**
 * @author yanfengzhang
 * @description 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。返回你可以获得的最大乘积。
 * <p>
 * 示例 1：输入：2.     输出：1.    解释：2 = 1 + 1，1 × 1 = 1。
 * 示例 2：输入：10     输出：36.     解释：10 = 3 + 3 + 4，3 × 3 × 4 = 36。
 * 说明：你可以假设 n 不小于 2 且不大于 58。
 * @date 2023/7/14  23:18
 */
public class IntegerBreak {

    /**
     * 最优解法可以通过动态规划来实现整数拆分问题。
     * 动态规划的思想是将问题拆分成子问题，并保存子问题的解，避免重复计算，从而得到最优解。
     * 具体步骤如下：
     * 1. 创建一个大小为 n+1 的数组 dp，用于保存拆分整数 i 所能得到的最大乘积。
     * 2. 初始化 dp[0] 和 dp[1] 为 0，因为 0 和 1 不能拆分。
     * 3. 从 i = 2 开始遍历到 n，对每个整数 i 进行如下操作：
     * - 定义两个指针 left 和 right，分别指向 1 和 i-1，表示 i 可以拆分成两个整数 1 和 i-1。
     * - 在循环中，不断缩小 left 和 right 的范围，计算 left 和 right 对应的最大乘积，并将其与当前 dp[i] 比较，更新 dp[i] 的值为较大的那个。
     * 4. 遍历完成后，dp[n] 即为整数 n 拆分后能得到的最大乘积。
     * 该算法的时间复杂度为 O(n^2)，其中 n 是给定的正整数。因为需要遍历从 2 到 n 的每个整数，对每个整数进行拆分计算。而空间复杂度为 O(n)，需要额外的数组来保存子问题的解。
     */
    public static int integerBreak(int n) {
        // 创建大小为 n+1 的数组用于保存拆分整数 i 所能得到的最大乘积
        int[] dp = new int[n + 1];
        // 初始化 dp[0] 为 0，因为 0 不能拆分
        dp[0] = 0;
        // 初始化 dp[1] 为 0，因为 1 不能拆分
        dp[1] = 0;

        // 从 i = 2 开始遍历到 n
        for (int i = 2; i <= n; i++) {
            // 定义两个指针 left 和 right，分别指向 1 和 i-1
            int left = 1;
            int right = i - 1;
            // 初始化 dp[i] 为 i-1，因为整数 i 可以拆分成两个整数 1 和 i-1
            dp[i] = i - 1;

            // 在循环中，不断缩小 left 和 right 的范围
            while (left <= right) {
                // 计算 left 和 right 对应的最大乘积，并将其与当前 dp[i] 比较，更新 dp[i] 的值为较大的那个
                dp[i] = Math.max(dp[i], Math.max(left, dp[left]) * Math.max(right, dp[right]));
                left++;
                right--;
            }
        }

        // dp[n] 即为整数 n 拆分后能得到的最大乘积
        return dp[n];
    }

    public static void main(String[] args) {
        int n1 = 2;
        int n2 = 10;

        System.out.println("Input: " + n1 + " Output: " + integerBreak(n1));
        System.out.println("Input: " + n2 + " Output: " + integerBreak(n2));
    }

}
