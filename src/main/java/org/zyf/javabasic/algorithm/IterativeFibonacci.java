package org.zyf.javabasic.algorithm;

import java.math.BigInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 采用迭代方式计算斐波那契数列
 * @author: zhangyanfeng
 * @create: 2024-04-24 23:58
 **/
public class IterativeFibonacci {
    public static BigInteger fibonacci(int n) {
        if (n <= 1) {
            return BigInteger.valueOf(n);
        }

        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        BigInteger temp;

        for (int i = 2; i <= n; i++) {
            temp = b;
            b = a.add(b);
            a = temp;
        }

        return b;
    }

    public static void main(String[] args) {
        int[] nValues = {10000, 20000, 80000, 800000, 1000000, 5000000, 10000000, 90000000};

        for (int n : nValues) {
            long startTime = System.currentTimeMillis();
            BigInteger result = fibonacci(n);
            long endTime = System.currentTimeMillis();
            long elapsedTime = endTime - startTime;

            System.out.println("Fibonacci(" + n + ") = ");
            System.out.println("程序运行时间：" + elapsedTime + " 毫秒");
        }
    }
}
