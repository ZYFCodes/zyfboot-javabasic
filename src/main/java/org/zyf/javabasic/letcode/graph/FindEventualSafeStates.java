package org.zyf.javabasic.letcode.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 给定一个有向图，其中节点的数量为 n，边的数量为 m。
 * 节点编号从 0 到 n-1。给定一个数组 graph，其中 graph[i] 是节点 i 的所有出边的目标节点。
 * 求解图中所有最终安全的节点。
 * 最终安全的节点是指在某个节点出发，通过任意路径无法到达环中的节点。
 * 换句话说，如果节点在有向图中没有出边，或者从该节点出发能够到达的所有节点都是最终安全的，则该节点是最终安全的。
 * @date 2020/2/21  23:44
 */
public class FindEventualSafeStates {

    /**
     * 最优解法分析：
     * 可以使用深度优先搜索（DFS）来解决该问题。从每个节点开始进行DFS，同时使用一个标志数组 visited 来记录节点的访问状态。
     * 初始状态下，所有节点的 visited 值都设为0，表示未访问。
     * 在DFS的过程中，需要处理以下三种情况：
     * 1. 当访问到一个已访问过的节点时，说明存在环路，将当前路径上的所有节点标记为不安全节点，即将它们的 visited 值设为-1。
     * 2. 当访问到一个已标记为不安全节点的节点时，直接返回。
     * 3. 当访问到一个未访问过的节点时，将其 visited 值设为1，表示正在访问，然后继续对它的邻居节点进行DFS。
     * 最后，遍历所有节点，将 visited 值为1的节点添加到结果列表中，即为最终安全的节点。
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        // 标记节点的访问状态：0-未访问，1-正在访问，-1-不安全节点
        int[] visited = new int[n];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if (isSafe(graph, i, visited)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean isSafe(int[][] graph, int node, int[] visited) {
        if (visited[node] == 1) {
            // 当前节点正在访问，存在环路，不安全
            return false;
        }
        if (visited[node] == -1) {
            // 当前节点已标记为不安全节点，安全
            return true;
        }

        // 标记当前节点为正在访问状态
        visited[node] = 1;

        for (int neighbor : graph[node]) {
            if (!isSafe(graph, neighbor, visited)) {
                // 邻居节点存在环路，当前节点不安全
                return false;
            }
        }

        // 当前节点及其邻居节点都是安全的
        visited[node] = -1;

        return true;
    }

    public static void main(String[] args) {
        FindEventualSafeStates solution = new FindEventualSafeStates();

        // 示例 1
        int[][] graph1 = {{1, 2}, {2, 3}, {5}, {0}, {5}, {}, {}};
        List<Integer> result1 = solution.eventualSafeNodes(graph1);
        // 预期输出：[2, 4, 5, 6]
        System.out.println("Example 1: " + result1);

        // 示例 2
        int[][] graph2 = {{1, 2, 3, 4}, {1, 2}, {3, 4}, {0, 4}, {}};
        List<Integer> result2 = solution.eventualSafeNodes(graph2);
        // 预期输出：[4]
        System.out.println("Example 2: " + result2);

        // 示例 3
        int[][] graph3 = {};
        List<Integer> result3 = solution.eventualSafeNodes(graph3);
        // 预期输出：[]
        System.out.println("Example 3: " + result3);
    }
}
