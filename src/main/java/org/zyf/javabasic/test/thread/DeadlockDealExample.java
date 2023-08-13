package org.zyf.javabasic.test.thread;

/**
 * @program: zyfboot-javabasic
 * @description: 死锁用例解决
 * @author: zhangyanfeng
 * @create: 2023-08-13 22:41
 **/
public class DeadlockDealExample {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Holding resource 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 1: Waiting for resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread 1: Acquired resource 2!");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 2: Holding resource 1...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread 2: Waiting for resource 2...");
                synchronized (resource2) {
                    System.out.println("Thread 2: Acquired resource 2!");
                }
            }
        });

        thread1.start();
        thread2.start();
    }
}
