package org.zyf.javabasic.algorithm;

import com.google.common.collect.Maps;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * @program: zyfboot-javabasic
 * @description: 采用递归+HashMap缓存
 * @author: zhangyanfeng
 * @create: 2024-04-24 08:43
 **/
public class MemoizationFibonacci {
    private static HashMap<Integer, BigInteger> memoization = Maps.newHashMap();

    public static BigInteger fibonacciWithMemoization(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        // 如果HashMap中已经有了斐波那契数列的第n项的值，则直接返回缓存结果
        if (memoization.containsKey(n)) {
            return memoization.get(n);
        }

        // 如果HashMap中没有斐波那契数列的第n项的值，则进行递归计算，并将结果存入HashMap中
        BigInteger result = fibonacciWithMemoization(n - 1).add(fibonacciWithMemoization(n - 2));
        memoization.put(n, result);
        return result;
    }

    public static void main(String[] args) {
        // 验证斐波那契数列前几项的正确性
        for (int i = 0; i < 10; i++) {
            System.out.println("Fibonacci(" + i + ") = " + fibonacciWithMemoization(i));
        }

        long startTime = System.currentTimeMillis();
        BigInteger result = fibonacciWithMemoization(8000);
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;

        System.out.println("Fibonacci( 8000 ) = " + result);
        System.out.println("程序运行时间：" + elapsedTime + " 毫秒");

        long startTime1 = System.currentTimeMillis();
        BigInteger result1 = fibonacciWithMemoization(10000);
        long endTime1 = System.currentTimeMillis();
        long elapsedTime1 = endTime1 - startTime1;

        System.out.println("Fibonacci(10000) = " + result1);
        System.out.println("程序运行时间：" + elapsedTime1 + " 毫秒");

        long startTime2 = System.currentTimeMillis();
        BigInteger result2 = fibonacciWithMemoization(18400);
        long endTime2 = System.currentTimeMillis();
        long elapsedTime2 = endTime2 - startTime2;

        System.out.println("Fibonacci(18000) = " + result2);
        System.out.println("程序运行时间：" + elapsedTime2 + " 毫秒");
    }
}
