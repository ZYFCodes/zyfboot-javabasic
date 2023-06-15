package org.zyf.javabasic.letcode.graph;

/**
 * @author yanfengzhang
 * @description 给定一个无向图，判断它是否是一个二分图。
 * 如果一个图可以被分割成两个独立的子集，且图中的每条边连接两个不同的子集中的节点，则该图是一个二分图。
 * 你可以假设图中没有自环（即图中没有自己连接自己的边）和重复的边。
 * 示例 1:输入: [[1,3], [0,2], [1,3], [0,2]].  输出: true
 * 解释: 无向图如下所示，可以将节点分成两个独立的子集 {0, 2} 和 {1, 3}。
 * 0----1
 * |\   |
 * | \  |
 * |  \ |
 * 3----2
 * 示例 2:输入: [[1,2,3], [0,2], [0,1,3], [0,2]]    输出: false
 * 解释: 无向图如下所示，无法将节点分成两个独立的子集。
 * 0----1
 * | \  |
 * |  \ |
 * 3----2
 * @date 2022/8/25  23:03
 */
public class IsGraphBipartite {

    /**
     * 判断一个图是否为二分图，可以使用深度优先搜索（DFS）或广度优先搜索（BFS）的方式进行遍历和染色。
     * 具体解决方法如下：
     * 1. 定义一个数组 colors，用于记录每个节点的染色情况。初始时，将所有节点的颜色设置为未染色，可以用整数 0 表示。
     * 2. 对于图中的每个节点，如果该节点还未染色，则从该节点开始进行染色操作。
     * * 使用 DFS 或 BFS 遍历图的节点，并进行染色：
     * * 选择一个初始节点，并将其染色为 1。
     * * 遍历该节点的所有相邻节点，如果相邻节点未染色，则将其染色为与当前节点不同的颜色（即 1 的补）。
     * * 继续递归或迭代遍历相邻节点的相邻节点，并进行染色操作。
     * * 如果在染色过程中，发现相邻节点已经染色，并且颜色与当前节点相同，则说明该图不是二分图，返回 false。
     * 3. 如果所有节点都被染色并且满足染色规则，则说明该图是二分图，返回 true。
     * 该算法的时间复杂度是 O(V + E)，其中 V 表示节点的数量，E 表示边的数量。空间复杂度是 O(V)，用于存储节点的染色情况。
     */
    public boolean isBipartite(int[][] graph) {
        // 图中节点的数量
        int n = graph.length;
        // 记录节点的染色情况
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && !dfs(graph, i, 1, colors)) {
                return false;
            }
        }

        return true;
    }

    private boolean dfs(int[][] graph, int node, int color, int[] colors) {
        // 将当前节点染色
        colors[node] = color;

        for (int neighbor : graph[node]) {
            if (colors[neighbor] == color) {
                // 如果相邻节点已经染色并且颜色与当前节点相同，则返回 false
                return false;
            }

            if (colors[neighbor] == 0 && !dfs(graph, neighbor, -color, colors)) {

                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // 定义图的邻接表表示
        int[][] graph = {
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };
        // 判断图是否为二分图
        boolean isBipartite = new IsGraphBipartite().isBipartite(graph);

        // 输出结果
        if (isBipartite) {
            System.out.println("The graph is bipartite.");
        } else {
            System.out.println("The graph is not bipartite.");
        }
    }

}
