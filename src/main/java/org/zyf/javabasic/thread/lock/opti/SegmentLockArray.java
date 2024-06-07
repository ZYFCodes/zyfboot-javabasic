package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: zyfboot-javabasic
 * @description: 将数组分成多个段，每个段使用一个独立的锁。这样，当多个线程访问不同段的数据时，可以并行执行，而不会相互阻塞
 * @author: zhangyanfeng
 * @create: 2024-06-08 01:06
 **/
public class SegmentLockArray {
    private static final int NUM_SEGMENTS = 10;
    private static final int SEGMENT_SIZE = 1000;
    private static final int ARRAY_SIZE = NUM_SEGMENTS * SEGMENT_SIZE;
    private final int[] array = new int[ARRAY_SIZE];
    private final Lock[] locks = new ReentrantLock[NUM_SEGMENTS];

    public SegmentLockArray() {
        for (int i = 0; i < NUM_SEGMENTS; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    public void increment(int index) {
        int segment = index / SEGMENT_SIZE;
        locks[segment].lock();
        try {
            array[index]++;
        } finally {
            locks[segment].unlock();
        }
    }

    public int get(int index) {
        int segment = index / SEGMENT_SIZE;
        locks[segment].lock();
        try {
            return array[index];
        } finally {
            locks[segment].unlock();
        }
    }

}
