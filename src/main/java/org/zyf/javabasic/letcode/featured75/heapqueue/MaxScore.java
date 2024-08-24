package org.zyf.javabasic.letcode.featured75.heapqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @program: zyfboot-javabasic
 * @description: 最大子序列的分数
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:01
 **/
public class MaxScore {
    public long maxScore(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        // 存储索引的数组
        Integer[] ids = new Integer[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }

        // 按 nums2 中的值降序排序索引
        Arrays.sort(ids, (i, j) -> nums2[j] - nums2[i]);

        // 最大堆（优先队列）用来维护 nums1 中的 k 个最大值
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long sum = 0;

        // 初始化前 k 个元素的和
        for (int i = 0; i < k; i++) {
            sum += nums1[ids[i]];
            pq.offer(nums1[ids[i]]);
        }

        // 初始分数
        long ans = sum * nums2[ids[k - 1]];

        // 遍历后续的元素，尝试替换堆中最小的元素
        for (int i = k; i < n; i++) {
            int x = nums1[ids[i]];
            if (x > pq.peek()) { // 如果当前元素比堆中最小的元素大
                sum += x - pq.poll(); // 更新总和
                pq.offer(x); // 将新元素加入堆
                // 更新最大分数
                ans = Math.max(ans, sum * nums2[ids[i]]);
            }
        }

        return ans;
    }
    public static void main(String[] args) {
        MaxScore sol = new MaxScore();

        int[] nums1 = {1, 3, 3, 2};
        int[] nums2 = {2, 1, 3, 4};
        int k = 3;
        System.out.println(sol.maxScore(nums1, nums2, k)); // 输出 12

        int[] nums1_2 = {4, 2, 3, 1, 1};
        int[] nums2_2 = {7, 5, 10, 9, 6};
        int k_2 = 1;
        System.out.println(sol.maxScore(nums1_2, nums2_2, k_2)); // 输出 30
    }
}
