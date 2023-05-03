package org.zyf.javabasic.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  18:54
 */
public class ResourceTest {
    private Lock lock = new ReentrantLock();

    public void methodA() {
        lock.lock();
        try {
            // do something
        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        lock.lock();
        try {
            // do something
        } finally {
            lock.unlock();
        }
    }
}
