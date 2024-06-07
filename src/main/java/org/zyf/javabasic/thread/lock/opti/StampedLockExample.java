package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.locks.StampedLock;

/**
 * @program: zyfboot-javabasic
 * @description: 使用StampedLock的代码示例
 * @author: zhangyanfeng
 * @create: 2024-06-05 23:11
 **/
public class StampedLockExample {
    private static int counter = 0;
    private static final StampedLock lock = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new IncrementWithStampedLock());
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time with StampedLock: " + (endTime - startTime) + " ms");
    }

    static class IncrementWithStampedLock implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                long stamp = lock.writeLock();
                try {
                    counter++;
                } finally {
                    lock.unlockWrite(stamp);
                }
            }
        }
    }
}
