package org.zyf.javabasic.thread.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/5/1  19:44
 */
public class SingleThreadExecutorExample {
    public static void main(String[] args) {
        // 创建SingleThreadExecutor
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 提交任务
        for (int i = 0; i < 5; i++) {
            final int taskId = i;
            executor.execute(() -> {
                System.out.println("Executing task: " + taskId + " on thread: " + Thread.currentThread().getName());
            });
        }

        // 关闭线程池
        executor.shutdown();
    }
}
