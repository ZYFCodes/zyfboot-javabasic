package org.zyf.javabasic.thread.lock.opti;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: zyfboot-javabasic
 * @description: ceshi
 * @author: zhangyanfeng
 * @create: 2024-06-07 21:03
 **/
public class LockPerformanceTestNew {
    private static final int NUM_OPERATIONS = 100000;
    private static final Map<String, String> map = new HashMap<>();
    private static final ReentrantLock reentrantLock = new ReentrantLock();
    private static final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private static final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
    private static final int NUM_THREADS = 10;

    public static void main(String[] args) throws InterruptedException {
        // Test with ReentrantLock
        long startTime = System.currentTimeMillis();
        Thread[] threads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_OPERATIONS / NUM_THREADS; j++) {
                    reentrantLock.lock();
                    try {
                        map.put("key" + j, "value" + j);
                    } finally {
                        reentrantLock.unlock();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("ReentrantLock Write Time: " + (endTime - startTime) + " ms");

        // Test with ReentrantReadWriteLock
        startTime = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_OPERATIONS / NUM_THREADS; j++) {
                    writeLock.lock();
                    try {
                        map.put("key" + j, "value" + j);
                    } finally {
                        writeLock.unlock();
                    }
                }
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        endTime = System.currentTimeMillis();
        System.out.println("ReentrantReadWriteLock Write Time: " + (endTime - startTime) + " ms");
    }
}
