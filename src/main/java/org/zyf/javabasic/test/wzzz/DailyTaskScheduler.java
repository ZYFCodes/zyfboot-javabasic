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
        long initialDelay = calculateRandomDelayWithRandomRange();
        System.out.println("Random delay with random range: " + initialDelay + " seconds");

        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("任务开始执行：" + LocalDateTime.now());
                //开启账号进行评论处理,包括自己的本账号
                CSDNLoginAndSubmitTest.commitDealNew();
                //对现有的一些文章进行阅读
                CSDNTest.v2();
                //先清理统计
                CSDNLoginAndSubmitTest.clearFrequencyMap();
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
     * 计算距离下一个随机时间段内的延迟时间
     * 随机选择开始和结束时间
     *
     * @return 延迟时间（秒）
     */
    private static long calculateRandomDelayWithRandomRange() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 随机生成 startHour 和 endHour，范围是6到22之间
        Random random = new Random();
        int startHour = random.nextInt(17);  // 随机范围从6到22
        int endHour = random.nextInt(17);  // 随机范围从6到22

        // 保证 startHour < endHour
        while (endHour <= startHour) {
            endHour = random.nextInt(17);
        }

        // 获取随机生成的开始时间和结束时间的 LocalDateTime
        LocalDateTime startTime = LocalDateTime.of(now.toLocalDate(), LocalTime.of(startHour, 0));
        LocalDateTime endTime = LocalDateTime.of(now.toLocalDate(), LocalTime.of(endHour, 0));

        // 如果当前时间已经过了该时间段，则将时间段设置为第二天
        if (now.isAfter(endTime)) {
            startTime = startTime.plusDays(1);
            endTime = endTime.plusDays(1);
        }

        // 随机生成一个分钟数，范围是开始时间和结束时间之间的分钟数
        long randomMinutes = random.nextInt((int) Duration.between(startTime, endTime).toMinutes()) + 1;

        // 计算目标随机时间
        LocalDateTime randomTime = startTime.plusMinutes(randomMinutes);

        // 计算当前时间到目标随机时间的延迟秒数
        return Duration.between(now, randomTime).getSeconds();
    }

}
