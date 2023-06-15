package org.zyf.javabasic.letcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author yanfengzhang
 * @description 图中的割边问题，也被称为最小割问题（Minimum Cut），是指在无向图中找到一条边，删除这条边后将图分成两个或多个连通分量，且删除后的连通分量数最小。以下是一个简化的描述：
 * 给定一个无向图和它的顶点数量 n，以及图中的边集合 edges，每条边由两个顶点的索引组成。找到一条边，删除它后，将图分成两个或多个连通分量，且删除后的连通分量数最小。
 * 问题的目标是找到这条割边，或者判断给定的图中是否存在割边。
 * 请注意，最小割问题通常使用更复杂的算法来解决，例如图的最大流算法（Ford-Fulkerson 算法或 Edmonds-Karp 算法）结合深度优先搜索（DFS）或广度优先搜索（BFS）。
 * @date 2022/10/2  20:04
 */
public class MinimumCut {
    /**
     * 最小割问题是一个经典的图论问题，其最优解法可以使用图的最大流算法来解决。以下是最小割问题的最优解法分析：
     * 1. 使用图的最大流算法：最小割问题可以转化为图的最大流问题。
     * 首先，我们将给定的无向图转换为有向图，对于每条边 (u, v)，我们添加两条有向边 (u, v) 和 (v, u)，并将它们的容量都设为1。
     * 然后，我们使用最大流算法（如Ford-Fulkerson算法或Edmonds-Karp算法）找到图的最大流。
     * 2. 寻找割边：在最大流算法找到最大流后，我们需要寻找割边。割边是指从源节点（或起始节点）可达的节点与从汇点（或终止节点）可达的节点之间的边。
     * 在最大流算法中，我们可以通过标记可达节点的方法来寻找割边。首先，从源节点开始，通过遍历流量不为0的边，标记所有可达的节点。
     * 然后，我们遍历所有的边，找到一条连接一个被标记节点和一个未标记节点的边，即为割边。
     * 3. 输出结果：根据题目的要求，我们可以返回割边的具体信息，如两个顶点的索引或边的具体描述，或者只返回是否存在割边的布尔值。
     * 最大流算法的时间复杂度取决于具体的实现方法，常见的算法复杂度为 O(E * V^2)，其中 E 是边的数量，V 是顶点的数量。
     * 因此，最小割问题的解法在一般情况下具有较高的时间复杂度。
     */
    public int findMinCut(int n, int[][] edges) {
        // 构建有向图
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u][v] += w;
            graph[v][u] += w;
        }

        // 使用Ford-Fulkerson算法求最大流
        int[] parent = new int[n];
        int maxFlow = 0;
        while (bfs(graph, 0, n - 1, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = n - 1; v != 0; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, graph[u][v]);
            }
            for (int v = n - 1; v != 0; v = parent[v]) {
                int u = parent[v];
                graph[u][v] -= pathFlow;
                graph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    public List<int[]> findCutEdges(int n, int[][] edges) {
        // 构建有向图
        int[][] graph = new int[n][n];
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph[u][v] += w;
            graph[v][u] += w;
        }

        // 使用Ford-Fulkerson算法求最大流
        int[] parent = new int[n];
        int maxFlow = 0;
        while (bfs(graph, 0, n - 1, parent)) {
            int pathFlow = Integer.MAX_VALUE;
            for (int v = n - 1; v != 0; v = parent[v]) {
                int u = parent[v];
                pathFlow = Math.min(pathFlow, graph[u][v]);
            }
            for (int v = n - 1; v != 0; v = parent[v]) {
                int u = parent[v];
                graph[u][v] -= pathFlow;
                graph[v][u] += pathFlow;
            }
            maxFlow += pathFlow;
        }

        // 标记可达节点
        boolean[] visited = new boolean[n];
        dfs(graph, 0, visited);

        // 找到割边
        List<int[]> cutEdges = new ArrayList<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (visited[u] && !visited[v] || !visited[u] && visited[v]) {
                cutEdges.add(new int[]{u, v});
            }
        }

        return cutEdges;
    }

    private boolean bfs(int[][] graph, int s, int t, int[] parent) {
        int n = graph.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(s);
        visited[s] = true;
        parent[s] = -1;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] > 0) {
                    queue.offer(v);
                    visited[v] = true;
                    parent[v] = u;
                }
            }
        }
        return visited[t];
    }

    private void dfs(int[][] graph, int u, boolean[] visited) {
        visited[u] = true;
        for (int v = 0; v < graph.length; v++) {
            if (graph[u][v] > 0 && !visited[v]) {
                dfs(graph, v, visited);
            }
        }
    }

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {{0, 1, 2}, {0, 2, 3}, {1, 2, 1}, {1, 3, 4}, {2, 4, 5}, {3, 4, 2}, {3, 5, 1}, {4, 5, 4}};
        MinimumCut minimumCut = new MinimumCut();
        int minCut = minimumCut.findMinCut(n, edges);
        List<int[]> cutEdges = minimumCut.findCutEdges(n, edges);
        System.out.println("最小割值为：" + minCut);
        System.out.println("割边为：");
        for (int[] edge : cutEdges) {
            System.out.println(Arrays.toString(edge));
        }
    }
}
