package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: zyfboot-javabasic
 * @description: 比较非公平锁和公平锁的性能
 * @author: zhangyanfeng
 * @create: 2024-06-08 00:07
 **/
public class LockPerformanceComparison {
    private static final int NUM_THREADS = 10;
    private static final int NUM_ITERATIONS = 1000000;
    private static final Lock unfairLock = new ReentrantLock();
    private static final Lock fairLock = new ReentrantLock(true); // 公平锁

    public static void main(String[] args) {
        System.out.println("Unfair Lock Performance Test");
        testLock(unfairLock);

        System.out.println("Fair Lock Performance Test");
        testLock(fairLock);
    }

    private static void testLock(Lock lock) {
        long startTime = System.currentTimeMillis();

        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_ITERATIONS; j++) {
                    lock.lock();
                    try {
                        // 模拟一些计算或任务
                        Math.pow(Math.random(), Math.random());
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
    }
}
