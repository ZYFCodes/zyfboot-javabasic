package org.zyf.javabasic.letcode.graph;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 要求在带有负权边的有向图中，找到从源节点到目标节点的路径中权重之和最小的路径。
 * 具体要求如下：
 * * 图中的每个边都有一个权重值，可以为正、负或零。
 * * 源节点是给定的起始节点。
 * * 目标节点是给定的终止节点。
 * * 需要找到从源节点到目标节点的路径中权重之和最小的路径。
 * *
 * 该问题的输入通常是表示图的数据结构（如邻接表或邻接矩阵）以及源节点和目标节点的标识。
 * 负权最短路径问题与最短路径问题类似，但不同之处在于允许存在负权边。这
 * 使得问题更具挑战性，因为负权边可能导致无限循环或无法收敛的情况。因此，传统的最短路径算法，如 Dijkstra 算法，不能直接应用于负权最短路径问题。
 * 解决负权最短路径问题的常见算法包括 Bellman-Ford 算法和 Floyd-Warshall 算法。这些算法能够处理负权边，但其时间复杂度较高。
 * 需要注意的是，在某些情况下，负权边可能导致没有有限路径从源节点到目标节点，或者存在无穷多个路径。
 * 因此，在使用负权最短路径算法时，需要考虑这些特殊情况，并确定是否存在有效的路径。
 * @date 2022/4/2  23:19
 */
public class NegativeWeightShortestPath {

    /**
     * Bellman-Ford 算法：
     * Bellman-Ford 算法是一种适用于有向图的负权最短路径算法。
     * 算法通过迭代更新节点的最短路径估计，直到收敛或发现负权回路。
     * 对于一个包含 V 个节点和 E 条边的图，Bellman-Ford 算法的时间复杂度为 O(VE)。
     * Bellman-Ford 算法可以处理负权边和负权回路，因此适用于解决负权最短路径问题。
     */
    public int[] bellmanFord(int[][] graph, int source) {
        // 图中节点的数量
        int V = graph.length;
        // 保存从源节点到每个节点的最短路径距离
        int[] dist = new int[V];
        // 初始化距离为无穷大
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 源节点到自身的距离为0
        dist[source] = 0;

        // 进行 V-1 轮松弛操作
        for (int i = 0; i < V - 1; i++) {
            // 遍历所有边
            for (int u = 0; u < V; u++) {
                for (int v = 0; v < V; v++) {
                    if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                        // 更新节点 v 的最短路径距离
                        dist[v] = dist[u] + graph[u][v];
                    }
                }
            }
        }

        // 检测负权回路
        for (int u = 0; u < V; u++) {
            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    // 存在负权回路，无法求解最短路径
                    return new int[0];
                }
            }
        }

        // 返回源节点到每个节点的最短路径距离
        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 4, 2, 0, 0},
                {4, 0, -1, 5, 0},
                {2, -1, 0, 8, 10},
                {0, 5, 8, 0, -6},
                {0, 0, 10, -6, 0}
        };

        // 源节点的索引
        int source = 0;
        int[] dist = new NegativeWeightShortestPath().bellmanFord(graph, source);

        if (dist.length == 0) {
            System.out.println("存在负权回路，无法求解最短路径");
        } else {
            System.out.println("最短路径距离：");
            for (int i = 0; i < dist.length; i++) {
                System.out.println("从节点 " + source + " 到节点 " + i + " 的距离为：" + dist[i]);
            }
        }
    }
}
