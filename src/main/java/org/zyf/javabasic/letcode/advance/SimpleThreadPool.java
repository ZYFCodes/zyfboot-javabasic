package org.zyf.javabasic.letcode.advance;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yanfengzhang
 * @description 设计一个简单的线程池，
 * 构造函数参数包括 coreSize（核心线程数）、maxSize（最大线程数）和 queueSize（任务队列大小）。
 * 线程池需要实现一个 submit 方法，用于提交任务（Runnable），并由线程池执行。
 * @date 2023/6/15  23:02
 */
public class SimpleThreadPool {
    // 核心线程数
    private final int coreSize;
    // 最大线程数
    private final int maxSize;
    // 任务队列
    private final BlockingQueue<Runnable> taskQueue;
    // 工作线程数组
    private final WorkerThread[] workers;

    public SimpleThreadPool(int coreSize, int maxSize, int queueSize) {
        this.coreSize = coreSize;
        this.maxSize = maxSize;
        this.taskQueue = new LinkedBlockingQueue<>(queueSize);
        this.workers = new WorkerThread[maxSize];

        // 创建并启动核心线程
        for (int i = 0; i < coreSize; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();
        }
    }

    public void submit(Runnable task) {
        // 提交任务到任务队列
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    // 从任务队列中取出任务并执行
                    Runnable task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        // 创建一个线程池
        int coreSize = 2;
        int maxSize = 5;
        int queueSize = 10;
        SimpleThreadPool threadPool = new SimpleThreadPool(coreSize, maxSize, queueSize);

        // 提交任务到线程池
        for (int i = 1; i <= 10; i++) {
            int taskNumber = i;
            threadPool.submit(() -> {
                System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // 等待一段时间，让任务完成
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 关闭线程池
        for (int i = 0; i < threadPool.maxSize; i++) {
            threadPool.workers[i].interrupt();
        }
    }
}
