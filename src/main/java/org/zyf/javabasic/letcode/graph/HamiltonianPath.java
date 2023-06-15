package org.zyf.javabasic.letcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 给定一个无向图，判断是否存在一条哈密顿路径，使得路径经过图中的每个顶点恰好一次。如果存在，返回true；否则，返回false。
 * 请注意，哈密顿路径是一个经典的NP完全问题，因此目前尚没有已知的多项式时间复杂度的解法。
 * 最优解法是基于回溯法（Backtracking）的深度优先搜索（DFS）算法，通过枚举所有可能的路径来判断是否存在哈密顿路径。
 * @date 2021/12/5  23:49
 */
public class HamiltonianPath {

    /**
     * 哈密顿路径是一个经典的NP完全问题，因此没有已知的多项式时间复杂度的解法。最优解法是基于回溯法（Backtracking）的深度优先搜索（DFS）算法。
     * 以下是最优解法的分析：
     * 1. 解题思路：
     * * 使用回溯法（Backtracking）和深度优先搜索（DFS）来枚举所有可能的路径。
     * * 从任意一个顶点开始，递归地尝试访问该顶点的所有未访问过的相邻顶点，直到达到路径的长度为图中顶点的数量或无法继续前进为止。
     * * 在搜索过程中，需要记录已经访问过的顶点，并避免重复访问。
     * * 如果在搜索过程中找到了一条包含所有顶点的路径，即存在哈密顿路径，返回true；否则，返回false。
     * 2. 算法实现：
     * * 使用邻接矩阵或邻接表来表示图的连接关系。
     * * 使用一个布尔数组来记录顶点的访问状态。
     * * 从每个顶点开始进行深度优先搜索，判断是否存在哈密顿路径。
     * 由于哈密顿路径是一个NP完全问题，解决大规模的哈密顿路径问题往往是非常耗时的，因此实际应用中常常需要根据具体情况进行问题的简化、剪枝等策略来提高算法的效率。
     */
    public static boolean hasHamiltonianPath(int[][] graph) {
        // 图中顶点的数量
        int n = graph.length;
        // 记录顶点的访问状态
        boolean[] visited = new boolean[n];
        // 存储哈密顿路径的顶点序列
        List<Integer> path = new ArrayList<>();

        // 从每个顶点开始进行深度优先搜索
        for (int i = 0; i < n; i++) {
            // 初始化访问状态
            Arrays.fill(visited, false);
            // 清空路径
            path.clear();
            // 将当前顶点加入路径
            path.add(i);
            // 标记当前顶点为已访问
            visited[i] = true;
            if (dfs(graph, i, visited, path)) {
                // 如果存在哈密顿路径，返回true
                return true;
            }
        }

        // 未找到哈密顿路径，返回false
        return false;
    }

    private static boolean dfs(int[][] graph, int current, boolean[] visited, List<Integer> path) {
        if (path.size() == graph.length) {
            // 如果路径长度等于顶点数量，说明找到了哈密顿路径
            return true;
        }

        for (int i = 0; i < graph.length; i++) {
            // 如果存在边且未访问过
            if (graph[current][i] == 1 && !visited[i]) {
                // 标记顶点为已访问
                visited[i] = true;
                // 将顶点加入路径
                path.add(i);
                if (dfs(graph, i, visited, path)) {
                    // 继续深度优先搜索
                    return true;
                }
                // 回溯
                // 恢复顶点的访问状态
                visited[i] = false;
                // 移除顶点
                path.remove(path.size() - 1);
            }
        }

        // 未找到哈密顿路径
        return false;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 1, 1, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0}
        };

        boolean hasHamiltonianPath = hasHamiltonianPath(graph);
        System.out.println("是否存在哈密顿路径: " + hasHamiltonianPath);
    }
}
