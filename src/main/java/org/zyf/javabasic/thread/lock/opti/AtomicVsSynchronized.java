package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 比较使用 AtomicInteger 和 synchronized 关键字的性能
 * @author: zhangyanfeng
 * @create: 2024-06-08 11:10
 **/
public class AtomicVsSynchronized {
    private static final int THREAD_COUNT = 100;
    private static final int ITERATIONS = 1000000;

    // 使用 AtomicInteger 实现计数器
    private static AtomicInteger atomicCounter = new AtomicInteger(0);

    // 使用 synchronized 实现计数器
    private static int synchronizedCounter = 0;

    // 使用 AtomicInteger 进行计数
    private static class AtomicCounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                atomicCounter.incrementAndGet();
            }
        }
    }

    // 使用 synchronized 进行计数
    private static class SynchronizedCounterRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < ITERATIONS; i++) {
                synchronized (AtomicVsSynchronized.class) {
                    synchronizedCounter++;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long startTime;
        long endTime;

        // 测试使用 AtomicInteger 的性能
        startTime = System.currentTimeMillis();
        Thread[] atomicThreads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            atomicThreads[i] = new Thread(new AtomicCounterRunnable());
            atomicThreads[i].start();
        }
        for (int i = 0; i < THREAD_COUNT; i++) {
            atomicThreads[i].join();
        }
        endTime = System.currentTimeMillis();
        System.out.println("AtomicInteger 总耗时：" + (endTime - startTime) + " 毫秒");
        System.out.println("AtomicInteger 计数结果：" + atomicCounter.get());

        // 测试使用 synchronized 的性能
        startTime = System.currentTimeMillis();
        Thread[] syncThreads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i] = new Thread(new SynchronizedCounterRunnable());
            syncThreads[i].start();
        }
        for (int i = 0; i < THREAD_COUNT; i++) {
            syncThreads[i].join();
        }
        endTime = System.currentTimeMillis();
        System.out.println("synchronized 总耗时：" + (endTime - startTime) + " 毫秒");
        System.out.println("synchronized 计数结果：" + synchronizedCounter);
    }
}
