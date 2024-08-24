package org.zyf.javabasic.letcode.hot100.matrix;

import java.util.Arrays;

/**
 * @program: zyfboot-javabasic
 * @description: 旋转图像
 * @author: zhangyanfeng
 * @create: 2024-08-21 23:21
 **/
public class RotateSolution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // 遍历每一层
        for (int i = 0; i < n / 2; ++i) {
            // 每一层的元素交换
            for (int j = 0; j < (n + 1) / 2; ++j) {
                // 交换四个位置上的元素
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }

    public static void main(String[] args) {
        RotateSolution solution = new RotateSolution();

        // Test Case 1
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        solution.rotate(matrix1);
        printMatrix(matrix1);
        // Expected output:
        // 7 4 1
        // 8 5 2
        // 9 6 3

        // Test Case 2
        int[][] matrix2 = {
                {5, 1, 9, 11},
                {2, 4, 8, 10},
                {13, 3, 6, 7},
                {15, 14, 12, 16}
        };
        solution.rotate(matrix2);
        printMatrix(matrix2);
        // Expected output:
        // 15 13 2 5
        // 14 3 4 1
        // 12 6 8 9
        // 16 7 10 11

        // Test Case 3
        int[][] matrix3 = {
                {1, 2},
                {3, 4}
        };
        solution.rotate(matrix3);
        printMatrix(matrix3);
        // Expected output:
        // 3 1
        // 4 2
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println();
    }
}
