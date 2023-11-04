package org.zyf.javabasic.test.thread;

/**
 * @program: zyfboot-javabasic
 * @description: 阻塞
 * @author: zhangyanfeng
 * @create: 2023-10-21 21:47
 **/
public class BlockingExample {
    public static void main(String[] args) {
        Runnable blockingTask = () -> {
            System.out.println("Blocking task is waiting for 3 seconds...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Blocking task is done!");
        };

        Thread thread = new Thread(blockingTask);
        thread.start();

        try {
            thread.join(); // 主线程等待子线程完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread continues.");
    }
}
