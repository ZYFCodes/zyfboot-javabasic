package org.zyf.javabasic.thread.lock;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/5/3  18:08
 */
public class LightWeightLockDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                // 获取轻量级锁
                System.out.println(Thread.currentThread().getName() + " 持有轻量级锁");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            synchronized (obj) {
                // 轻量级锁升级为重量级锁
                System.out.println(Thread.currentThread().getName() + " 持有重量级锁");
            }
        }).start();
    }
}
