package org.zyf.javabasic.letcode.graph;

/**
 * @author yanfengzhang
 * @description 岛屿数量（Number of Islands）是 LeetCode 上的一道经典算法题目，原题目描述如下：
 * 给定一个由 '1'（陆地）和 '0'（水）组成的二维网格的地图，计算岛屿的数量。一个岛被水包围，并且通过水平或垂直方向上相邻的陆地连接形成。你可以假设网格的四个边均被水包围。
 * 示例1：
 * 输入:
 * 11110
 * 11010
 * 11000
 * 00000
 * 输出: 1
 * 示例2：
 * 输入:
 * 11000
 * 11000
 * 00100
 * 00011
 * 输出: 3
 * 岛屿被水平或垂直方向上相邻的陆地连接形成，因此可以使用深度优先搜索（DFS）或广度优先搜索（BFS）算法来解决该问题。遍历网格中的每个格子，如果遇到陆地（值为 '1'），则进行深度优先搜索或广度优先搜索，将相邻的陆地全部标记为已访问的水域（值为 '0'）。通过重复这个过程，可以计算出岛屿的数量。
 * 注意：在进行深度优先搜索或广度优先搜索时，需要注意边界条件的处理，以及避免重复访问已经访问过的格子。
 * @date 2023/5/2  22:45
 */
public class NumberOfIslands {
    /**
     * 算法步骤如下：
     * 1. 定义一个变量 count，用于记录岛屿的数量。
     * 2. 遍历二维网格的每个格子。
     * * 如果当前格子是陆地（值为 '1'），则进行深度优先搜索。
     * * 在深度优先搜索过程中，将当前格子标记为已访问，即将值从 '1' 修改为 '0'。
     * * 搜索当前格子的上、下、左、右四个方向的相邻格子，如果相邻格子也是陆地，则继续进行深度优先搜索。
     * * 深度优先搜索结束后，将岛屿的数量 count 加1。
     * 3. 返回岛屿的数量 count。
     * 该解法的时间复杂度是 O(M * N)，其中 M 是二维网格的行数，N 是二维网格的列数。
     * 因为每个格子最多被访问一次，并且深度优先搜索的时间复杂度为 O(1)，所以总体的时间复杂度为 O(M * N)。
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        // 岛屿数量计数器
        int count = 0;
        // 网格行数
        int rows = grid.length;
        // 网格列数
        int cols = grid[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }

        return count;
    }

    private void dfs(char[][] grid, int row, int col) {
        // 网格行数
        int rows = grid.length;
        // 网格列数
        int cols = grid[0].length;

        // 检查当前格子是否越界或者不是陆地
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] != '1') {
            return;
        }

        // 将当前格子标记为已访问的水域
        grid[row][col] = '0';

        // 对当前格子的上、下、左、右四个相邻格子进行深度优先搜索
        // 上
        dfs(grid, row - 1, col);
        // 下
        dfs(grid, row + 1, col);
        // 左
        dfs(grid, row, col - 1);
        // 右
        dfs(grid, row, col + 1);
    }

    public static void main(String[] args) {
        // 创建二维字符数组表示网格
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };

        // 创建 NumberOfIslands 对象并计算岛屿数量
        NumberOfIslands numberOfIslands = new NumberOfIslands();
        int islandCount = numberOfIslands.numIslands(grid);

        // 打印岛屿数量
        System.out.println("Number of Islands: " + islandCount);
    }


}
