package org.zyf.javabasic.gdtest;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/8/1  19:04
 */
public class DeadlockExp {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (resource1) {
                System.out.println("Thread 1: Holding resource 1");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 1 Waiting for resource 2 ");
                synchronized (resource2) {
                    System.out.println("Thread 1: Acquired resource 1");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (resource2) {
                System.out.println("Thread 2: Holding resource 2");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("Thread 2 Waiting for resource 1 ");
                synchronized (resource1) {
                    System.out.println("Thread 2: Acquired resource 2");
                }
            }
        });

        thread1.start();
        thread2.start();

    }
}
