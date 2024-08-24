package org.zyf.javabasic.letcode.hot100.graph;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @program: zyfboot-javabasic
 * @description: 腐烂的橘子（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 12:56
 **/
public class OrangesRottingSolution {
    public int orangesRotting(int[][] grid) {
        // 获取网格的行数和列数
        int m = grid.length;
        int n = grid[0].length;

        // 用于保存新鲜橘子的位置
        Queue<int[]> queue = new LinkedList<>();
        // 记录新鲜橘子的数量
        int freshCount = 0;

        // 遍历整个网格
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 如果当前单元格是腐烂的橘子
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
                // 如果当前单元格是新鲜的橘子
                else if (grid[i][j] == 1) {
                    freshCount++;
                }
            }
        }

        // 如果没有新鲜橘子，直接返回0
        if (freshCount == 0) return 0;

        // 记录时间步数
        int minutes = 0;
        // 4个方向的移动数组
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        // BFS遍历
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 对当前时间步的所有腐烂橘子进行处理
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                int x = cell[0];
                int y = cell[1];

                // 遍历4个方向
                for (int[] dir : directions) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    // 检查新位置是否在网格内且是新鲜橘子
                    if (newX >= 0 && newX < m && newY >= 0 && newY < n && grid[newX][newY] == 1) {
                        // 将新鲜橘子腐烂
                        grid[newX][newY] = 2;
                        // 将腐烂的橘子位置添加到队列
                        queue.add(new int[]{newX, newY});
                        // 新鲜橘子数量减少
                        freshCount--;
                    }
                }
            }
            // 如果队列不为空，增加时间步数
            if (!queue.isEmpty()) {
                minutes++;
            }
        }

        // 如果还有新鲜橘子未腐烂，返回-1
        return freshCount == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        OrangesRottingSolution solution = new OrangesRottingSolution();

        // 示例 1
        int[][] grid1 = {
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        };
        System.out.println(solution.orangesRotting(grid1)); // 输出: 4

        // 示例 2
        int[][] grid2 = {
                {2, 1, 1},
                {0, 1, 1},
                {1, 0, 1}
        };
        System.out.println(solution.orangesRotting(grid2)); // 输出: -1

        // 示例 3
        int[][] grid3 = {
                {0, 2}
        };
        System.out.println(solution.orangesRotting(grid3)); // 输出: 0
    }
}
