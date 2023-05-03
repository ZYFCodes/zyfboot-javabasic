package org.zyf.javabasic.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  18:59
 */
public class OrderRTLockService {
    private Lock lock = new ReentrantLock();
    private int count = 0;

    public void addOrder() {
        lock.lock();
        try {
            // 订单号生成
            count++;
            System.out.println(Thread.currentThread().getName() + "生成订单号：" + count);
            // 其他操作
        } finally {
            lock.unlock();
        }
    }

    public void deleteOrder() {
        lock.lock();
        try {
            // 订单删除
            System.out.println(Thread.currentThread().getName() + "删除订单号：" + count);
            count--;
            // 其他操作
        } finally {
            lock.unlock();
        }
    }

}
