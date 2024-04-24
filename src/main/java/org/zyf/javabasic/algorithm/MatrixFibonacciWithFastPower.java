package org.zyf.javabasic.algorithm;

/**
 * @program: zyfboot-javabasic
 * @description: 使用矩阵解法结合快速幂
 * @author: zhangyanfeng
 * @create: 2024-04-25 01:06
 **/
public class MatrixFibonacciWithFastPower {
    private static final long[][] INIT_MATRIX = {{1, 1}, {1, 0}};
    private static final long[][] UNIT_MATRIX = {{1, 0}, {0, 1}};

    public static long getFibonacciByMatrixAndFastPower(int index) {
        // 检查索引的有效性
        if (index <= 0) {
            throw new IllegalArgumentException("Index must be a positive integer");
        }

        // 边界情况处理
        if (index == 1 || index == 2) {
            return 1;
        }

        // 初始结果为单位矩阵
        long[][] result = UNIT_MATRIX;
        long[][] temp = INIT_MATRIX;
        int m = index - 2;

        // 利用快速幂算法计算矩阵的高次幂
        while (m != 0) {
            if ((m & 1) == 1) {
                result = matrixMultiply(temp, result);
            }
            temp = matrixMultiply(temp, temp);
            m >>= 1;
        }

        // 结果矩阵的第一个元素即为斐波那契数列的第n项的值
        return result[0][0];
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
        long startTime1 = System.nanoTime();
        long fibonacci100000 = MatrixFibonacciWithFastPower.getFibonacciByMatrixAndFastPower(100000);
        long endTime1 = System.nanoTime();
        double elapsedTime1 = (endTime1 - startTime1) / 1_000_000.0; // 转换为毫秒
        System.out.println("Fibonacci(100000) = " + fibonacci100000);
        System.out.println("耗时：" + elapsedTime1 + "毫秒");

        // 计算第2100000000项的斐波那契数
        long startTime2 = System.nanoTime();
        long fibonacci2100000000 = MatrixFibonacciWithFastPower.getFibonacciByMatrixAndFastPower(2100000000);
        long endTime2 = System.nanoTime();
        double elapsedTime2 = (endTime2 - startTime2) / 1_000_000.0; // 转换为毫秒
        System.out.println("Fibonacci(2100000000) = " + fibonacci2100000000);
        System.out.println("耗时：" + elapsedTime2 + "毫秒");
    }
}
