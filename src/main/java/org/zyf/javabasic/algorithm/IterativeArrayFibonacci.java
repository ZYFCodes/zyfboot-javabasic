package org.zyf.javabasic.algorithm;

import java.math.BigInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 使用数组缓存+顺序迭代方法来计算斐波那契数列
 * @author: zhangyanfeng
 * @create: 2024-04-24 23:42
 **/
public class IterativeArrayFibonacci {
    public static BigInteger fibonacci(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        // 初始化数组缓存
        BigInteger[] fib = new BigInteger[n + 1];
        fib[0] = BigInteger.ZERO;
        fib[1] = BigInteger.ONE;

        for (int i = 2; i <= n; i++) {
            // 顺序迭代计算每一项的值
            fib[i] = fib[i - 1].add(fib[i - 2]);
        }

        // 返回斐波那契数列的第n项的值
        return fib[n];
    }

    public static void main(String[] args) {
        int[] nValues = {10000, 15000, 20000, 30000, 40000, 10000000};

        for (int n : nValues) {
            long startTime = System.currentTimeMillis();
            BigInteger result = fibonacci(n);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            System.out.println("Fibonacci(" + n + ") = " + result);
            System.out.println("程序运行时间：" + elapsedTime + " 毫秒");
        }
    }
}
