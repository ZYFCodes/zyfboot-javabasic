package org.zyf.javabasic.test.wzzz.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.bizdeal.entity.dto.CsdnUserInfo;
import org.zyf.javabasic.test.wzzz.newdeal.CommentSubmitService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: zyfboot-javabasic
 * @description: 批量处理
 * @author: zhangyanfeng
 * @create: 2025-06-28 16:21
 **/
@Component
@Slf4j
public class QueueBatchManager {

    @Resource
    private CommentSubmitService commentSubmitService;
    private final BlockingQueue<CsdnUserInfo> queue = new LinkedBlockingQueue<>();

    public void add(CsdnUserInfo userInfo) throws InterruptedException {
        queue.put(userInfo);
    }

    @PostConstruct
    public void startConsumer() {
        // 创建一个固定大小线程池，用于并发处理
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    List<CsdnUserInfo> batch = new ArrayList<>();

                    // 批量阻塞式取五个数据
                    for (int i = 0; i < 2; i++) {
                        CsdnUserInfo userInfo = queue.take(); // 阻塞等待，保证队列至少有五个
                        batch.add(userInfo);
                    }

                    System.out.println("Starting batch: " + batch);

                    // 批次控制器
                    CountDownLatch latch = new CountDownLatch(batch.size());

                    // 批量并发处理
                    for (CsdnUserInfo userInfo : batch) {
                        executorService.submit(() -> {
                            try {
                                long start = System.currentTimeMillis();
                                String userIdentification = userInfo.getUserIdentification();
                                String pwdOrVerifyCode = userInfo.getPwdOrVerifyCode();
                                log.info("用户【{}】操作对张彦峰在线CSDN所有文章进行评论行为===============================================", userIdentification);

                                log.info("处理用户：{}, 开始操作对张彦峰在线CSDN所有文章进行评论行为！！！！！！！！！", userIdentification);
                                //评论操作执行
                                commentSubmitService.doSubmit(userIdentification, pwdOrVerifyCode);
                                long end = System.currentTimeMillis();
                                String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
                                log.info("处理用户：{}】在{}操作对张彦峰在线CSDN所有文章进行评论行为已完成DoneDoneDoneDoneDoneDoneDoneDone！！！！！！！！！, {}",
                                        userIdentification, currentDate, costTime(start, end));
                            } finally {
                                latch.countDown(); // 完成一个任务
                            }
                        });
                    }

                    // 等待当前批次全部完成
                    latch.await();
                    System.out.println("Batch completed.\n");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        consumerThread.setDaemon(true);
        consumerThread.start();
    }

    private static String costTime(long start, long end) {

        // 计算耗时
        long elapsedTime = end - start;
        long seconds = elapsedTime / 1000;

        if (seconds > 60) {
            long minutes = seconds / 60;
            // 剩余秒数
            seconds = seconds % 60;
            return "   本次总耗时: " + minutes + " 分 " + seconds + " 秒";
        } else {
            return "   本次总耗时: " + seconds + " 秒";
        }
    }
}
