package org.zyf.javabasic.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  18:52
 */
public class CounterTest {
    private int count;
    private Lock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
            System.out.println(Thread.currentThread().getName() + ": count=" + count);
        } finally {
            lock.unlock();
        }
    }
}
