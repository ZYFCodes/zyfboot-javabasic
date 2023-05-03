package org.zyf.javabasic.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  19:00
 */
public class GoodsNRTLockService {
    private Lock lock = new ReentrantLock(false); // 非公平锁
    private int stock = 10;

    public void buyGoods() {
        lock.lock();
        try {
            if (stock > 0) {
                stock--;
                System.out.println(Thread.currentThread().getName() + "购买成功，当前库存：" + stock);
            } else {
                System.out.println(Thread.currentThread().getName() + "购买失败，库存不足");
            }
        } finally {
            lock.unlock();
        }
    }

}
