package org.zyf.javabasic.letcode.jd150.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 课程表 II
 * @author: zhangyanfeng
 * @create: 2024-08-25 17:33
 **/
public class FindOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 初始化图的邻接表和入度数组
        List<Integer>[] graph = new ArrayList[numCourses];
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        // 构建图和计算每个节点的入度
        for (int[] pair : prerequisites) {
            int dest = pair[0];
            int src = pair[1];
            graph[src].add(dest);
            inDegree[dest]++;
        }

        // 初始化队列并将所有入度为0的节点入队
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        // 执行拓扑排序
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : graph[node]) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // 如果排序结果的节点数等于总课程数，则返回结果，否则返回空数组
        if (result.size() == numCourses) {
            return result.stream().mapToInt(i -> i).toArray();
        } else {
            return new int[0];
        }
    }
}
