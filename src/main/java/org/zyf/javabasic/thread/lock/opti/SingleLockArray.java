package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: zyfboot-javabasic
 * @description: 使用一个全局锁来保护整个数组。所有线程在访问数组时都需要获取这把锁，因此会导致更多的锁竞争和阻塞。
 * @author: zhangyanfeng
 * @create: 2024-06-08 01:02
 **/
public class SingleLockArray {
    private static final int ARRAY_SIZE = 10000;
    private final int[] array = new int[ARRAY_SIZE];
    private final Lock lock = new ReentrantLock();

    public void increment(int index) {
        lock.lock();
        try {
            array[index]++;
        } finally {
            lock.unlock();
        }
    }

    public int get(int index) {
        lock.lock();
        try {
            return array[index];
        } finally {
            lock.unlock();
        }
    }
}
