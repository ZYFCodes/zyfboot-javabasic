package org.zyf.javabasic.thread.base;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/5/1  19:41
 */
public class ScheduledThreadPoolExample {
    public static void main(String[] args) {
        // 创建ScheduledThreadPool
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);

        // 延迟执行任务
        executor.schedule(() -> {
            System.out.println("Task 1 executed after 2 seconds.");
        }, 2, TimeUnit.SECONDS);

        // 固定时间间隔执行任务
        executor.scheduleAtFixedRate(() -> {
            System.out.println("Task 2 executed every 3 seconds.");
        }, 0, 3, TimeUnit.SECONDS);

        // 关闭线程池
        executor.shutdown();
    }
}
