package org.zyf.javabasic.letcode.hot100.graph;

/**
 * @program: zyfboot-javabasic
 * @description: 岛屿数量（中等）
 * @author: zhangyanfeng
 * @create: 2024-08-22 12:29
 **/
public class NumIslandsSolution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int numIslands = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    // Found a new island
                    numIslands++;
                    // Perform DFS to mark all connected land
                    dfs(grid, visited, i, j);
                }
            }
        }

        return numIslands;
    }

    private void dfs(char[][] grid, boolean[][] visited, int row, int col) {
        // Base cases
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0' || visited[row][col]) {
            return;
        }

        // Mark this cell as visited
        visited[row][col] = true;

        // Visit all adjacent cells (up, down, left, right)
        dfs(grid, visited, row - 1, col); // up
        dfs(grid, visited, row + 1, col); // down
        dfs(grid, visited, row, col - 1); // left
        dfs(grid, visited, row, col + 1); // right
    }

    public static void main(String[] args) {
        NumIslandsSolution solution = new NumIslandsSolution();

        char[][] grid1 = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        System.out.println("Number of islands in grid1: " + solution.numIslands(grid1)); // Output: 1

        char[][] grid2 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        System.out.println("Number of islands in grid2: " + solution.numIslands(grid2)); // Output: 3
    }
}
