package org.zyf.javabasic.letcode.featured75.heapqueue;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @program: zyfboot-javabasic
 * @description: 雇佣 K 位工人的总代价
 * @author: zhangyanfeng
 * @create: 2024-08-24 12:07
 **/
public class TotalCost {
    public long totalCost(int[] costs, int k, int candidates) {
        int n = costs.length;
        long ans = 0;

        // 如果候选人数和雇佣人数加起来超出总工人数，则直接排序选择
        if (candidates * 2 + k > n) {
            Arrays.sort(costs);
            for (int i = 0; i < k; i++) {
                ans += costs[i];
            }
            return ans;
        }

        // 使用优先队列分别维护前 candidates 和后 candidates 的工人代价
        PriorityQueue<Integer> pre = new PriorityQueue<>();
        PriorityQueue<Integer> suf = new PriorityQueue<>();

        // 初始化前 candidates 和后 candidates 的工人代价
        for (int i = 0; i < candidates; i++) {
            pre.offer(costs[i]);
            suf.offer(costs[n - 1 - i]);
        }

        // 用于跟踪当前需要从中选择的工人的索引
        int i = candidates; // 下一个前候选工人的索引
        int j = n - 1 - candidates; // 下一个后候选工人的索引

        // 雇佣 k 位工人
        while (k-- > 0) {
            // 从前和后候选工人中选择代价最小的工人
            if (pre.peek() <= suf.peek()) {
                ans += pre.poll(); // 选择前候选工人
                if (i <= j) { // 检查是否还有工人可以添加到队列中
                    pre.offer(costs[i++]);
                }
            } else {
                ans += suf.poll(); // 选择后候选工人
                if (i <= j) { // 检查是否还有工人可以添加到队列中
                    suf.offer(costs[j--]);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        TotalCost sol = new TotalCost();

        int[] costs1 = {17, 12, 10, 2, 7, 2, 11, 20, 8};
        int k1 = 3;
        int candidates1 = 4;
        System.out.println(sol.totalCost(costs1, k1, candidates1)); // 输出 11

        int[] costs2 = {1, 2, 4, 1};
        int k2 = 3;
        int candidates2 = 3;
        System.out.println(sol.totalCost(costs2, k2, candidates2)); // 输出 4
    }
}
