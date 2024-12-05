package org.zyf.javabasic.test.wzzz;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;
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
    public static void main(String[] args) {
        // 启动定时任务
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        // 计算下一个随机午夜的延迟时间（在00:10到00:30之间）
        long initialDelay = calculateRandomInitialDelay();

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务开始执行：" + LocalDateTime.now());
                //开启账号进行评论处理,包括自己的本账号
                CSDNLoginAndSubmitTest.commitDealNew();
                //对现有的一些文章进行阅读
                CSDNTest.v2();
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
     * 计算距离下一个随机午夜的延迟时间（00:10到00:30之间）
     *
     * @return 延迟时间（秒）
     */
    private static long calculateRandomInitialDelay() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 获取今天午夜的时间
        LocalDateTime midnight = LocalDateTime.of(now.toLocalDate(), LocalTime.MIDNIGHT);

        // 随机生成一个分钟数，范围是10到30
        Random random = new Random();
        int randomMinute = random.nextInt(12) + 6;  // 随机值范围是 [10, 30]

        // 将随机分钟数加到午夜时间上
        midnight = midnight.plusMinutes(randomMinute);

        // 如果当前时间已经过了这个随机午夜时刻，则计算到第二天的同一时刻
        if (now.isAfter(midnight)) {
            midnight = midnight.plusDays(1);
        }

        // 计算当前时间到下一个随机午夜的延迟秒数
        return Duration.between(now, midnight).getSeconds();
    }
}
