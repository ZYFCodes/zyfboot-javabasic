package org.zyf.javabasic.letcode.jd150.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 蛇梯棋
 * @author: zhangyanfeng
 * @create: 2024-08-25 17:38
 **/
public class SnakesAndLadders {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        // 将二维坐标转换为一维坐标的映射
        int[] flatBoard = new int[n * n + 1];
        boolean leftToRight = true; // 矩阵从底行开始处理，方向交替
        int index = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (leftToRight) {
                for (int j = 0; j < n; j++) {
                    flatBoard[index++] = board[i][j];
                }
            } else {
                for (int j = n - 1; j >= 0; j--) {
                    flatBoard[index++] = board[i][j];
                }
            }
            leftToRight = !leftToRight;
        }

        // BFS 初始化
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n * n + 1];
        queue.offer(1); // 从方格1开始
        visited[1] = true;
        int moves = 0;

        // BFS 开始
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                if (curr == n * n) return moves; // 到达终点

                // 遍历所有可能的骰子结果
                for (int dice = 1; dice <= 6; dice++) {
                    int next = curr + dice;
                    if (next > n * n) break; // 超过棋盘范围

                    // 处理蛇或梯子
                    if (flatBoard[next] != -1) {
                        next = flatBoard[next];
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.offer(next);
                    }
                }
            }
            moves++;
        }

        return -1; // 无法到达终点
    }
}
