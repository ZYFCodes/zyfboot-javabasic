package org.zyf.javabasic.test.wzzz;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @program: zyfboot-javabasic
 * @description: DailyTaskScheduler
 * @author: zhangyanfeng
 * @create: 2024-11-30 23:43
 **/
public class DailyTaskScheduler {
    // 创建一个线程池
    private static ExecutorService executorService = Executors.newFixedThreadPool(3);

    public static void main(String[] args) {
        // 启动定时任务
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // 计算下一个随机时间
        long initialDelay = calculateRandomDelay();
        System.out.println("Random delay with random range: " + initialDelay + " seconds");

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务开始执行：" + LocalDateTime.now());
                executorService.submit(() -> {
                    // 对现有的一些文章进行阅读
                    CSDNTest.v2();
                });
                // 异步执行任务
                executorService.submit(() -> {
                    // 开启账号进行评论处理,包括自己的本账号
                    commitDeal();
                });
            }
        }, initialDelay, 24 * 60 * 60, TimeUnit.SECONDS);

        // 保持主线程活跃，避免程序退出
        try {
            Thread.sleep(Long.MAX_VALUE); // 保持主线程不结束
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取当前时间并根据当天的随机时间执行任务
     */
    private static void commitDeal() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();
        System.out.println("当前执行时间：" + now);

        // 生成当天0点到17点之间的随机时间
        LocalTime randomTime = generateRandomTimeForToday();

        // 计算当前时间到随机时间的延迟
        long delaySeconds = java.time.Duration.between(now.toLocalTime(), randomTime).getSeconds();

        // 如果当前时间已经超过了随机时间，则延迟到明天同一时间
        if (delaySeconds < 0) {
            randomTime = randomTime.plusHours(24); // 加24小时
            delaySeconds = java.time.Duration.between(now.toLocalTime(), randomTime).getSeconds();
        }

        // 打印随机时间和延迟
        System.out.println("随机时间为：" + randomTime + ", 延迟时间为：" + delaySeconds + "秒");

        // 延迟执行指定任务
        long finalDelaySeconds = delaySeconds;
        executorService.submit(() -> {
            try {
                // 等待指定的延迟时间后执行
                TimeUnit.SECONDS.sleep(finalDelaySeconds);

                // 执行评论处理
                System.out.println("执行评论处理和清理统计任务：" + LocalDateTime.now());
                CSDNLoginAndSubmitTest.commitDealNew();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 生成当天0点到17点之间的随机时间
     *
     * @return 随机时间
     */
    private static LocalTime generateRandomTimeForToday() {
        // 生成0到17点之间的随机小时和分钟
        int randomHour = (int) (Math.random() * 18) + 1; // 随机小时，范围[0, 17]
        int randomMinute = (int) (Math.random() * 60); // 随机分钟，范围[0, 59]

        // 返回当天的随机时间
        return LocalTime.of(randomHour, randomMinute);
    }

    /**
     * 计算从当前时间到今天0点10分之间的随机延迟
     *
     * @return 延迟秒数
     */
    private static long calculateRandomDelay() {
        // 获取当前时间
        LocalTime now = LocalTime.now();

        // 计算今天0点到0点10分的时间
        LocalTime targetTime = LocalTime.MIDNIGHT.plusMinutes(10);

        // 计算当前时间到目标时间的秒数
        long remainingSeconds = java.time.Duration.between(now, targetTime).getSeconds();

        // 如果当前时间已经超过0点10分，则计算第二天0点10分的延迟
        if (remainingSeconds < 0) {
            remainingSeconds = 24 * 60 * 60 + remainingSeconds; // 加一天的秒数
        }

        // 在0到600秒之间生成一个随机延迟
        long randomDelay = (long) (Math.random() * 600);

        // 返回随机延迟后的最终延迟秒数
        return remainingSeconds + randomDelay;
    }

}
