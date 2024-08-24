package org.zyf.javabasic.letcode.hot100.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: N 皇后（困难）
 * @author: zhangyanfeng
 * @create: 2024-08-22 13:48
 **/
public class SolveNQueensSolution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // 用来记录列的状态
        boolean[] cols = new boolean[n];
        // 用来记录主对角线的状态
        boolean[] diag1 = new boolean[2 * n - 1];
        // 用来记录副对角线的状态
        boolean[] diag2 = new boolean[2 * n - 1];
        // 临时棋盘，用来记录皇后的放置位置
        char[][] board = new char[n][n];

        // 初始化棋盘
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        // 从第一行开始回溯
        backtrack(result, board, 0, n, cols, diag1, diag2);
        return result;
    }

    private void backtrack(List<List<String>> result, char[][] board, int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        if (row == n) {
            result.add(construct(board));
            return;
        }

        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row - col + n - 1] || diag2[row + col]) {
                continue;
            }

            // 放置皇后
            board[row][col] = 'Q';
            cols[col] = true;
            diag1[row - col + n - 1] = true;
            diag2[row + col] = true;

            // 递归处理下一行
            backtrack(result, board, row + 1, n, cols, diag1, diag2);

            // 撤回皇后
            board[row][col] = '.';
            cols[col] = false;
            diag1[row - col + n - 1] = false;
            diag2[row + col] = false;
        }
    }

    private List<String> construct(char[][] board) {
        List<String> result = new ArrayList<>();
        for (char[] row : board) {
            result.add(new String(row));
        }
        return result;
    }

    public static void main(String[] args) {
        SolveNQueensSolution solution = new SolveNQueensSolution();

        // 示例 1
        int n1 = 4;
        System.out.println(solution.solveNQueens(n1));
        // 输出: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]

        // 示例 2
        int n2 = 1;
        System.out.println(solution.solveNQueens(n2));
        // 输出: [["Q"]]
    }
}
