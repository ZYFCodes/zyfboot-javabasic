package org.zyf.javabasic.letcode.featured75.graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 钥匙和房间
 * @author: zhangyanfeng
 * @create: 2024-08-24 11:18
 **/
public class CanVisitAllRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int n = rooms.size();
        boolean[] visited = new boolean[n]; // 记录每个房间是否被访问过
        Queue<Integer> queue = new LinkedList<>(); // BFS 队列

        queue.offer(0); // 从房间 0 开始
        visited[0] = true; // 标记房间 0 为已访问

        while (!queue.isEmpty()) {
            int room = queue.poll(); // 取出当前房间

            // 遍历当前房间能获得的钥匙
            for (int key : rooms.get(room)) {
                if (!visited[key]) { // 如果钥匙对应的房间未被访问
                    visited[key] = true; // 标记房间为已访问
                    queue.offer(key); // 将房间放入队列中
                }
            }
        }

        // 检查所有房间是否都被访问过
        for (boolean roomVisited : visited) {
            if (!roomVisited) {
                return false; // 如果有未访问的房间，则返回 false
            }
        }

        return true; // 所有房间都被访问过，返回 true
    }

    // 主函数用于测试
    public static void main(String[] args) {
        CanVisitAllRooms solution = new CanVisitAllRooms();

        // 示例 1
        List<List<Integer>> rooms1 = Arrays.asList(
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(3),
                Arrays.asList()
        );
        System.out.println(solution.canVisitAllRooms(rooms1)); // 输出: true

        // 示例 2
        List<List<Integer>> rooms2 = Arrays.asList(
                Arrays.asList(1, 3),
                Arrays.asList(3, 0, 1),
                Arrays.asList(2),
                Arrays.asList(0)
        );
        System.out.println(solution.canVisitAllRooms(rooms2)); // 输出: false
    }
}
