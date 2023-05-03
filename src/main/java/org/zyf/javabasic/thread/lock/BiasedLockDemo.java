package org.zyf.javabasic.thread.lock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  18:05
 */
public class BiasedLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread.sleep(5000);
        synchronized (obj) {
            // 获取偏向锁
            System.out.println(Thread.currentThread().getName() + " 持有偏向锁");
            Thread.sleep(5000);
        }
        System.out.println("--------------------");
        synchronized (obj) {
            // 偏向锁失效，升级为轻量级锁
            System.out.println(Thread.currentThread().getName() + " 持有轻量级锁");
        }
    }
}
