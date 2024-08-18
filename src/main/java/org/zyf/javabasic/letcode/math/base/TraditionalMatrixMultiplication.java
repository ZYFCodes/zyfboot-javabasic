package org.zyf.javabasic.letcode.math.base;

import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: 传统三重循环法
 * @author: zhangyanfeng
 * @create: 2023-06-17 23:22
 **/
public class TraditionalMatrixMultiplication {
    public static void main(String[] args) {
        int n = 2000; // 增大矩阵规模以更好地测试性能
        int[][] A = generateRandomMatrix(n);
        int[][] B = generateRandomMatrix(n);

        long startTime = System.nanoTime();
        int[][] C = traditionalMatrixMultiplication(A, B);
        long endTime = System.nanoTime();

        System.out.println("传统方法耗时: " + (endTime - startTime) / 1e6 + " 毫秒");
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
}
