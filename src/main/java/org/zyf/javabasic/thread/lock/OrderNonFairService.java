package org.zyf.javabasic.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  18:32
 */
public class OrderNonFairService {
    // 创建非公平锁
    private Lock lock = new ReentrantLock();
    // 订单数量
    private int orderCount;

    // 添加订单
    public void addOrder() {
        lock.lock();
        try {
            // 处理订单逻辑
            orderCount++;
        } finally {
            lock.unlock();
        }
    }

    // 获取订单数量
    public int getOrderCount() {
        lock.lock();
        try {
            return orderCount;
        } finally {
            lock.unlock();
        }
    }
}
