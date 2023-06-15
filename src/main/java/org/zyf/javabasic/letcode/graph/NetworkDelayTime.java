package org.zyf.javabasic.letcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author yanfengzhang
 * @description 给定一个由 n 个节点组成的网络，节点标号从 1 到 n。
 * 同时给定一个列表 times，其中 times[i] = (u, v, w) 表示信号从节点 u 发送到节点 v 需要的时间为 w。
 * 假设从给定节点 k 发送信号，返回所有 n 个节点接收信号所需的最短时间。如果无法使所有节点接收信号，返回 -1。
 * 限制条件：
 * * 网络中节点的数量在范围 [1, 100] 内。
 * * 网络中边的数量在范围 [0, 10^4] 内。
 * * 每条边 times[i] = (u, v, w) 满足 1 <= u, v <= n 且 0 <= w <= 100。
 * @date 2023/6/2  23:53
 */
public class NetworkDelayTime {

    /**
     * 1. 创建一个距离字典 distances，用于存储从源节点到每个节点的最短路径距离。初始时，将源节点的距离设为0，其他节点的距离设为正无穷大。
     * 2. 创建一个优先队列 pq，用于存储待处理的节点。将源节点加入队列，并将其距离设为0。
     * * 进入循环，直到队列为空：
     * * 从队列中取出一个节点 node。
     * * 遍历与 node 相邻的节点：
     * * 计算从源节点到相邻节点的新距离 newDistance，即经过当前节点 node 到达相邻节点的距离。
     * * 如果 newDistance 小于相邻节点的当前距离，则更新相邻节点的距离为 newDistance，并将相邻节点加入队列 pq。
     * 3. 遍历距离字典 distances，找出最大的距离。如果最大距离为正无穷大，则表示无法使所有节点接收信号，返回 -1；否则，返回最大距离。
     * 使用迪杰斯特拉算法，可以在 O(E log V) 的时间复杂度内解决该问题，其中 E 是边的数量，V 是节点的数量。
     * 需要注意的是，在实现中，可以使用邻接表或邻接矩阵来表示图的连接关系，并根据题目的要求进行相应的处理和计算。
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // 构建邻接表，用于存储节点的连接关系和权重
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] edge : times) {
            int u = edge[0];
            int v = edge[1];
            int w = edge[2];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(new int[]{v, w});
        }

        // 创建距离字典，初始时将所有节点的距离设为正无穷大
        Map<Integer, Integer> distances = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            distances.put(i, Integer.MAX_VALUE);
        }

        // 创建优先队列，用于按照距离从小到大处理节点
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);

        // 将源节点加入队列，并将其距离设为0
        pq.offer(new int[]{k, 0});
        distances.put(k, 0);

        // 使用迪杰斯特拉算法计算最短路径
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];

            // 如果当前距离大于节点的已知最短距离，则跳过处理
            if (distance > distances.get(node)) {
                continue;
            }

            // 遍历与当前节点相邻的节点
            if (graph.containsKey(node)) {
                for (int[] neighbor : graph.get(node)) {
                    int nextNode = neighbor[0];
                    int nextDistance = distance + neighbor[1];

                    // 如果新距离小于节点的当前距离，则更新距离并加入队列
                    if (nextDistance < distances.get(nextNode)) {
                        pq.offer(new int[]{nextNode, nextDistance});
                        distances.put(nextNode, nextDistance);
                    }
                }
            }
        }

        // 找出最大的距离，如果存在正无穷大，则返回 -1
        int maxDistance = 0;
        for (int distance : distances.values()) {
            maxDistance = Math.max(maxDistance, distance);
        }
        return maxDistance == Integer.MAX_VALUE ? -1 : maxDistance;
    }

    public static void main(String[] args) {
        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int n = 4;
        int k = 2;
        int result = new NetworkDelayTime().networkDelayTime(times, n, k);
        System.out.println("Network Delay Time: " + result);
    }

}
