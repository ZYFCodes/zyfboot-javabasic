package org.zyf.javabasic.letcode.featured75.graph;

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 重新规划路线
 * @author: zhangyanfeng
 * @create: 2024-08-24 11:26
 **/
public class MinReorder {
    public int minReorder(int n, int[][] connections) {
        // 建立邻接表和反向图
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Set<Integer>> reverseGraph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new HashSet<>());
            reverseGraph.put(i, new HashSet<>());
        }

        for (int[] conn : connections) {
            int u = conn[0];
            int v = conn[1];
            graph.get(u).add(v); // 记录原始方向
            reverseGraph.get(v).add(u); // 记录反向图方向
        }

        // BFS 初始化
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        int changes = 0;

        queue.add(0); // 从城市 0 开始
        visited.add(0);

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 遍历当前城市的所有邻接城市
            for (int neighbor : graph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    changes++; // 需要反转的边
                }
            }

            // 遍历当前城市在反向图中的所有邻接城市
            for (int neighbor : reverseGraph.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return changes;
    }

    // 主函数用于测试
    public static void main(String[] args) {
        MinReorder solution = new MinReorder();

        // 示例 1
        int[][] connections1 = {
                {0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}
        };
        System.out.println(solution.minReorder(6, connections1)); // 输出: 3

        // 示例 2
        int[][] connections2 = {
                {1, 0}, {1, 2}, {3, 2}, {3, 4}
        };
        System.out.println(solution.minReorder(5, connections2)); // 输出: 2

        // 示例 3
        int[][] connections3 = {
                {1, 0}, {2, 0}
        };
        System.out.println(solution.minReorder(3, connections3)); // 输出: 0
    }
}
