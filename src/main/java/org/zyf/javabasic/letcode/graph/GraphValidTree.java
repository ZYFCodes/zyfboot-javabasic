package org.zyf.javabasic.letcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 给定一个无向图，判断它是否是一棵有效的树。一个有效的树满足以下两个条件：
 * 1. 图中的所有节点都通过边连接在一起，形成一个联通图。
 * 2. 不存在环，即图中没有回路。
 * 这个问题可以用于判断一些实际应用场景中的拓扑结构是否满足树的条件，例如电路布线、网络拓扑等。
 * @date 2022/11/12  23:58
 */
public class GraphValidTree {

    /**
     * 使用邻接表表示图，并使用深度优先搜索（DFS）算法进行遍历和环的检测。具体步骤如下：
     * 1. 首先创建一个邻接表 adjacencyList，表示图中每个节点的邻接节点列表。
     * 2. 使用输入的边信息构建邻接表。
     * 3. 创建一个布尔数组 visited，用于记录节点是否被访问过，初始值为 false。
     * 4. 调用 hasCycle 方法进行环的检测，从节点 0 开始，初始父节点为 -1。
     * 5. 在 hasCycle 方法中，首先将当前节点标记为已访问，并遍历当前节点的邻接节点。
     * 6. 如果邻接节点已被访问过且不是当前节点的父节点，说明存在环，返回 true。
     * 7. 如果邻接节点没有被访问过，则递归调用 hasCycle 方法，并将邻接节点作为新的当前节点，当前节点作为新的父节点。
     * 8. 如果遍历完所有节点后都没有找到环，则返回 false，表示图中没有环。
     */
    public boolean isValidTree(int n, int[][] edges) {
        // 创建邻接表表示图
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        // 构建邻接表
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        // 记录节点是否被访问过
        boolean[] visited = new boolean[n];
        Arrays.fill(visited, false);

        // 检测是否存在环
        if (hasCycle(adjacencyList, visited, 0, -1)) {
            return false;
        }

        // 检测图是否是联通的
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    // 使用深度优先搜索检测是否存在环
    private boolean hasCycle(List<List<Integer>> adjacencyList, boolean[] visited, int node, int parent) {
        visited[node] = true;

        // 遍历当前节点的邻接节点
        for (int neighbor : adjacencyList.get(node)) {
            // 如果邻接节点已经被访问过，并且不是当前节点的父节点，说明存在环
            if (visited[neighbor] && neighbor != parent) {
                return true;
            }

            // 如果邻接节点没有被访问过，则继续递归检测
            if (!visited[neighbor] && hasCycle(adjacencyList, visited, neighbor, node)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        GraphValidTree graphValidTree = new GraphValidTree();

        // 创建一个示例图
        int n = 5;
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {1, 4}};

        boolean isValid = graphValidTree.isValidTree(n, edges);

        System.out.println("图是否是一棵有效的树: " + isValid);
    }

}
