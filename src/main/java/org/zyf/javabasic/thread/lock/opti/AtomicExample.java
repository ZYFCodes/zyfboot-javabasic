package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 使用AtomicInteger来替代显式的锁
 * @author: zhangyanfeng
 * @create: 2024-06-05 23:07
 **/
public class AtomicExample {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new IncrementAtomic());
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time with AtomicInteger: " + (endTime - startTime) + " ms");
    }

    static class IncrementAtomic implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                counter.incrementAndGet();
            }
        }
    }
}
