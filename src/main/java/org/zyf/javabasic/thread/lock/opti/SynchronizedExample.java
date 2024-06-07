package org.zyf.javabasic.thread.lock.opti;

/**
 * @program: zyfboot-javabasic
 * @description: 同步方法和同步代码块的示例类，以及它们在字节码中的表现。
 * @author: zhangyanfeng
 * @create: 2024-06-06 07:56
 **/
public class SynchronizedExample {
    private int count = 0;
    private final Object lock = new Object();

    // 同步实例方法
    public synchronized void increment() {
        count++;
    }

    // 同步静态方法
    public static synchronized void staticIncrement() {
        // 静态方法体
    }

    // 同步代码块
    public void blockIncrement() {
        synchronized (lock) {
            count++;
        }
    }
}
