package org.zyf.javabasic.letcode.jd150.heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @program: zyfboot-javabasic
 * @description: IPO
 * @author: zhangyanfeng
 * @create: 2024-08-25 18:43
 **/
public class MaximizedCapital {
    // 主方法，用于计算在选择最多 k 个项目后可以获得的最大资本
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // 项目数
        int n = profits.length;

        // 项目列表（每个项目包括资本需求和利润）
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];  // 资本需求
            projects[i][1] = profits[i];  // 利润
        }

        // 按资本需求升序排序
        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));

        // 最大堆，用于存储当前资本 w 能够启动的项目利润
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int index = 0;  // 用于遍历项目
        // 选择最多 k 个项目
        for (int i = 0; i < k; i++) {
            // 将所有当前资本 w 能启动的项目的利润加入堆中
            while (index < n && projects[index][0] <= w) {
                maxHeap.offer(projects[index][1]);
                index++;
            }

            // 如果堆中有可选项目，选择利润最大的项目
            if (!maxHeap.isEmpty()) {
                w += maxHeap.poll();  // 更新资本
            } else {
                break;  // 如果没有更多可选项目，直接结束
            }
        }

        return w;  // 返回最终的资本
    }

    // 测试代码
    public static void main(String[] args) {
        MaximizedCapital solution = new MaximizedCapital();
        int k = 2, w = 0;
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};

        int result = solution.findMaximizedCapital(k, w, profits, capital);
        System.out.println(result);  // 输出应为 4
    }
}
