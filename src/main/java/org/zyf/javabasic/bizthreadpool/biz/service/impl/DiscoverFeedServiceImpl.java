package org.zyf.javabasic.bizthreadpool.biz.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.bizthreadpool.biz.future.DiscoverFeedFutureService;
import org.zyf.javabasic.bizthreadpool.biz.future.model.DiscoverFeedFuture;
import org.zyf.javabasic.bizthreadpool.biz.service.DiscoverFeedService;
import org.zyf.javabasic.bizthreadpool.biz.service.model.DiscoverFeedRequest;
import org.zyf.javabasic.bizthreadpool.biz.service.model.DiscoverFeedRes;
import org.zyf.javabasic.bizthreadpool.core.ZYFThreadPoolExecutor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @program: zyfboot-javabasic
 * @description: 发现流对应实现
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:43
 **/
@Service
@Log4j2
public class DiscoverFeedServiceImpl implements DiscoverFeedService {

    @Autowired
    private DiscoverFeedFutureService feedFutureService;


    @Override
    public DiscoverFeedRes getRecommendFeeds(DiscoverFeedRequest request) {

        // 执行查询类型的异步接口
        DiscoverFeedFuture queryFuture = feedFutureService.queryTypesAsync(() -> getRecommendFeedRes(request), request.getTopicType());

        // 执行排序干预处理的异步接口
        DiscoverFeedFuture sortFuture = feedFutureService.sortInterventionAsync(() -> sortIntervention(queryFuture), request.getTopicType());

        // 处理兜底策略
        if (sortFuture == null || sortFuture.getFuture() == null) {
            // 执行兜底处理的异步接口
            DiscoverFeedFuture fallbackFuture = feedFutureService.fallbackAsync(() -> fallback(request), request.getTopicType());
            try {
                return (DiscoverFeedRes) fallbackFuture.getFuture().get();
            } catch (InterruptedException | ExecutionException e) {
                log.info("兜底返回相关兜底推荐内容信息返回异常");
                return null;
            }
        }

        // 处理结果合并
        return mergeResults(queryFuture, sortFuture);
    }

    /**
     * 模拟召回对应推荐内容
     *
     * @param request
     * @return
     */
    private DiscoverFeedRes getRecommendFeedRes(DiscoverFeedRequest request) {
        log.info("分析用户并进行推荐内容返回");
        // 实际处理推荐流的业务逻辑，这里只是示例
        // 可以根据传入的请求参数进行相应的处理，获取推荐流信息
        // 假设这里是获取推荐流信息的业务逻辑
        DiscoverFeedRes result = DiscoverFeedRes.builder().build();
        // result.setFeedItems(获取的推荐流信息);
        // result.setHasNext(是否有下一页);
        // result.setLastTime(最后一个Item的时间戳);
        return result;
    }

    /**
     * 模拟排序
     *
     * @param queryFuture
     * @return
     */
    private DiscoverFeedRes sortIntervention(DiscoverFeedFuture queryFuture) {
        // 实际处理排序干预的业务逻辑，这里只是示例
        // 假设这里是对查询结果进行排序干预处理的业务逻辑
        try {
            DiscoverFeedRes queryRes = (DiscoverFeedRes) queryFuture.getFuture().get();
            log.info("排序分析处理：分析推荐内容进行分析并排序");
            return DiscoverFeedRes.builder().build();
        } catch (InterruptedException | ExecutionException e) {
            log.info("排序分析处理：分析推荐内容进行分析并排序 -----异常");
            return null;
        }
    }

    /**
     * 模拟兜底处理
     *
     * @param request
     * @return
     */
    private DiscoverFeedRes fallback(DiscoverFeedRequest request) {
        // 实际处理兜底的业务逻辑，这里只是示例
        // 假设这里是兜底处理的业务逻辑
        log.info("兜底处理分析处理：兜底返回相关兜底推荐内容信息");
        return DiscoverFeedRes.builder().build();
    }

    private DiscoverFeedRes mergeResults(DiscoverFeedFuture queryFuture, DiscoverFeedFuture sortFuture) {
        // 实际处理结果合并的业务逻辑，这里只是示例
        // 假设这里是将查询结果和排序结果进行合并的业务逻辑
        try {
            log.info("返回结果整合分析：返回实际推荐的数据内容");
            DiscoverFeedRes queryResult = (DiscoverFeedRes) queryFuture.getFuture().get();
            DiscoverFeedRes sortResult = (DiscoverFeedRes) sortFuture.getFuture().get();
            // 合并结果逻辑...
            // 返回一个合并后的结果作为示例
            return DiscoverFeedRes.builder().build();
        } catch (InterruptedException | ExecutionException e) {
            log.info("返回结果整合分析：返回实际推荐的数据内容   ---- 异常");
            return null;
        }
    }

    // 创建自定义线程池
    ZYFThreadPoolExecutor threadPoolExecutor = new ZYFThreadPoolExecutor(
            4, 8, 60L, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>());

    @Override
    public DiscoverFeedRes getLiveFeeds(DiscoverFeedRequest request) {

        CompletableFuture<DiscoverFeedRes> queryFuture = CompletableFuture.supplyAsync(() -> {
            // 实际处理推荐流的业务逻辑，这里只是示例
            // 假设这里是获取推荐流信息的业务逻辑
            return getRecommendFeedRes(request);
        }, threadPoolExecutor);

        CompletableFuture<DiscoverFeedRes> sortFuture = queryFuture.thenApplyAsync(this::sortIntervention, threadPoolExecutor);
        // 实现方法略，与getRecommendFeeds类似
        return fallbackAndMerge(queryFuture, sortFuture);
    }

    private DiscoverFeedRes sortIntervention(DiscoverFeedRes feedRes) {
        // 实际处理排序干预的业务逻辑，这里只是示例
        // 假设这里是对查询结果进行排序干预处理的业务逻辑
        log.info("排序分析处理：分析推荐内容进行分析并排序");
        return feedRes;
    }

    private DiscoverFeedRes fallbackAndMerge(CompletableFuture<DiscoverFeedRes> queryFuture,
                                             CompletableFuture<DiscoverFeedRes> sortFuture) {
        CompletableFuture<DiscoverFeedRes> fallbackFuture = queryFuture.exceptionally(throwable -> {
            // 实际处理兜底的业务逻辑，这里只是示例
            // 假设这里是兜底处理的业务逻辑
            log.info("兜底处理分析处理：兜底返回相关兜底推荐内容信息");
            return DiscoverFeedRes.builder().build(); // 返回一个空结果作为示例
        });

        return fallbackFuture.thenCombineAsync(sortFuture, (fallbackRes, sortRes) -> {
            // 实际处理结果合并的业务逻辑，这里只是示例
            // 假设这里是将查询结果和排序结果进行合并的业务逻辑
            // 合并结果逻辑...
            log.info("返回结果整合分析：返回实际推荐的数据内容");
            // 返回一个合并后的结果作为示例
            return DiscoverFeedRes.builder().build();
        }, threadPoolExecutor).join();
    }

}
