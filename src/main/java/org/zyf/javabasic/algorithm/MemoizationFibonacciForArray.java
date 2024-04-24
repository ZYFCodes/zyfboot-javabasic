package org.zyf.javabasic.algorithm;

import java.math.BigInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 采用递归+数组缓存的方法来计算斐波那契数列
 * @author: zhangyanfeng
 * @create: 2024-04-24 23:22
 **/
public class MemoizationFibonacciForArray {
    private static BigInteger[] memoization;

    public static BigInteger fibonacciWithMemoization(int n) {
        memoization = new BigInteger[n + 1]; // 初始化缓存数组
        return fib(n);
    }

    private static BigInteger fib(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        // 如果缓存中已经有了斐波那契数列的第n项的值，则直接返回缓存结果
        if (memoization[n] != null) {
            return memoization[n];
        }

        // 如果缓存中没有斐波那契数列的第n项的值，则进行递归计算，并将结果存入缓存数组中
        memoization[n] = fib(n - 1).add(fib(n - 2));
        return memoization[n];
    }

    public static void main(String[] args) {
        int n1 = 10000;
        long startTime1 = System.currentTimeMillis();
        BigInteger result1 = fibonacciWithMemoization(n1);
        long endTime1 = System.currentTimeMillis();
        long elapsedTime1 = endTime1 - startTime1;

        System.out.println("Fibonacci(" + n1 + ") = " + result1);
        System.out.println("程序运行时间：" + elapsedTime1 + " 毫秒");

        int n2 = 15000;
        long startTime2 = System.currentTimeMillis();
        BigInteger result2 = fibonacciWithMemoization(n2);
        long endTime2 = System.currentTimeMillis();
        long elapsedTime2 = endTime2 - startTime2;

        System.out.println("Fibonacci(" + n2 + ") = " + result2);
        System.out.println("程序运行时间：" + elapsedTime2 + " 毫秒");

        int n3 = 18000;
        long startTime3 = System.currentTimeMillis();
        BigInteger result3 = fibonacciWithMemoization(n3);
        long endTime3 = System.currentTimeMillis();
        long elapsedTime3 = endTime3 - startTime3;

        System.out.println("Fibonacci(" + n3 + ") = " + result3);
        System.out.println("程序运行时间：" + elapsedTime3 + " 毫秒");
    }
}
