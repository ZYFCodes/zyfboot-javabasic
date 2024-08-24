package org.zyf.javabasic.letcode.featured75.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 迷宫中离入口最近的出口
 * @author: zhangyanfeng
 * @create: 2024-08-24 11:44
 **/
public class NearestExit {
    public int nearestExit(char[][] maze, int[] entrance) {
        int m = maze.length; // 迷宫的行数
        int n = maze[0].length; // 迷宫的列数

        // 上下左右四个方向的行列变换量
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        // 创建一个队列用于 BFS，存储当前坐标和步数
        Queue<int[]> queue = new LinkedList<>();
        // 将入口坐标加入队列，并将入口位置标记为墙 '+'
        queue.offer(new int[]{entrance[0], entrance[1], 0});
        maze[entrance[0]][entrance[1]] = '+';

        // BFS 遍历迷宫
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0]; // 当前行
            int y = curr[1]; // 当前列
            int dist = curr[2]; // 当前步数

            // 遍历四个方向
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k]; // 新的行
                int ny = y + dy[k]; // 新的列

                // 检查新坐标是否合法且为空格子
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && maze[nx][ny] == '.') {
                    // 检查新坐标是否为出口（边界上的空格子）
                    if (nx == 0 || nx == m - 1 || ny == 0 || ny == n - 1) {
                        return dist + 1;
                    }
                    // 标记新坐标为墙 '+'
                    maze[nx][ny] = '+';
                    // 将新坐标加入队列，并更新步数
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }
        // 如果没有找到出口，返回 -1
        return -1;
    }

    public static void main(String[] args) {
        NearestExit solution = new NearestExit();

        // Test case 1
        char[][] maze1 = {
                {'+', '+', '.', '+'},
                {'.', '.', '.', '+'},
                {'+', '+', '+', '.'}
        };
        int[] entrance1 = {1, 2};
        System.out.println(solution.nearestExit(maze1, entrance1)); // Output: 1

        // Test case 2
        char[][] maze2 = {
                {'+', '+', '+'},
                {'.', '.', '.'},
                {'+', '+', '+'}
        };
        int[] entrance2 = {1, 0};
        System.out.println(solution.nearestExit(maze2, entrance2)); // Output: 2

        // Test case 3
        char[][] maze3 = {
                {'.', '+'}
        };
        int[] entrance3 = {0, 0};
        System.out.println(solution.nearestExit(maze3, entrance3)); // Output: -1
    }

}
