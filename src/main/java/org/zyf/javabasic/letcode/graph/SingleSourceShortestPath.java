package org.zyf.javabasic.letcode.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 给定一个带权重的有向图，找到从源节点到目标节点的最短路径，即权重之和最小的路径。
 * 具体要求如下：
 * * 图中的每个边都有一个非负权重值，表示从一个节点到另一个节点的代价。
 * * 源节点是给定的起始节点。
 * * 目标节点是给定的终止节点。
 * * 如果不存在从源节点到目标节点的路径，则返回一个特定的标识值，如无穷大或者-1。
 * 该问题的输入通常是表示图的数据结构（如邻接表或邻接矩阵）以及源节点和目标节点的标识。
 * @date 2023/5/2  23:02
 */
public class SingleSourceShortestPath {

    /**
     * Dijkstra 算法是解决单源最短路径问题的一种常见且有效的算法。它采用贪心策略，逐步确定从源节点到其他节点的最短路径。
     * 以下是 Dijkstra 算法的最优解法思路：
     * 1. 创建一个距离数组 dist，用于保存从源节点到每个节点的当前最短路径的距离。初始化所有节点的距离为无穷大，源节点的距离为0。
     * 2. 创建一个优先队列（如最小堆） pq，用于选择距离最小的节点进行扩展。
     * 3. 将源节点加入优先队列 pq。
     * * 当优先队列 pq 非空时，执行以下步骤：
     * * 从优先队列 pq 中取出距离最小的节点 u。
     * * 遍历节点 u 的所有邻居节点 v，更新其距离：
     * * 如果从源节点到节点 u 的距离加上节点 u 到节点 v 的边的权重小于节点 v 的当前距离，则更新节点 v 的距离为新的最短路径距离，并将节点 v 加入优先队列 pq。
     * 4. 遍历完所有节点后，距离数组 dist 中保存的即为从源节点到每个节点的最短路径距离。
     * Dijkstra 算法的最优解法使用了优先队列来选择当前距离最小的节点进行扩展，以保证每次扩展都是选择距离最短的路径。通过逐步更新节点的距离，最终得到从源节点到其他节点的最短路径。
     * Dijkstra 算法的时间复杂度取决于优先队列的实现方式，一般为 O((V + E) log V)，其中 V 是节点数，E 是边数。
     * 需要注意的是，如果图中存在负权边，Dijkstra 算法不适用，可以考虑使用其他算法，如 Bellman-Ford 算法或使用适应负权边的改进算法，如 Dijkstra 的变种算法。
     */
    public int[] dijkstra(int[][] graph, int source) {
        // 图中节点的数量

        int n = graph.length;
        // 保存从源节点到每个节点的最短路径距离
        int[] dist = new int[n];
        // 初始化距离为无穷大
        Arrays.fill(dist, Integer.MAX_VALUE);
        // 源节点到自身的距离为0
        dist[source] = 0;

        // 优先队列，按照距离排序
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 将源节点加入优先队列
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            // 取出距离最小的节点
            int[] curr = pq.poll();

            int node = curr[0];
            int distance = curr[1];

            // 遍历当前节点的邻居节点
            for (int i = 0; i < n; i++) {
                // 当前节点到邻居节点存在边
                if (graph[node][i] > 0) {
                    int newDistance = distance + graph[node][i];
                    // 如果新路径距离更短，则更新距离并将邻居节点加入优先队列
                    if (newDistance < dist[i]) {
                        dist[i] = newDistance;
                        pq.offer(new int[]{i, newDistance});
                    }
                }
            }
        }
        // 返回源节点到每个节点的最短路径距离
        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = {
                {0, 4, 2, 0, 0},
                {4, 0, 1, 5, 0},
                {2, 1, 0, 8, 10},
                {0, 5, 8, 0, 2},
                {0, 0, 10, 2, 0}
        };
        // 源节点的索引
        int source = 0;

        int[] dist = new SingleSourceShortestPath().dijkstra(graph, source);

        System.out.println("最短路径距离：");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("从节点 " + source + " 到节点 " + i + " 的距离为：" + dist[i]);
        }
    }
}
