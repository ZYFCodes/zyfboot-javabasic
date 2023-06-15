package org.zyf.javabasic.letcode.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description 给定一个网络，包含源节点s和汇节点t，以及一些中间节点。每条边都有一个容量，表示该边允许通过的最大流量。
 * 你的任务是找到从源节点s到汇节点t的最大流量。
 * 请实现一个函数 int maxFlow(int[][] graph, int s, int t)，其中 graph 是一个二维数组，表示网络的邻接矩阵，
 * graph[i][j] 表示节点i到节点j的边的容量。s 表示源节点的索引，t 表示汇节点的索引。
 * 函数应返回从源节点到汇节点的最大流量。
 * 示例：
 * 输入：
 * graph = [
 *     [0, 3, 0, 3, 0, 0],
 *     [0, 0, 4, 0, 0, 0],
 *     [0, 0, 0, 1, 2, 0],
 *     [0, 0, 0, 0, 2, 6],
 *     [0, 0, 0, 0, 0, 1],
 *     [0, 0, 0, 0, 0, 0]
 * ]
 * s = 0
 * t = 5
 * 输出：5
 * 解释：
 * 从源节点0到汇节点5的最大流量为5。
 * 请注意，这只是一个示例题目描述，实际题目可能会有更多的细节和限制条件。
 * @date 2021/7/15  23:49
 */
public class MaximumFlow {

    /**
     * 使用BFS找到从源节点到汇节点的增广路径
     */
    private boolean bfs(int[][] residualGraph, int[] parent, int source, int target) {
        int vertices = residualGraph.length;
        boolean[] visited = new boolean[vertices];
        Arrays.fill(visited, false);

        // 创建一个队列用于BFS遍历
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 遍历当前节点的邻接节点
            for (int next = 0; next < vertices; next++) {
                if (!visited[next] && residualGraph[current][next] > 0) {
                    queue.add(next);
                    visited[next] = true;
                    parent[next] = current;
                }
            }
        }

        // 如果在BFS过程中能够到达汇节点，则存在增广路径
        return visited[target];
    }

    /**
     * 使用Edmonds-Karp算法计算最大流
     */
    public int maxFlow(int[][] graph, int source, int target) {
        int vertices = graph.length;

        // 创建一个剩余图，初始化为输入图
        int[][] residualGraph = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                residualGraph[i][j] = graph[i][j];
            }
        }

        // 初始化最大流为0
        int maxFlow = 0;

        // 创建一个用于存储增广路径的数组
        int[] parent = new int[vertices];

        // 不断寻找增广路径，并更新剩余图中的流量，直到不存在增广路径
        while (bfs(residualGraph, parent, source, target)) {
            // 计算增广路径上的最大可增加流量
            int minCapacity = Integer.MAX_VALUE;
            for (int v = target; v != source; v = parent[v]) {
                int u = parent[v];
                minCapacity = Math.min(minCapacity, residualGraph[u][v]);
            }

            // 更新路径上的流量
            for (int v = target; v != source; v = parent[v]) {
                int u = parent[v];
                residualGraph[u][v] -= minCapacity;
                residualGraph[v][u] += minCapacity;
            }

            // 增加最大流量
            maxFlow += minCapacity;
        }

        // 返回最大流量
        return maxFlow;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 3, 0, 3, 0, 0},
                {0, 0, 4, 0, 0, 0},
                {0, 0, 0, 1, 2, 0},
                {0, 0, 0, 0, 2, 6},
                {0, 0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 0}
        };
        int source = 0;
        int target = 5;

        MaximumFlow maxFlow = new MaximumFlow();
        int maxFlowValue = maxFlow.maxFlow(graph, source, target);

        System.out.println("The maximum flow from source " + source + " to target " + target + " is: " + maxFlowValue);
    }


}
