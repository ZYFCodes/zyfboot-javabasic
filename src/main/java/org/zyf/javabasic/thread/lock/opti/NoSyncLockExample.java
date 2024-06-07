package org.zyf.javabasic.thread.lock.opti;

/**
 * @program: zyfboot-javabasic
 * @description: 不使用任何同步机制，直接操作共享资源。
 * @author: zhangyanfeng
 * @create: 2024-06-05 22:55
 **/
public class NoSyncLockExample {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threads[i] = new Thread(new IncrementWithoutLock());
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time without lock: " + (endTime - startTime) + " ms");
    }

    static class IncrementWithoutLock implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                counter++;
            }
        }
    }
}
