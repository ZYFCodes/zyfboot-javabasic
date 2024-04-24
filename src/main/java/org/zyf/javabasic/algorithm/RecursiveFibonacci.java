package org.zyf.javabasic.algorithm;

/**
 * @program: zyfboot-javabasic
 * @description: Fibonacci算法
 * @author: zhangyanfeng
 * @create: 2024-04-24 08:22
 **/
public class RecursiveFibonacci {

    /**
     * 当传入n=50时，递归方法在电脑上耗时高达35.549秒。
     */
    public static int fibonacciRecursive(int n) {
        // 边界条件：当n为0或1时，斐波那契数列的值分别为0和1
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        // 递归调用：求解F(n-1)和F(n-2)，然后返回它们的和
        else {
            return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
        }
    }

    public static void main(String[] args) {
        System.out.println("简单递归方法验证");
        // 验证斐波那契数列前几项的正确性
        // 测试不同n值下的性能
        for (int n = 0; n <= 50; n += 5) {
            long startTime = System.nanoTime();
            int result = fibonacciRecursive(n);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000; // 毫秒
            System.out.println("Fibonacci(" + n + ") = " + result + ", Time: " + duration + "ms");
        }
    }
}
