package org.zyf.javabasic.thread.lock.opti;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @program: zyfboot-javabasic
 * @description: 使用ConcurrentLinkedQueue的简单示例
 * @author: zhangyanfeng
 * @create: 2024-06-08 02:02
 **/
public class LockFreeExample {
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws InterruptedException {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                queue.offer(i);
            }
        });

        Thread consumer = new Thread(() -> {
            while (true) {
                Integer value = queue.poll();
                if (value != null) {
                    System.out.println("Consumed: " + value);
                }
            }
        });

        long startTime = System.currentTimeMillis();
        producer.start();
        consumer.start();

        producer.join();
        consumer.join(1000);

        long endTime = System.currentTimeMillis();
        System.out.println("Execution Time: " + (endTime - startTime) + " ms");
    }
}
