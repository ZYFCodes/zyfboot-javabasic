package org.zyf.javabasic.letcode.array;

/**
 * @author yanfengzhang
 * @description 在给定数组中寻找和大于等于给定值的最短子数组的长度
 * @date 2023/7/14  23:42
 */
public class ShortestSubarray {

    /**
     * 这个问题可以通过使用双指针的方法来解决。我们可以使用两个指针，分别表示子数组的起始位置和结束位置。
     * 首先，将起始指针和结束指针都初始化为数组的第一个元素的索引，然后将它们都向右移动，直到子数组的和大于等于目标值，或者结束指针达到数组的末尾。
     * 一旦子数组的和大于等于目标值，我们记录当前子数组的长度，并尝试将起始指针向右移动，以看是否可以找到更短的子数组，同时更新最短子数组的长度。
     * 然后，我们继续将结束指针向右移动，再次检查子数组的和是否大于等于目标值。如果是，我们再次尝试将起始指针向右移动，并更新最短子数组的长度。
     * 我们重复以上步骤，直到结束指针达到数组的末尾。
     * 最后，我们得到的最短子数组的长度就是我们要找的结果。
     * 这种方法的时间复杂度是 O(n)，其中 n 是数组的长度。
     */
    public static int findShortestSubarray(int[] nums, int target) {
        // 子数组的起始位置
        int left = 0;
        // 最短子数组的长度
        int minLen = Integer.MAX_VALUE;
        // 子数组的和
        int sum = 0;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLen = Math.min(minLen, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }

        // 如果没有找到满足条件的子数组，则返回0
        return minLen != Integer.MAX_VALUE ? minLen : 0;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 7;

        int result = findShortestSubarray(nums, target);
        System.out.println("最短子数组的长度是：" + result);
    }

}
