package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @program: zyfboot-javabasic
 * @description: 对比
 * @author: zhangyanfeng
 * @create: 2024-06-08 01:07
 **/
public class LockArrayPerComparison {
    private static final int NUM_SEGMENTS = 10;
    private static final int SEGMENT_SIZE = 1000;
    private static final int ARRAY_SIZE = NUM_SEGMENTS * SEGMENT_SIZE;

    public static void main(String[] args) throws InterruptedException {
        SegmentLockArray array = new SegmentLockArray();
        int numThreads = 100;
        Thread[] threads = new Thread[numThreads];

        // Writing threads
        for (int i = 0; i < numThreads / 2; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int index = ThreadLocalRandom.current().nextInt(ARRAY_SIZE);
                    array.increment(index);
                }
            });
        }

        // Reading threads
        for (int i = numThreads / 2; i < numThreads; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int index = ThreadLocalRandom.current().nextInt(ARRAY_SIZE);
                    array.get(index);
                }
            });
        }

        long startTime = System.currentTimeMillis();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time with Segment Locks: " + (endTime - startTime) + " ms");

        // Compare with single lock
        SingleLockArray singleLockArray = new SingleLockArray();
        Thread[] singleLockThreads = new Thread[numThreads];

        // Writing threads
        for (int i = 0; i < numThreads / 2; i++) {
            singleLockThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int index = ThreadLocalRandom.current().nextInt(ARRAY_SIZE);
                    singleLockArray.increment(index);
                }
            });
        }

        // Reading threads
        for (int i = numThreads / 2; i < numThreads; i++) {
            singleLockThreads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    int index = ThreadLocalRandom.current().nextInt(ARRAY_SIZE);
                    singleLockArray.get(index);
                }
            });
        }

        startTime = System.currentTimeMillis();
        for (Thread thread : singleLockThreads) {
            thread.start();
        }
        for (Thread thread : singleLockThreads) {
            thread.join();
        }
        endTime = System.currentTimeMillis();
        System.out.println("Execution Time with Single Lock: " + (endTime - startTime) + " ms");
    }
}
