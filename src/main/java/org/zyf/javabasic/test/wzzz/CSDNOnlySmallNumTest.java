package org.zyf.javabasic.test.wzzz;

import com.google.common.collect.Lists;
import org.zyf.javabasic.common.utils.HttpUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * @program: zyfboot-javabasic
 * @description: 任务：给指定的其他账号开始刷浏览量
 * @author: zhangyanfeng
 * @create: 2024-10-16 23:44
 **/
public class CSDNOnlySmallNumTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        v1();
        //v2();
        //executeBasedOnTime();
    }

    /**
     * 根据当前时间执行不同的方法
     */
    public static void executeBasedOnTime() throws IOException, InterruptedException {
        // 获取当前时间
        LocalTime currentTime = LocalTime.now();
        // 定义凌晨3点的时间
        LocalTime startTime = LocalTime.of(3, 0);
        // 定义晚上11点55分的时间
        LocalTime endTime = LocalTime.of(23, 55);

        // 判断当前时间是否在指定范围内
        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            v2(); // 执行方法1
        } else {
            v1(); // 执行方法2
        }
    }

    public static void v2() throws IOException, InterruptedException {
        int limitViewCount = 10000;
        List<String> zyfUrl = Lists.newArrayList(CSDNArticles.articlesForOnly());
        System.out.println("当前访问次数少于" + limitViewCount + "的文章个数为" + zyfUrl.size());

        // 创建线程池，核心线程数为 CPU 核心数的两倍
        int threadPoolSize = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);

        // 限制并发批次大小（可根据实际服务器能力调整）
        int batchSize = 1000;
        List<List<String>> urlBatches = Lists.partition(zyfUrl, batchSize);

        // 记录任务开始时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS");
        System.out.println(sdf.format(new Date()) + " 开始访问 URL 批次，总批次数：" + urlBatches.size());

        for (int time = 0; time < 3000; time++) {
            int currentTime = time;
            System.out.println(sdf.format(new Date()) + " 执行第 " + currentTime + " 次全列表数据分析");

            // 提交批次任务
            List<Future<?>> futures = new CopyOnWriteArrayList<>();
            for (List<String> batch : urlBatches) {
                Future<?> future = executor.submit(() -> {
                    batch.forEach(url -> {
                        try {
                            String res = HttpUtils.sendPost(url, null); // 发送 POST 请求
                            System.out.println(sdf.format(new Date()) + " 访问网站：" + url);
                        } catch (Exception e) {
                            System.err.println("访问失败，URL: " + url + "，异常信息：" + e.getMessage());
                        }
                    });
                });
                futures.add(future);
            }

            // 等待所有批次任务完成
            for (Future<?> future : futures) {
                try {
                    future.get(); // 阻塞等待每个任务完成
                } catch (Exception e) {
                    System.err.println("批次任务执行失败：" + e.getMessage());
                }
            }
        }

        // 优雅关闭线程池，允许任务尽量完成
        executor.shutdown();
        boolean isCompleted = executor.awaitTermination(15, TimeUnit.HOURS); // 等待更长时间
        if (!isCompleted) {
            System.err.println("部分任务未完成，开始强制关闭线程池！");
            List<Runnable> remainingTasks = executor.shutdownNow(); // 返回未完成的任务
            System.out.println("未完成的任务数：" + remainingTasks.size());
            // 可以记录或重新处理 remainingTasks
        }

        // 日志记录
        System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date()) + " 任务执行结束！");
    }

    public static void v1() throws IOException, InterruptedException {
        int limitViewCount = 10000;
        List<String> zyfUrl = Lists.newArrayList(CSDNArticles.articlesForOnly());
        System.out.println("当前访问次数少于" + limitViewCount + "的文章个数为" + zyfUrl.size());

        for (int time = 0; time < 3000; time++) {
            Calendar cal1 = Calendar.getInstance();
            Date date1 = cal1.getTime();
            System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date1) + "执行访问全列表数据进行分析，当前次数：" + time);
            IntStream.range(0, zyfUrl.size()).forEach(idx -> {
                String urlTest = zyfUrl.get(idx);
                String res = HttpUtils.sendPost(urlTest, null); // 假设这是发送 POST 请求的方法
//                try {
//                    Thread.sleep(6);
//                } catch (InterruptedException e) {
//                    System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
//                            " 访问网站序号：" + idx +
//                            " 存在异常！");
//                    ;
//                }
                System.out.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(Calendar.getInstance().getTime()) +
                        " 访问网站序号：" + idx +
                        " 访问网站：" + urlTest);
            });
        }
    }
}
