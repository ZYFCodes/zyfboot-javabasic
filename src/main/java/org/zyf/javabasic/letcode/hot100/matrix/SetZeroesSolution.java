package org.zyf.javabasic.letcode.hot100.matrix;

/**
 * @program: zyfboot-javabasic
 * @description: 矩阵置零
 * @author: zhangyanfeng
 * @create: 2024-08-21 22:46
 **/
public class SetZeroesSolution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;     // 获取矩阵的行数
        int n = matrix[0].length;  // 获取矩阵的列数

        // 步骤 1: 判断第一行和第一列是否需要被置为 0
        boolean firstRowZero = false;
        boolean firstColZero = false;

        // 检查第一行是否有 0
        for (int j = 0; j < n; j++) {
            if (matrix[0][j] == 0) {
                firstRowZero = true;
                break;
            }
        }

        // 检查第一列是否有 0
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColZero = true;
                break;
            }
        }

        // 步骤 2: 使用第一行和第一列作为标记，记录其他行和列的 0
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0; // 标记行
                    matrix[0][j] = 0; // 标记列
                }
            }
        }

        // 步骤 3: 根据标记将相应的行和列置为 0
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < n; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < m; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        // 步骤 4: 根据之前的标记将第一行和第一列置为 0
        if (firstRowZero) {
            for (int j = 0; j < n; j++) {
                matrix[0][j] = 0;
            }
        }

        if (firstColZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    public static void main(String[] args) {
        SetZeroesSolution solution = new SetZeroesSolution();

        // 测试用例 1
        int[][] matrix1 = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        solution.setZeroes(matrix1);
        printMatrix(matrix1);  // 输出: [[1, 0, 1], [0, 0, 0], [1, 0, 1]]

        // 测试用例 2
        int[][] matrix2 = {
                {0, 1, 2, 0},
                {3, 4, 5, 2},
                {1, 3, 1, 5}
        };
        solution.setZeroes(matrix2);
        printMatrix(matrix2);  // 输出: [[0, 0, 0, 0], [0, 4, 5, 0], [0, 3, 1, 0]]
    }

    // 辅助函数：打印矩阵
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
