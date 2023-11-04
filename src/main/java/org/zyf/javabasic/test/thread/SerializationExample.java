package org.zyf.javabasic.test.thread;

/**
 * @program: zyfboot-javabasic
 * @description: 串行化的概念
 * @author: zhangyanfeng
 * @create: 2023-10-21 22:03
 **/
public class SerializationExample {
    private int counter = 0;

    // 串行化访问计数器的方法
    public synchronized void incrementCounter() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }

    public static void main(String[] args) {
        SerializationExample example = new SerializationExample();
        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                example.incrementCounter();
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + example.getCounter());
    }
}
