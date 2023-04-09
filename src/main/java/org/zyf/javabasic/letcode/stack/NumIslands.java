package org.zyf.javabasic.letcode.stack;

import java.util.Stack;

/**
 * @author yanfengzhang
 * @description 给你一个由'1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * @date 2023/4/9  15:35
 */
public class NumIslands {

    /**
     * 具体实现思路如下：
     * <p>
     * 遍历矩阵中的每个元素，当元素为1时，进入DFS遍历。
     * 进入DFS遍历时，将当前元素标记为0，表示已经遍历过。
     * 遍历当前元素的四个方向（上、下、左、右），如果该方向上的元素为1，则进入该方向继续DFS遍历。
     * 当所有与当前元素相连通的元素都被遍历过后，回到上一个节点继续遍历。
     * 统计遍历的次数即为岛屿的数量。
     */
    public int numIslands(char[][] grid) {
        /*记录岛屿数量*/
        int count = 0;
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        /*方向数组，用于四个方向的遍历*/
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        /*记录当前位置是否已经被访问*/
        boolean[][] visited = new boolean[m][n];
        /*用栈来模拟深度优先搜索*/
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                /*如果当前位置是陆地且没有被访问过*/
                if (grid[i][j] == '1' && !visited[i][j]) {
                    /*岛屿数量+1*/
                    count++;
                    /*将当前位置入栈*/
                    stack.push(new int[]{i, j});
                    /*标记当前位置为已访问*/
                    visited[i][j] = true;
                    /*栈不为空时继续搜索*/
                    while (!stack.empty()) {
                        /*取出栈顶元素作为当前位置*/
                        int[] cur = stack.pop();
                        /*四个方向遍历*/
                        for (int k = 0; k < 4; k++) {
                            int x = cur[0] + dx[k];
                            int y = cur[1] + dy[k];
                            /*如果满足条件，则入栈并标记为已访问*/
                            if (x >= 0 && x < m && y >= 0
                                    && y < n
                                    && !visited[x][y]
                                    && grid[x][y] == '1') {
                                stack.push(new int[]{x, y});
                                visited[x][y] = true;
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int numIslands = new NumIslands().numIslands(grid);
        /*输出 3*/
        System.out.println(numIslands);
    }


    class Solution {
        public int numIslands(char[][] grid) {
            /*边界条件判断*/
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int m = grid.length;
            int n = grid[0].length;
            /*记录每个点是否已经被访问过*/
            boolean[][] visited = new boolean[m][n];
            /*岛屿数量计数器*/
            int count = 0;
            /*遍历整个网格*/
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    /*如果当前点为 '1' 且未被访问过，说明发现一个新的岛屿，需要进行 DFS 搜索*/
                    if (!visited[i][j] && grid[i][j] == '1') {
                        /*岛屿数量加1*/
                        count++;
                        /*从当前点开始进行 DFS 搜索*/
                        dfs(grid, visited, i, j);
                    }
                }
            }
            /*返回岛屿数量*/
            return count;
        }

        private void dfs(char[][] grid, boolean[][] visited, int i, int j) {
            /*如果坐标不合法，或当前点已被访问过，或当前点为 '0'，则直接返回*/
            if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] == '0') {
                return;
            }
            /*将当前点标记为已访问*/
            visited[i][j] = true;
            /*分别向上、下、左、右四个方向递归搜索*/
            /*上*/
            dfs(grid, visited, i - 1, j);
            /*下*/
            dfs(grid, visited, i + 1, j);
            /*左*/
            dfs(grid, visited, i, j - 1);
            /*右*/
            dfs(grid, visited, i, j + 1);
        }
    }
}
