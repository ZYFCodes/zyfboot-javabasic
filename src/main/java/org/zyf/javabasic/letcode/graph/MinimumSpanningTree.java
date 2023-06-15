package org.zyf.javabasic.letcode.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 给定一个带有权重的无向连通图，你需要找到一个生成树，使得这个生成树的权重之和最小。
 * 注意：
 * * 该图包含 N 个节点，编号为 0 到 N-1，其中 N 为图中节点的数量。
 * * 生成树必须包含图中的所有节点。
 * * 图的权重以一个二维数组 graph 表示，graph[i][j] 表示节点 i 和节点 j 之间的边的权重。
 * * 如果节点 i 和节点 j 之间没有边，则 graph[i][j] = graph[j][i] = -1。
 * 示例 1:
 * 输入:
 * graph = [    [-1, 2, -1, 6, -1],
 *     [2, -1, 3, 8, 5],
 *     [-1, 3, -1, -1, 7],
 *     [6, 8, -1, -1, 9],
 *     [-1, 5, 7, 9, -1]
 * ]
 * 输出: 16
 * 解释: 生成树的边包括 (0, 1), (1, 2), (1, 3), (1, 4)，总权重为 2 + 3 + 8 + 5 = 16。
 * 注意：
 * * 你可以假设图是连通的，并且图中不存在环。
 * * 该算法的实现使用了 Prim 算法，通过贪心策略逐步添加权重最小的边来构建生成树。
 * @date 2021/10/5  22:18
 */
public class MinimumSpanningTree {

    /**
     * 最小生成树问题可以使用 Prim 算法解决，该算法基于贪心策略，逐步构建生成树，选择当前权重最小的边来扩展生成树。
     * 具体步骤如下：
     * 1. 创建一个空的生成树集合和一个空的已访问节点集合。
     * 2. 选择一个起始节点，将其添加到已访问节点集合中。
     *     * 重复以下步骤，直到已访问节点集合包含所有节点：
     *     * 遍历已访问节点集合中的每个节点，找到与其相连且未访问过的节点中权重最小的边。
     *     * 将找到的边添加到生成树集合中，并将对应的节点添加到已访问节点集合中。
     * 3. 计算生成树集合中所有边的权重之和，即为最小生成树的代价。
     * 最优解法的时间复杂度为 O(V^2)，其中 V 是节点的数量。
     * 在实现过程中，通常会使用优先队列（Priority Queue）来快速选择权重最小的边，可以将时间复杂度优化到 O(E log V)，其中 E 是边的数量。
     * 注意：在图中存在多个连通分量的情况下，需要对每个连通分量分别进行最小生成树的构建。
     */
    public int minimumCost(int[][] graph) {
        // 节点的数量
        int N = graph.length;
        // 记录节点是否已访问
        boolean[] visited = new boolean[N];
        // 记录每个节点与已访问节点集合的最小边权重
        int[] minWeight = new int[N];
        // 初始化最小权重为最大值
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        // 起始节点的最小权重为0
        minWeight[0] = 0;

        // 最小生成树的总代价
        int totalCost = 0;

        // 优先队列用于快速选择权重最小的边
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 起始节点入队
        pq.offer(new int[]{0, 0});

        while (!pq.isEmpty()) {
            // 弹出权重最小的边
            int[] curr = pq.poll();
            int node = curr[0];
            int weight = curr[1];

            if (visited[node]) {
                // 跳过已访问的节点
                continue;
            }

            // 标记节点为已访问
            visited[node] = true;
            // 更新总代价
            totalCost += weight;

            // 遍历与当前节点相连的边
            for (int i = 0; i < N; i++) {
                if (graph[node][i] != -1 && !visited[i]) {
                    if (graph[node][i] < minWeight[i]) {
                        // 更新节点与已访问节点集合的最小权重
                        minWeight[i] = graph[node][i];
                        // 将边加入优先队列
                        pq.offer(new int[]{i, graph[node][i]});
                    }
                }
            }
        }

        return totalCost;
    }

    public static void main(String[] args) {
        // 创建一个有权重的连通图
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        MinimumSpanningTree mst = new MinimumSpanningTree();
        int minCost = mst.minimumCost(graph);
        System.out.println("Minimum cost of spanning tree: " + minCost);
    }
}
