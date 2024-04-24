package org.zyf.javabasic.algorithm;

/**
 * @program: zyfboot-javabasic
 * @description: 使用矩阵解法计算斐波那契数列
 * @author: zhangyanfeng
 * @create: 2024-04-25 00:54
 **/
public class MatrixFibonacci {
    public static long getFibonacciByMatrix(int index) {
        // 检查输入的索引是否合法
        if (index <= 0) {
            throw new IllegalArgumentException("Index must be a positive integer");
        }

        // 初始斐波那契矩阵
        long[][] fibonacciMatrix = {{1, 1}, {1, 0}};

        // 迭代计算矩阵的 index - 2 次幂
        for (int i = 2; i < index; i++) {
            fibonacciMatrix = matrixMultiply(fibonacciMatrix, new long[][]{{1, 1}, {1, 0}});
        }

        // 结果存储在矩阵的第一行第一列位置
        return fibonacciMatrix[0][0];
    }

    // 矩阵乘法
    private static long[][] matrixMultiply(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = a[0][0] * b[0][0] + a[0][1] * b[1][0];
        result[0][1] = a[0][0] * b[0][1] + a[0][1] * b[1][1];
        result[1][0] = a[1][0] * b[0][0] + a[1][1] * b[1][0];
        result[1][1] = a[1][0] * b[0][1] + a[1][1] * b[1][1];
        return result;
    }

    public static void main(String[] args) {
        // 计算第100000项的斐波那契数
        long startTime1 = System.currentTimeMillis();
        long fibonacci100000 = MatrixFibonacci.getFibonacciByMatrix(100000);
        long endTime1 = System.currentTimeMillis();
        long elapsedTime1 = endTime1 - startTime1;
        System.out.println("Fibonacci(100000) = " + fibonacci100000);
        System.out.println("耗时：" + elapsedTime1 + "毫秒");

        // 计算第2100000000项的斐波那契数
        long startTime2 = System.currentTimeMillis();
        long fibonacci2100000000 = MatrixFibonacci.getFibonacciByMatrix(2100000000);
        long endTime2 = System.currentTimeMillis();
        long elapsedTime2 = endTime2 - startTime2;
        System.out.println("Fibonacci(2100000000) = " + fibonacci2100000000);
        System.out.println("耗时：" + elapsedTime2 + "毫秒");
    }
}
