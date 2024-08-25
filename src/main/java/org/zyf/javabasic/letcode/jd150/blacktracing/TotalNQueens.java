package org.zyf.javabasic.letcode.jd150.blacktracing;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: N 皇后 II
 * @author: zhangyanfeng
 * @create: 2024-08-25 18:11
 **/
public class TotalNQueens {
    public int totalNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>(); // 存储所有解决方案
        backtrack(solutions, new ArrayList<>(), n, new boolean[n], new boolean[2 * n - 1], new boolean[2 * n - 1], 0);
        return solutions.size(); // 返回解决方案的数量
    }

    private void backtrack(List<List<String>> solutions, List<String> board, int n, boolean[] cols, boolean[] diag1, boolean[] diag2, int row) {
        // 递归终止条件：如果所有行都被处理完毕
        if (row == n) {
            solutions.add(new ArrayList<>(board)); // 记录当前有效的解决方案
            return;
        }

        // 尝试在当前行的每一列放置皇后
        for (int col = 0; col < n; col++) {
            int d1 = row - col + (n - 1); // 主要对角线索引
            int d2 = row + col;           // 副对角线索引

            // 检查当前位置是否安全
            if (!cols[col] && !diag1[d1] && !diag2[d2]) {
                char[] rowArray = new char[n];
                for (int i = 0; i < n; i++) {
                    rowArray[i] = '.'; // 初始化当前行
                }
                rowArray[col] = 'Q'; // 放置皇后
                board.add(new String(rowArray)); // 记录当前行的字符串形式

                // 标记当前位置为占用
                cols[col] = true;
                diag1[d1] = true;
                diag2[d2] = true;

                // 递归处理下一行
                backtrack(solutions, board, n, cols, diag1, diag2, row + 1);

                // 撤销当前选择
                board.remove(board.size() - 1);
                cols[col] = false;
                diag1[d1] = false;
                diag2[d2] = false;
            }
        }
    }
}
