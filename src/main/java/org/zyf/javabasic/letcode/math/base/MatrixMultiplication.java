package org.zyf.javabasic.letcode.math.base;

import java.util.Arrays;

/**
 * @author yanfengzhang
 * @description 案例饿分析：当处理大规模矩阵乘法时，使用传统的三重循环会导致O(n^3)的时间复杂度。
 * 但是，通过数学优化技巧，如Strassen算法，可以将矩阵乘法的时间复杂度降低到O(n^2.81)。
 * @date 2023/6/17  23:34
 */
public class MatrixMultiplication {
    /**
     * 传统的矩阵乘法
     */
    public static int[][] traditionalMatrixMultiplication(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;

        int[][] result = new int[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return result;
    }

    /**
     * 使用Strassen算法优化的矩阵乘法
     */
    public static int[][] strassenMatrixMultiplication(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int p = B[0].length;

        int[][] result = new int[m][p];

        // Base case: If the matrices are small, use traditional matrix multiplication
        if (m <= 64 || n <= 64 || p <= 64) {
            return traditionalMatrixMultiplication(A, B);
        }

        // Divide the matrices into submatrices
        int newSize = Math.max(Math.max(m, n), p);
        int newSizeHalf = newSize / 2;

        int[][] A11 = new int[newSizeHalf][newSizeHalf];
        int[][] A12 = new int[newSizeHalf][newSizeHalf];
        int[][] A21 = new int[newSizeHalf][newSizeHalf];
        int[][] A22 = new int[newSizeHalf][newSizeHalf];

        int[][] B11 = new int[newSizeHalf][newSizeHalf];
        int[][] B12 = new int[newSizeHalf][newSizeHalf];
        int[][] B21 = new int[newSizeHalf][newSizeHalf];
        int[][] B22 = new int[newSizeHalf][newSizeHalf];

        splitMatrix(A, A11, A12, A21, A22);
        splitMatrix(B, B11, B12, B21, B22);

        // Calculate 7 intermediate matrices using Strassen's formulas
        int[][] M1 = strassenMatrixMultiplication(addMatrices(A11, A22), addMatrices(B11, B22));
        int[][] M2 = strassenMatrixMultiplication(addMatrices(A21, A22), B11);
        int[][] M3 = strassenMatrixMultiplication(A11, subtractMatrices(B12, B22));
        int[][] M4 = strassenMatrixMultiplication(A22, subtractMatrices(B21, B11));
        int[][] M5 = strassenMatrixMultiplication(addMatrices(A11, A12), B22);
        int[][] M6 = strassenMatrixMultiplication(subtractMatrices(A21, A11), addMatrices(B11, B12));
        int[][] M7 = strassenMatrixMultiplication(subtractMatrices(A12, A22), addMatrices(B21, B22));

        // Calculate the final result matrix using Strassen's formulas
        int[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
        int[][] C12 = addMatrices(M3, M5);
        int[][] C21 = addMatrices(M2, M4);
        int[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

        // Combine the submatrices to form the final result
        joinMatrices(C11, C12, C21, C22, result);

        return result;
    }

    // Split a matrix into four submatrices
    public static void splitMatrix(int[][] matrix, int[][] topLeft, int[][] topRight,
                                   int[][] bottomLeft, int[][] bottomRight) {
        int size = matrix.length / 2;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                topLeft[i][j] = matrix[i][j];
                topRight[i][j] = matrix[i][j + size];
                bottomLeft[i][j] = matrix[i + size][j];
                bottomRight[i][j] = matrix[i + size][j + size];
            }
        }
    }

    // Add two matrices
    public static int[][] addMatrices(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }

        return result;
    }

    // Subtract two matrices
    public static int[][] subtractMatrices(int[][] A, int[][] B) {
        int rows = A.length;
        int cols = A[0].length;
        int[][] result = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }

        return result;
    }

    // Join four submatrices into a single matrix
    public static void joinMatrices(int[][] topLeft, int[][] topRight,
                                    int[][] bottomLeft, int[][] bottomRight, int[][] result) {
        int size = topLeft.length;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = topLeft[i][j];
                result[i][j + size] = topRight[i][j];
                result[i + size][j] = bottomLeft[i][j];
                result[i + size][j + size] = bottomRight[i][j];
            }
        }
    }

    // 生成随机矩阵
    public static int[][] generateRandomMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 生成0到9之间的随机整数
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }

    // 打印矩阵
    public static void printMatrix(int[][] matrix) {
//        for (int[] row : matrix) {
//            System.out.println(Arrays.toString(row));
//        }

        //模拟打印几行验证下
        int total = matrix.length;
        int printrows = 1;
        if (total < printrows) {
            return;
        }
        for (int[] row : matrix) {
            if (printrows < 1) {
                break;
            }
            if (row.length > 8) {
                int[] newrow = Arrays.copyOfRange(row, 0, 7);
                System.out.println(Arrays.toString(newrow));
            }
            printrows--;
        }

    }

    public static void main(String[] args) {
        // 矩阵行数
        int rows = 1500;
        // 矩阵列数
        int cols = 1500;

        // 生成两个随机矩阵
        int[][] A = generateRandomMatrix(rows, cols);
        int[][] B = generateRandomMatrix(rows, cols);

        // 传统的矩阵乘法
        long startTime = System.currentTimeMillis();
        int[][] traditionalResult = traditionalMatrixMultiplication(A, B);
        long endTime = System.currentTimeMillis();
        System.out.println("传统矩阵乘法耗时: " + (endTime - startTime) + "ms，计算结果如下：");
        printMatrix(traditionalResult);

        // 使用Strassen算法优化的矩阵乘法
        startTime = System.currentTimeMillis();
        int[][] strassenResult = strassenMatrixMultiplication(A, B);
        endTime = System.currentTimeMillis();
        System.out.println("Strassen算法优化矩阵乘法耗时: " + (endTime - startTime) + "ms，计算结果如下：");
        printMatrix(strassenResult);
    }
}
