package org.zyf.javabasic.test.wzzz;

import com.google.common.collect.Lists;
import org.zyf.javabasic.common.utils.HttpUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: zyfboot-javabasic
 * @description: 任务：给指定的其他账号开始刷浏览量
 * @author: zhangyanfeng
 * @create: 2024-10-16 23:44
 **/
public class CSDNOnlySmallNumTest {
    public static void main(String[] args) throws IOException, InterruptedException {
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
}
