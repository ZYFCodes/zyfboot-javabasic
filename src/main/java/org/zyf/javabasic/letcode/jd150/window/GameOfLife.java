package org.zyf.javabasic.letcode.jd150.window;

/**
 * @program: zyfboot-javabasic
 * @description: gameOfLife
 * @author: zhangyanfeng
 * @create: 2024-08-25 11:50
 **/
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        // 定义邻居位置的偏移量
        int[] neighbors = {0, 1, -1};

        // 获取网格的行数和列数
        int rows = board.length;
        int cols = board[0].length;

        // 创建与原始网格大小相同的复制网格
        int[][] copyBoard = new int[rows][cols];

        // 将原始网格的状态复制到复制网格中
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                copyBoard[row][col] = board[row][col];
            }
        }

        // 遍历每个细胞，更新网格状态
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // 统计当前细胞的八个邻居中的活细胞数量
                int liveNeighbors = 0;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        // 排除自己
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)) {
                            int r = row + neighbors[i];
                            int c = col + neighbors[j];

                            // 确保邻居在网格范围内并且是活细胞
                            if (r >= 0 && r < rows && c >= 0 && c < cols && copyBoard[r][c] == 1) {
                                liveNeighbors++;
                            }
                        }
                    }
                }

                // 根据活邻居数和当前状态更新细胞状态
                if (copyBoard[row][col] == 1) { // 当前细胞是活的
                    if (liveNeighbors < 2 || liveNeighbors > 3) {
                        board[row][col] = 0; // 死亡
                    }
                } else { // 当前细胞是死的
                    if (liveNeighbors == 3) {
                        board[row][col] = 1; // 复活
                    }
                }
            }
        }
    }
}
