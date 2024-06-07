package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: zyfboot-javabasic
 * @description: 使用了ReentrantLock来保护对共享资源（counter）的访问，确保同一时间只有一个线程可以对计数器进行操作。
 * @author: zhangyanfeng
 * @create: 2024-06-05 22:54
 **/
public class SyncLockExample {
    private static int counter = 0;
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new IncrementWithLock());
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time with lock: " + (endTime - startTime) + " ms");
    }

    static class IncrementWithLock implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                lock.lock();
                try {
                    counter++;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
