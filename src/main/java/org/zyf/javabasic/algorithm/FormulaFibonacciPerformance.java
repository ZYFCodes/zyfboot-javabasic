package org.zyf.javabasic.algorithm;

/**
 * @program: zyfboot-javabasic
 * @description: 使用初等代数推导的公式解法
 * @author: zhangyanfeng
 * @create: 2024-04-25 00:26
 **/
public class FormulaFibonacciPerformance {
    public static long getFibonacciByFormula(int index) {
        double temp = Math.sqrt(5.0);
        return (long) ((Math.pow((1 + temp) / 2, index) - Math.pow((1 - temp) / 2, index)) / temp);
    }

    public static void main(String[] args) {
        int[] nValues = {100000, 2100000000};

        for (int n : nValues) {
            long startTime = System.nanoTime();
            long result = getFibonacciByFormula(n);
            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            System.out.println("Fibonacci(" + n + ") = " + result);
            System.out.println("程序运行时间：" + elapsedTime + " 纳秒");
        }
    }
}
