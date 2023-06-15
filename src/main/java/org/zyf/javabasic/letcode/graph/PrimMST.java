package org.zyf.javabasic.letcode.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 给定一个连通图，每个边都有一个权重值。要求找到连通图的最小生成树，即包含所有节点且权重之和最小的子图。
 * 具体要求如下：
 * * 给定一个无向连通图，图中的每个边都有一个权重值。
 * * 需要找到一个包含所有节点的子图，使得子图是一个连通图，并且子图的边权重之和最小。
 * 该问题的输入通常是表示图的数据结构（如邻接矩阵或邻接表）以及节点的数量。
 * @date 2023/5/5  23:36
 */
public class PrimMST {

    /**
     * Prim 算法是求解具有最小生成树的连通图的最小代价的最优解法之一。它的时间复杂度为 O(E log V)，其中 V 是节点数量，E 是边数量。
     * 最优解法的思路如下：
     * 1. 创建一个空的最小生成树集合，用于存储最小生成树的边。
     * 2. 选择一个起始节点，将其加入最小生成树集合，并将其标记为已访问。
     * 3. 初始化一个优先队列（最小堆），用于存储边的权重。将起始节点的所有边加入优先队列。
     * * 进入循环，直到优先队列为空。
     * * 从优先队列中取出权重最小的边。
     * * 如果该边连接的节点已经被访问过，则跳过。
     * * 否则，将该边加入最小生成树集合，并将连接的节点标记为已访问。
     * * 将连接节点的所有未访问的边加入优先队列。
     * 4. 循环结束后，最小生成树集合中存储了具有最小代价的连通图的边。
     * 最优解法的核心是使用优先队列来选择权重最小的边，以确保每次加入最小生成树的边都具有最小的权重。这样可以逐步构建最小生成树，并保证最小生成树的代价最小。
     * 需要注意的是，Prim 算法适用于无向连通图，如果图不是连通的，则需要对每个连通分量分别应用 Prim 算法。
     */
    public List<Edge> primMST(int[][] graph) {
        // 图中节点的数量
        int V = graph.length;

        // 最小生成树的边集合
        List<Edge> mst = new ArrayList<>();

        // 用于标记节点是否被访问过，默认为 false
        boolean[] visited = new boolean[V];

        // 优先队列用于选择权重最小的边
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        // 选择节点 0 作为起始节点
        pq.offer(new Edge(0, 0));

        while (!pq.isEmpty()) {
            // 取出权重最小的边
            Edge edge = pq.poll();
            int node = edge.node;
            int weight = edge.weight;

            if (visited[node]) {
                // 如果节点已经被访问过，则跳过
                continue;
            }

            // 标记节点为已访问
            visited[node] = true;

            if (edge.parent != -1) {
                // 将边加入最小生成树的边集合
                mst.add(edge);
            }

            // 遍历与当前节点相邻的边
            for (int i = 0; i < V; i++) {
                if (graph[node][i] != 0 && !visited[i]) {
                    // 将未访问的边加入优先队列
                    pq.offer(new Edge(i, graph[node][i]));
                }
            }
        }

        // 返回最小生成树的边集合
        return mst;
    }

    // 表示图中的边的类
    class Edge {
        // 目标节点
        int node;
        // 权重
        int weight;
        // 源节点（用于构建最小生成树）
        int parent;

        public Edge(int node, int weight) {
            this.node = node;
            this.weight = weight;
            this.parent = -1;
        }
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        List<Edge> mst = new PrimMST().primMST(graph);

        System.out.println("最小生成树的边集合：");
        for (Edge edge : mst) {
            System.out.println("节点 " + edge.parent + " 到节点 " + edge.node + "，权重为 " + edge.weight);
        }
    }
}
