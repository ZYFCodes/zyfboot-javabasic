package org.zyf.javabasic.test.wzzz.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.bizdeal.entity.dto.CsdnUserInfo;
import org.zyf.javabasic.test.wzzz.newdeal.CommentSubmitService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @program: zyfboot-javabasic
 * @description: 队列管理类
 * @author: zhangyanfeng
 * @create: 2025-06-25 23:15
 **/
@Component
@Slf4j
public class QueueManager {

    @Resource
    private CommentSubmitService commentSubmitService;

    // 修改队列泛型为 UserInfo
    private final BlockingQueue<CsdnUserInfo> queue = new LinkedBlockingQueue<>();

    public void add(CsdnUserInfo userInfo) throws InterruptedException {
        queue.put(userInfo); // 阻塞式入队
    }

    @PostConstruct
    public void startConsumer() {
        Thread consumerThread = new Thread(() -> {
            try {
                while (true) {
                    CsdnUserInfo userInfo = queue.take(); // 阻塞式消费

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

