package org.zyf.javabasic.letcode.graph;

/**
 * @author yanfengzhang
 * @description 给定一个二维网格 grid，每个单元格的值表示该位置的颜色。
 * 现在，我们要对网格中的一个区域进行填充操作。如果一个单元格与边界相连（上、下、左、右四个方向之一），
 * 且其颜色与边界上的单元格颜色不同，那么该单元格应被填充为新的颜色。
 * 要求实现一个函数 colorBorder(int[][] grid, int row, int col, int newColor)，
 * 其中 grid 表示二维网格，row 和 col 表示要填充的区域的起始位置，newColor 表示新的颜色。
 * 函数应该返回填充后的二维网格。
 * @date 2018/6/5  21:07
 */
public class ColoringABorder {
    private int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int[][] colorBorder(int[][] grid, int row, int col, int newColor) {
        int m = grid.length;
        int n = grid[0].length;
        int originalColor = grid[row][col];
        boolean[][] visited = new boolean[m][n];
        dfs(grid, row, col, originalColor, newColor, visited);
        return grid;
    }

    private void dfs(int[][] grid, int row, int col, int originalColor, int newColor, boolean[][] visited) {
        // 检查越界情况或已经访问过的单元格
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col]) {
            return;
        }

        // 检查是否需要填充边界
        if (row == 0 || row == grid.length - 1 || col == 0 || col == grid[0].length - 1 || grid[row][col] != originalColor) {
            grid[row][col] = newColor;
        }

        visited[row][col] = true;

        // 遍历相邻的四个方向
        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            dfs(grid, newRow, newCol, originalColor, newColor, visited);
        }
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 1},
                {1, 1, 0},
                {1, 0, 1}
        };
        int row = 1;
        int col = 1;
        int newColor = 2;

        int[][] result = new ColoringABorder().colorBorder(grid, row, col, newColor);

        // 输出结果
        for (int[] rowArr : result) {
            for (int cell : rowArr) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
