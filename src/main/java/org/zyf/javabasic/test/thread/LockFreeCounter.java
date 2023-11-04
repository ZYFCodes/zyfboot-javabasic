package org.zyf.javabasic.test.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: zyfboot-javabasic
 * @description: 如何使用CAS loop来实现一个简单的无锁计数器
 * @author: zhangyanfeng
 * @create: 2023-10-21 22:50
 **/
public class LockFreeCounter {
    private AtomicInteger counter = new AtomicInteger(0);

    public void increment() {
        int expected, newValue;
        do {
            // 读取当前值
            expected = counter.get();
            // 计算新值
            newValue = expected + 1;
        } while (!counter.compareAndSet(expected, newValue)); // CAS操作
    }

    public int getValue() {
        return counter.get();
    }

    public static void main(String[] args) {
        LockFreeCounter counter = new LockFreeCounter();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    counter.increment();
                }
            }).start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter value: " + counter.getValue());
    }
}
