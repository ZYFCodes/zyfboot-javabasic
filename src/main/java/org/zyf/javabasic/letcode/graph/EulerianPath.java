package org.zyf.javabasic.letcode.graph;

/**
 * @author yanfengzhang
 * @description 给定一个有向图，图中可能存在一个或多个欧拉路径（Eulerian Path）。欧拉路径是指从图中的一个顶点出发，沿着边遍历图中的每个边恰好一次，并且最终回到起点的路径。要求编写一个函数boolean hasEulerianPath(int[][] graph)来判断给定的有向图是否存在欧拉路径。
 * 函数参数graph是一个二维数组，表示有向图的邻接矩阵。graph[i][j]为1表示存在一条从顶点i到顶点j的有向边，为0表示不存在。图中的顶点编号从0到n-1。
 * 如果给定的有向图存在欧拉路径，则返回true；否则返回false。
 * 注意：
 * * 图中可能存在多个连通分量，每个连通分量都需要满足存在欧拉路径的条件。
 * * 图中可能存在孤立的顶点，即没有任何出度和入度的顶点。
 * 这道题可以使用图的遍历算法来解决，可以通过深度优先搜索（DFS）或广度优先搜索（BFS）来查找欧拉路径。
 * @date 2019/3/5  20:46
 */
public class EulerianPath {

    /**
     * 判断给定的有向图是否存在欧拉路径的最优解法可以基于欧拉路径的性质进行判断。下面是最优解法的分析：
     * 1. 欧拉路径的性质：
     * * 对于一个有向图，存在欧拉路径的充分必要条件是：该图是强连通的（Strongly Connected）且满足以下两个条件之一：
     * * 所有顶点的出度和入度相等，即每个顶点的出度等于入度；
     * * 除了起点和终点之外，所有顶点的出度等于入度，而起点的出度比入度大1，终点的入度比出度大1。
     * 2. 解题思路：
     * * 首先，遍历图中的每个顶点，统计每个顶点的出度和入度。
     * * 如果图中存在孤立的顶点（出度和入度均为0），则直接返回false，因为无法从这些顶点出发或到达这些顶点。
     * * 否则，如果图是强连通的，并且满足上述欧拉路径的两个条件之一，则返回true；否则返回false。
     * 该解法的时间复杂度为O(V + E)，其中V是顶点的数量，E是边的数量。
     */
    public static boolean hasEulerianPath(int[][] graph) {
        // 图中顶点的数量
        int n = graph.length;
        // 记录每个顶点的出度
        int[] outDegrees = new int[n];
        // 记录每个顶点的入度
        int[] inDegrees = new int[n];
        // 统计出度与入度不相等的顶点数量
        int count = 0;

        // 统计每个顶点的出度和入度
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                outDegrees[i] += graph[i][j];
                inDegrees[j] += graph[i][j];
            }
            if (outDegrees[i] != inDegrees[i]) {
                count++;
            }
        }

        // 判断是否存在孤立的顶点（出度和入度均为0）
        for (int i = 0; i < n; i++) {
            if (outDegrees[i] == 0 && inDegrees[i] == 0) {
                return false;
            }
        }

        // 判断是否是强连通的图并满足欧拉路径的条件
        boolean isConnected = isStronglyConnected(graph, 0, new boolean[n]);
        if (isConnected && (count == 0 || count == 2)) {
            return true;
        } else {
            return false;
        }
    }

    // 深度优先搜索判断图是否是强连通的
    private static boolean isStronglyConnected(int[][] graph, int v, boolean[] visited) {
        visited[v] = true;
        for (int i = 0; i < graph.length; i++) {
            if (graph[v][i] == 1 && !visited[i]) {
                isStronglyConnected(graph, i, visited);
            }
        }
        for (boolean flag : visited) {
            if (!flag) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 0, 0, 1},
                {1, 0, 1, 1, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 0, 0},
                {1, 0, 0, 0, 0}
        };

        boolean hasEulerianPath = hasEulerianPath(graph);
        System.out.println("是否存在欧拉路径: " + hasEulerianPath);
    }

}
