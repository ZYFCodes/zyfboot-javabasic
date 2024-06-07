package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: zyfboot-javabasic
 * @description: 优化后的版本
 * @author: zhangyanfeng
 * @create: 2024-06-08 01:23
 **/
public class OptimizedCounter {
    private int count = 0;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        try {
            // Simulate some work that doesn't need to be locked
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
