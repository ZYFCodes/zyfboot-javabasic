package org.zyf.javabasic.letcode.queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yanfengzhang
 * @description 给定一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * @date 2023/4/9  15:23
 */
public class MaxSlidingWindow {

    /**
     * 1 我们使用双端队列 deque，存储数组的下标。deque 中的元素按照从大到小的顺序排列，即 deque 中的第一个元素是滑动窗口中的最大值。
     * 2 我们遍历数组，依次将元素放入 deque 中。当元素个数大于 k 时，我们需要将 deque 的第一个元素弹出，因为它不在滑动窗口中了。
     * 3 对于新加入 deque 的元素，从队尾开始比较，如果队尾的元素比当前元素小，
     * 则说明队尾的元素不可能是滑动窗口中的最大值，将其弹出。
     * 直到队尾的元素比当前元素大，或者 deque 为空，我们再将当前元素插入队尾。
     * 4 每次队列中的最大值即为 deque 的第一个元素，我们将其存入结果数组中。
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        if (k == 1) {
            return nums;
        }

        /*双端队列，存储数组下标*/
        Deque<Integer> deque = new LinkedList<>();

        /*初始化队列，从 0 到 k - 1，把队列中比 nums[i] 小的元素全部弹出*/
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];

        /*遍历数组，维护队列中的元素*/
        for (int i = k; i < n; i++) {
            /*判断队首是否在滑动窗口中*/
            if (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }

            /*把队列中比 nums[i] 小的元素全部弹出*/
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }

            /*将当前元素插入队尾*/
            deque.offerLast(i);

            ans[i - k + 1] = nums[deque.peekFirst()];
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        int[] result = new MaxSlidingWindow().maxSlidingWindow(nums, k);
        /*输出：[3, 3, 5, 5, 6, 7]*/
        System.out.println(Arrays.toString(result));

    }
}
