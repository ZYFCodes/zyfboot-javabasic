package org.zyf.javabasic.thread.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/5/1  19:38
 */
public class FixedThreadPoolExample {
    public static void main(String[] args) {
        // 创建FixedThreadPool，指定线程数为3
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // 提交任务
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Executing task: " + taskId + " on thread: " + Thread.currentThread().getName());
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
}
