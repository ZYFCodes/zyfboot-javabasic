package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例：
 * 输入：[10,9,2,5,3,7,101,18] 输出：4 解释：最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 输入：[0,1,0,3,2,3] 输出：4 解释：最长的上升子序列是 [0, 1, 2, 3]，它的长度是 4。
 * 说明：可能会有多种最长上升子序列的组合，只需要输出对应长度即可。
 * @date 2023/4/2  18:39
 */
public class LongestContinuousIncreasingSubsequence {

    /**
     * 可以定义一个数组 dp，其中 dp[i] 表示以 nums[i] 为结尾的最长递增子序列的长度。
     * 然后我们从左到右遍历数组，对于每个位置 i，我们通过比较 nums[i] 和之前的每个数字 nums[j] 来更新 dp[i]，
     * 具体来说，如果 nums[i] 大于 nums[j]，那么 dp[i] 可以更新为 dp[j]+1，
     * 表示在以 nums[j] 为结尾的最长递增子序列后面再加上一个 nums[i]。
     * 因此，我们可以得到如下的状态转移方程：
     * dp[i] = max(dp[j] + 1) (0 <= j < i, nums[j] < nums[i])
     * 最终，我们遍历一遍整个数组，找到 dp 中的最大值即为所求。
     */
    public int lengthOfLIS(int[] nums) {
        /*dp数组，表示以当前元素为结尾的最长上升子序列的长度*/
        int[] dp = new int[nums.length];
        /*最长上升子序列的长度*/
        int maxLen = 0;

        for (int i = 0; i < nums.length; i++) {
            /*初始化为1，因为每个元素都是一个上升子序列*/
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    /*如果当前元素大于前面的某个元素，可以构成一个更长的上升子序列*/
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            /*更新最长上升子序列的长度*/
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static void main(String[] args) {
        int[] nums1 = {10, 9, 2, 5, 3, 7, 101, 18};
        int res1 = new LongestContinuousIncreasingSubsequence().lengthOfLIS(nums1);
        System.out.println(res1);

        int[] nums2 = {0, 1, 0, 3, 2, 3};
        int res2 = new LongestContinuousIncreasingSubsequence().lengthOfLIS(nums2);
        System.out.println(res2);
    }


}
