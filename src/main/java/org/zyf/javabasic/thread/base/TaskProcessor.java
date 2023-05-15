package org.zyf.javabasic.thread.base;

import lombok.Data;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/5/1  17:56
 */
public class TaskProcessor {
    private ThreadPoolExecutor executor;

    public TaskProcessor() {
        // 创建ThreadPoolExecutor实例，进行自定义配置
        // 核心线程数
        int corePoolSize = 5;
        // 最大线程数
        int maxPoolSize = 10;
        // 非核心线程的空闲超时时间
        long keepAliveTime = 60;
        // 任务队列容量
        int queueCapacity = 100;

        executor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueCapacity)
        );
        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
    }

    public void processTask(Task task) {
        // 提交任务给线程池
        executor.execute(() -> {
            // 执行任务的逻辑
            // ...
            System.out.println("Processing task: " + task.getId());
            // ...
        });
    }

    @Data
    static class Task {
        private int id;

        Task(int id) {
            this.id = id;
        }
    }

    public void shutdown() {
        // 关闭线程池
        executor.shutdown();
    }

    public static void main(String[] args) {
        TaskProcessor taskProcessor = new TaskProcessor();
        // 模拟提交任务
        for (int i = 1; i <= 10; i++) {
            Task task = new Task(i);
            taskProcessor.processTask(task);
        }
        // 关闭线程池
        taskProcessor.shutdown();
    }
}
