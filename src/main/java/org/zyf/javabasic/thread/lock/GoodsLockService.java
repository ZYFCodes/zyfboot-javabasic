package org.zyf.javabasic.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  20:05
 */
public class GoodsLockService {
    private int stock = 0;
    private ReentrantLock lock = new ReentrantLock();

    public void reduceStock(int num) {
        lock.lock();
        try {
            if (stock >= num) {
                stock -= num;
            }
        } finally {
            lock.unlock();
        }
    }

    //其他方法省略...
}
