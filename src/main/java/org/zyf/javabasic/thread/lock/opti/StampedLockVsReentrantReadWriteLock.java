package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

/**
 * @program: zyfboot-javabasic
 * @description: 使用 StampedLock 和 ReentrantReadWriteLock 来实现读写分离，并比较它们的性能。
 * @author: zhangyanfeng
 * @create: 2024-06-07 22:35
 **/
public class StampedLockVsReentrantReadWriteLock {
    private static final int NUM_THREADS = 10;
    private static final int NUM_OPERATIONS = 5000000;

    private static volatile int sharedVariable = 0;

    public static void main(String[] args) throws InterruptedException {
        long start, end;

        // Test with StampedLock
        StampedLock stampedLock = new StampedLock();
        start = System.currentTimeMillis();
        Thread[] stampedLockWriteThreads = new Thread[NUM_THREADS];
        Thread[] stampedLockReadThreads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            stampedLockWriteThreads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_OPERATIONS; j++) {
                    long stamp = stampedLock.writeLock();
                    try {
                        sharedVariable++;
                    } finally {
                        stampedLock.unlockWrite(stamp);
                    }
                }
            });
            stampedLockWriteThreads[i].start();

            stampedLockReadThreads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_OPERATIONS; j++) {
                    long stamp = stampedLock.readLock();
                    try {
                        int value = sharedVariable;
                    } finally {
                        stampedLock.unlockRead(stamp);
                    }
                }
            });
            stampedLockReadThreads[i].start();
        }
        for (Thread thread : stampedLockWriteThreads) {
            thread.join();
        }
        for (Thread thread : stampedLockReadThreads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("StampedLock Write and Read Time: " + (end - start) + " ms");

        // Test with ReentrantReadWriteLock
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        Lock writeLock = rwLock.writeLock();
        Lock readLock = rwLock.readLock();
        start = System.currentTimeMillis();
        Thread[] rwLockWriteThreads = new Thread[NUM_THREADS];
        Thread[] rwLockReadThreads = new Thread[NUM_THREADS];
        for (int i = 0; i < NUM_THREADS; i++) {
            rwLockWriteThreads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_OPERATIONS; j++) {
                    writeLock.lock();
                    try {
                        sharedVariable++;
                    } finally {
                        writeLock.unlock();
                    }
                }
            });
            rwLockWriteThreads[i].start();

            rwLockReadThreads[i] = new Thread(() -> {
                for (int j = 0; j < NUM_OPERATIONS; j++) {
                    readLock.lock();
                    try {
                        int value = sharedVariable;
                    } finally {
                        readLock.unlock();
                    }
                }
            });
            rwLockReadThreads[i].start();
        }
        for (Thread thread : rwLockWriteThreads) {
            thread.join();
        }
        for (Thread thread : rwLockReadThreads) {
            thread.join();
        }
        end = System.currentTimeMillis();
        System.out.println("ReentrantReadWriteLock Write and Read Time: " + (end - start) + " ms");
    }
}
