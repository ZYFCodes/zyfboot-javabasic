package org.zyf.javabasic.letcode.math.base;

import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: Strassen算法
 * @author: zhangyanfeng
 * @create: 2023-06-17 23:34
 **/
public class StrassenMatrixMultiplication {
    private static final int THRESHOLD = 1000; // 阈值

    public static void main(String[] args) {
        int n = 2000; // 增大矩阵规模以更好地测试性能
        int[][] A = generateRandomMatrix(n);
        int[][] B = generateRandomMatrix(n);

        long startTime = System.nanoTime();
        int[][] C = strassenMatrixMultiplication(A, B);
        long endTime = System.nanoTime();

        System.out.println("Strassen算法耗时: " + (endTime - startTime) / 1e6 + " 毫秒");
    }

    public static int[][] strassenMatrixMultiplication(int[][] A, int[][] B) {
        int n = A.length;

        if (n <= THRESHOLD) {
            return traditionalMatrixMultiplication(A, B);
        }

        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];
        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        splitMatrix(A, A11, 0, 0);
        splitMatrix(A, A12, 0, newSize);
        splitMatrix(A, A21, newSize, 0);
        splitMatrix(A, A22, newSize, newSize);
        splitMatrix(B, B11, 0, 0);
        splitMatrix(B, B12, 0, newSize);
        splitMatrix(B, B21, newSize, 0);
        splitMatrix(B, B22, newSize, newSize);

        int[][] M1 = strassenMatrixMultiplication(addMatrices(A11, A22), addMatrices(B11, B22));
        int[][] M2 = strassenMatrixMultiplication(addMatrices(A21, A22), B11);
        int[][] M3 = strassenMatrixMultiplication(A11, subtractMatrices(B12, B22));
        int[][] M4 = strassenMatrixMultiplication(A22, subtractMatrices(B21, B11));
        int[][] M5 = strassenMatrixMultiplication(addMatrices(A11, A12), B22);
        int[][] M6 = strassenMatrixMultiplication(subtractMatrices(A21, A11), addMatrices(B11, B12));
        int[][] M7 = strassenMatrixMultiplication(subtractMatrices(A12, A22), addMatrices(B21, B22));

        int[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
        int[][] C12 = addMatrices(M3, M5);
        int[][] C21 = addMatrices(M2, M4);
        int[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

        int[][] C = new int[n][n];
        joinMatrix(C, C11, 0, 0);
        joinMatrix(C, C12, 0, newSize);
        joinMatrix(C, C21, newSize, 0);
        joinMatrix(C, C22, newSize, newSize);

        return C;
    }

    public static void splitMatrix(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                C[i1][j1] = P[i2][j2];
            }
        }
    }

    public static void joinMatrix(int[][] P, int[][] C, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++) {
                P[i2][j2] = C[i1][j1];
            }
        }
    }

    public static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    public static int[][] generateRandomMatrix(int n) {
        Random rand = new Random();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = rand.nextInt(10);
            }
        }
        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    public static int[][] traditionalMatrixMultiplication(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }
}
