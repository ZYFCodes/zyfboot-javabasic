package org.zyf.javabasic.letcode.hot100.substring;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @program: zyfboot-javabasic
 * @description: 滑动窗口最大值
 * @author: zhangyanfeng
 * @create: 2024-08-21 21:43
 **/
public class MaxSlidingWindowSolution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        // 结果数组
        int[] result = new int[nums.length - k + 1];
        // 使用双端队列存储索引
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // 移除队列中不在当前窗口范围内的元素
            if (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 移除队列中所有小于当前元素的索引
            // 因为这些元素不会再成为最大值
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 将当前元素的索引添加到队列中
            deque.offerLast(i);

            // 当窗口大小达到k时，将当前窗口的最大值添加到结果数组中
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MaxSlidingWindowSolution solution = new MaxSlidingWindowSolution();

        // 测试用例 1
        int[] nums1 = {1, 3, -1, -3, 5, 3, 6, 7};
        int k1 = 3;
        int[] result1 = solution.maxSlidingWindow(nums1, k1);
        for (int num : result1) {
            System.out.print(num + " ");
        }
        // 输出: [3, 3, 5, 5, 6, 7]

        System.out.println();

        // 测试用例 2
        int[] nums2 = {1};
        int k2 = 1;
        int[] result2 = solution.maxSlidingWindow(nums2, k2);
        for (int num : result2) {
            System.out.print(num + " ");
        }
        // 输出: [1]
    }
}
