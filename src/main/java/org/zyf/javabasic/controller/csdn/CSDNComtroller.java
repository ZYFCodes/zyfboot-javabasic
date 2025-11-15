package org.zyf.javabasic.controller.csdn;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zyf.javabasic.aop.bizdeal.entity.dto.CsdnUserInfo;
import org.zyf.javabasic.test.wzzz.newdeal.CommentSubmitService;
import org.zyf.javabasic.test.wzzz.newdeal.CsdnFavoriteService;
import org.zyf.javabasic.test.wzzz.newdeal.CsdnLikeService;
import org.zyf.javabasic.test.wzzz.newdeal.CsdnSubscribeService;
import org.zyf.javabasic.test.wzzz.queue.QueueBatchManager;
import org.zyf.javabasic.test.wzzz.queue.QueueManager;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * @program: zyfboot-javabasic
 * @description: CSDN操作合集
 * @author: zhangyanfeng
 * @create: 2025-04-18 13:03
 **/
@RestController
@RequestMapping(value = "/api/v2", produces = {"application/json"})
@Slf4j
@Api(value = "CSDN控制器")
public class CSDNComtroller {

    @Resource
    private CommentSubmitService commentSubmitService;
    @Resource
    private CsdnLikeService csdnLikeService;
    @Resource
    private CsdnFavoriteService csdnFavoriteService;
    @Resource
    private CsdnSubscribeService cdnSubscribeService;
    @Resource
    private QueueBatchManager queueBatchManager;

    @PostMapping("/doLikesAndFavoritesAndComments")
    @ApiOperation(value = "操作对张彦峰在线CSDN所有文章进行点赞、收藏、评论等行为")
    public String doLikesAndFavoritesAndComments(@RequestBody CsdnUserInfo csdnUserInfo) throws Exception {
        String userIdentification = csdnUserInfo.getUserIdentification();
        String pwdOrVerifyCode = csdnUserInfo.getPwdOrVerifyCode();
        log.info("用户【{}】操作对张彦峰在线CSDN所有文章进行点赞、收藏、评论等行为===============================================", userIdentification);
        CompletableFuture.runAsync(() -> {
            long start = System.currentTimeMillis();
            // 模拟一个长时间任务
            log.info("处理用户：【{}】, 开始操作对张彦峰在线CSDN所有文章进行点赞、收藏、评论等行为！！！！！！！！！", userIdentification);

            //执行点赞行为
            csdnLikeService.doLikes(userIdentification, pwdOrVerifyCode);

            //进行收藏行为
            csdnFavoriteService.doFavorites(userIdentification, pwdOrVerifyCode);

            //评论操作执行
            commentSubmitService.doSubmit(userIdentification, pwdOrVerifyCode);

            //专栏收藏
            cdnSubscribeService.doSubscribes(userIdentification, pwdOrVerifyCode);
            long end = System.currentTimeMillis();
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("处理用户：{}在{}操作对张彦峰在线CSDN所有文章进行点赞、收藏、评论等行为已完成DoneDoneDoneDoneDoneDoneDoneDone, {}！！！！！！！！！",
                    userIdentification, currentDate, costTime(start, end));
        });

        return userIdentification + " 任务已提交, 后台异步执行中！！！！！！！！！！！！！";
    }

    @PostMapping("/doLikes")
    @ApiOperation(value = "操作对张彦峰在线CSDN所有文章进行点赞行为")
    public String doLikes(@RequestBody CsdnUserInfo csdnUserInfo) throws Exception {
        long start = System.currentTimeMillis();
        String userIdentification = csdnUserInfo.getUserIdentification();
        String pwdOrVerifyCode = csdnUserInfo.getPwdOrVerifyCode();
        log.info("用户【{}】操作对张彦峰在线CSDN所有文章进行点赞行为===============================================", userIdentification);
        CompletableFuture.runAsync(() -> {
            // 模拟一个长时间任务
            log.info("处理用户【{}】, 开始操作对张彦峰在线CSDN所有文章进行点赞行为！！！！！！！！！", userIdentification);
            //执行点赞行为
            csdnLikeService.doLikes(userIdentification, pwdOrVerifyCode);
            long end = System.currentTimeMillis();
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("处理用户【{}】在{}操作对张彦峰在线CSDN所有文章进行点赞行为已完成DoneDoneDoneDoneDoneDoneDoneDone！！！！！！！！！, {}",
                    userIdentification, currentDate, costTime(start, end));
        });

        return userIdentification + " 任务已提交, 后台异步执行中！！！！！！！！！！！！！";
    }

    @PostMapping("/doFavorites")
    @ApiOperation(value = "操作对张彦峰在线CSDN所有文章进行收藏行为")
    public String doFavorites(@RequestBody CsdnUserInfo csdnUserInfo) throws Exception {
        long start = System.currentTimeMillis();
        String userIdentification = csdnUserInfo.getUserIdentification();
        String pwdOrVerifyCode = csdnUserInfo.getPwdOrVerifyCode();
        log.info("用户【{}】操作对张彦峰在线CSDN所有文章进行收藏行为===============================================", userIdentification);
        CompletableFuture.runAsync(() -> {
            // 模拟一个长时间任务
            log.info("处理用户：{}, 开始操作对张彦峰在线CSDN所有文章进行收藏行为！！！！！！！！！", userIdentification);
            //进行收藏行为
            csdnFavoriteService.doFavorites(userIdentification, pwdOrVerifyCode);
            long end = System.currentTimeMillis();
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("处理用户：{}】在{}操作对张彦峰在线CSDN所有文章进行收藏行为已完成DoneDoneDoneDoneDoneDoneDoneDone！！！！！！！！！, {}",
                    userIdentification, currentDate, costTime(start, end));
        });

        return userIdentification + " 任务已提交, 后台异步执行中！！！！！！！！！！！！！";
    }

    @PostMapping("/doComments")
    @ApiOperation(value = "操作对张彦峰在线CSDN所有文章进行评论行为")
    public String doComments(@RequestBody CsdnUserInfo csdnUserInfo) throws Exception {
        long start = System.currentTimeMillis();
        String userIdentification = csdnUserInfo.getUserIdentification();
        String pwdOrVerifyCode = csdnUserInfo.getPwdOrVerifyCode();
        log.info("用户【{}】操作对张彦峰在线CSDN所有文章进行评论行为===============================================", userIdentification);
        CompletableFuture.runAsync(() -> {
            // 模拟一个长时间任务
            log.info("处理用户：{}, 开始操作对张彦峰在线CSDN所有文章进行评论行为！！！！！！！！！", userIdentification);
            //评论操作执行
            commentSubmitService.doSubmit(userIdentification, pwdOrVerifyCode);
            long end = System.currentTimeMillis();
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("处理用户：{}】在{}操作对张彦峰在线CSDN所有文章进行评论行为已完成DoneDoneDoneDoneDoneDoneDoneDone！！！！！！！！！, {}",
                    userIdentification, currentDate, costTime(start, end));
        });

        return userIdentification + " 任务已提交, 后台异步执行中！！！！！！！！！！！！！";
    }

    @PostMapping("/doNewComments")
    @ApiOperation(value = "操作对张彦峰在线CSDN所有文章进行评论行为 === 启用新方式")
    public String doNewComments(@RequestBody CsdnUserInfo csdnUserInfo) throws Exception {
        String userIdentification = csdnUserInfo.getUserIdentification();
        try {
            queueBatchManager.add(csdnUserInfo);
            return "UserInfo added: " + userIdentification;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "Failed to add userInfo:" + userIdentification;
        }
    }

    @PostMapping("/doSubscribes")
    @ApiOperation(value = "操作对张彦峰在线CSDN所有专栏进行订阅行为")
    public String doSubscribes(@RequestBody CsdnUserInfo csdnUserInfo) throws Exception {
        long start = System.currentTimeMillis();
        String userIdentification = csdnUserInfo.getUserIdentification();
        String pwdOrVerifyCode = csdnUserInfo.getPwdOrVerifyCode();
        log.info("用户【{}】操作对张彦峰在线CSDN所有专栏进行订阅行为===============================================", userIdentification);
        CompletableFuture.runAsync(() -> {
            // 模拟一个长时间任务
            log.info("处理用户：{}, 开始操作对张彦峰在线CSDN所有专栏进行订阅行为！！！！！！！！！", userIdentification);
            //评论操作执行
            cdnSubscribeService.doSubscribes(userIdentification, pwdOrVerifyCode);
            csdnLikeService.doLikes(userIdentification, pwdOrVerifyCode);
            long end = System.currentTimeMillis();
            String currentDate = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS").format(new Date());
            log.info("处理用户：{}】在{}操作对张彦峰在线CSDN所有专栏进行订阅行为已完成DoneDoneDoneDoneDoneDoneDoneDone！！！！！！！！！, {}",
                    userIdentification, currentDate, costTime(start, end));
        });

        return userIdentification + " 任务已提交, 后台异步执行中！！！！！！！！！！！！！";
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
