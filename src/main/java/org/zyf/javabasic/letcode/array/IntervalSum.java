package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组 nums 和两个整数 start、end，
 * 计算数组 nums 在下标范围 [start, end] 内的元素之和。
 * @date 2023/6/12  23:23
 */
public class IntervalSum {
    private int[] prefixSum;

    public IntervalSum(int[] nums) {
        // 预处理数组的前缀和
        prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
    }

    /**
     * 为了尽量保证时间复杂度低，可以使用预处理的方式来快速计算区间段的和。具体步骤如下：
     *
     * 	1.	创建一个辅助数组 prefixSum，用于保存数组 nums 的前缀和。prefixSum[i] 表示从数组开始位置到下标 i 的元素之和。
     * 	2.	预处理数组 prefixSum，计算每个位置的前缀和。遍历数组 nums，并累加每个元素到当前位置的前缀和。
     * 	3.	计算区间段的和。根据前缀和数组 prefixSum，区间 [start, end] 的元素之和可以通过 prefixSum[end] - prefixSum[start-1] 来计算。
     * 	需要注意的是，如果 start 为 0，则前缀和数组中的元素 prefixSum[start-1] 不存在，此时可以忽略该项。
     *
     * 通过上述预处理的方式，可以在常数时间复杂度内计算区间段的和。
     * 预处理的时间复杂度为 O(n)，其中 n 是数组 nums 的长度。计算区间段的和的时间复杂度为 O(1)。
     */
    public int getSum(int start, int end) {
        // 计算区间段的和
        if (start == 0) {
            return prefixSum[end];
        } else {
            return prefixSum[end] - prefixSum[start - 1];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        IntervalSum intervalSum = new IntervalSum(nums);
        int sum = intervalSum.getSum(1, 4);
        // 输出 Sum: 14
        System.out.println("Sum: " + sum);
    }
}
