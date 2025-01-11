package org.zyf.javabasic.test.wzzz;

import java.time.LocalDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @program: zyfboot-javabasic
 * @description: ImmediateTaskExecutor
 * @author: zhangyanfeng
 * @create: 2025-01-06 01:08
 **/
public class ImmediateTaskExecutor {
    // 创建线程池，用于任务执行
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        // 定义一个示例任务
        Runnable task = createTask();

        // 立刻执行任务
        executeImmediately(task);

        // 保持主线程活跃，避免程序退出
        try {
            Thread.sleep(Long.MAX_VALUE); // 保持主线程不结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 创建示例任务
     *
     * @return 一个任务实例
     */
    private static Runnable createTask() {
        return () -> {
            System.out.println("任务开始执行：" + LocalDateTime.now());
            //开启账号进行评论处理,包括自己的本账号
            CSDNLoginAndSubmitTest.commitDealNew();
            //对现有的一些文章进行阅读
            CSDNTest.v2();
            //先清理统计
            CSDNLoginAndSubmitTest.clearFrequencyMap();
        };
    }

    /**
     * 立即执行指定任务
     *
     * @param task 要执行的任务
     */
    private static void executeImmediately(Runnable task) {
        System.out.println("准备立即执行任务：" + LocalDateTime.now());
        scheduler.execute(() -> {
            System.out.println("立即任务开始：" + LocalDateTime.now());
            task.run();
            System.out.println("立即任务结束：" + LocalDateTime.now());
        });
    }
}
