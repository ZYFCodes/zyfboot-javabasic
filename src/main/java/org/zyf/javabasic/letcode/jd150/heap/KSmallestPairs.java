package org.zyf.javabasic.letcode.jd150.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @program: zyfboot-javabasic
 * @description: 查找和最小的K对数字
 * @author: zhangyanfeng
 * @create: 2024-08-25 18:47
 **/
public class KSmallestPairs {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // 最小堆，用于存储数对的索引 [i, j]
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0]] + nums2[o1[1]] - nums1[o2[0]] - nums2[o2[1]];
        });

        // 结果列表
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;

        // 初始化堆，放入 nums1 的前 k 个元素与 nums2 第一个元素的组合
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }

        // 取出最小的 k 个数对
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();  // 取出最小和的数对索引
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);

            // 若 j + 1 还在数组范围内，继续将 (i, j+1) 放入堆中
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }

        return ans;
    }
}
