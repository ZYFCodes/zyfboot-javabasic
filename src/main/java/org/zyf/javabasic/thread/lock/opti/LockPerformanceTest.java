package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @program: zyfboot-javabasic
 * @description: 分别使用 ReentrantReadWriteLock 和 ReentrantLock 进行读写操作，并比较两者的执行时间。
 * @author: zhangyanfeng
 * @create: 2024-06-07 20:59
 **/
public class LockPerformanceTest {
    private static final int READ_THREADS = 10;
    private static final int WRITE_THREADS = 2;
    private static final int READ_ITERATIONS = 10000;
    private static final int WRITE_ITERATIONS = 1000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("ReentrantLock Performance Test");
        testLockPerformance(new ReentrantLock());

        System.out.println("ReentrantReadWriteLock Performance Test");
        testReadWriteLockPerformance(new ReentrantReadWriteLock());
    }

    private static void testLockPerformance(Lock lock) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        SharedResource resource = new SharedResource(lock);

        Thread[] readThreads = new Thread[READ_THREADS];
        Thread[] writeThreads = new Thread[WRITE_THREADS];

        for (int i = 0; i < READ_THREADS; i++) {
            readThreads[i] = new Thread(() -> {
                for (int j = 0; j < READ_ITERATIONS; j++) {
                    resource.read();
                }
            });
        }

        for (int i = 0; i < WRITE_THREADS; i++) {
            writeThreads[i] = new Thread(() -> {
                for (int j = 0; j < WRITE_ITERATIONS; j++) {
                    resource.write(j);
                }
            });
        }

        for (Thread thread : readThreads) {
            thread.start();
        }
        for (Thread thread : writeThreads) {
            thread.start();
        }

        for (Thread thread : readThreads) {
            thread.join();
        }
        for (Thread thread : writeThreads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
    }

    private static void testReadWriteLockPerformance(ReadWriteLock rwLock) throws InterruptedException {
        long startTime = System.currentTimeMillis();
        SharedReadWriteResource resource = new SharedReadWriteResource(rwLock);

        Thread[] readThreads = new Thread[READ_THREADS];
        Thread[] writeThreads = new Thread[WRITE_THREADS];

        for (int i = 0; i < READ_THREADS; i++) {
            readThreads[i] = new Thread(() -> {
                for (int j = 0; j < READ_ITERATIONS; j++) {
                    resource.read();
                }
            });
        }

        for (int i = 0; i < WRITE_THREADS; i++) {
            writeThreads[i] = new Thread(() -> {
                for (int j = 0; j < WRITE_ITERATIONS; j++) {
                    resource.write(j);
                }
            });
        }

        for (Thread thread : readThreads) {
            thread.start();
        }
        for (Thread thread : writeThreads) {
            thread.start();
        }

        for (Thread thread : readThreads) {
            thread.join();
        }
        for (Thread thread : writeThreads) {
            thread.join();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
    }

    static class SharedResource {
        private final Lock lock;
        private int value = 0;

        SharedResource(Lock lock) {
            this.lock = lock;
        }

        public void read() {
            lock.lock();
            try {
                // Simulate read operation
                int temp = value;
            } finally {
                lock.unlock();
            }
        }

        public void write(int newValue) {
            lock.lock();
            try {
                value = newValue;
            } finally {
                lock.unlock();
            }
        }
    }

    static class SharedReadWriteResource {
        private final ReadWriteLock rwLock;
        private final Lock readLock;
        private final Lock writeLock;
        private int value = 0;

        SharedReadWriteResource(ReadWriteLock rwLock) {
            this.rwLock = rwLock;
            this.readLock = rwLock.readLock();
            this.writeLock = rwLock.writeLock();
        }

        public void read() {
            readLock.lock();
            try {
                // Simulate read operation
                int temp = value;
            } finally {
                readLock.unlock();
            }
        }

        public void write(int newValue) {
            writeLock.lock();
            try {
                value = newValue;
            } finally {
                writeLock.unlock();
            }
        }
    }
}
