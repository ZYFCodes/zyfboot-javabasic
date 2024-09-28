package org.zyf.javabasic.bizthreadpool.biz.future.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.bizthreadpool.biz.future.DiscoverFeedFutureService;
import org.zyf.javabasic.bizthreadpool.biz.future.model.DiscoverFeedFuture;
import org.zyf.javabasic.bizthreadpool.core.ZYFThreadPool;
import org.zyf.javabasic.bizthreadpool.enums.ResultType;

import java.util.concurrent.Callable;

/**
 * @program: zyfboot-javabasic
 * @description: 发现流异步服务治理封装实现
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:17
 **/
@Service
@Slf4j
public class DiscoverFeedFutureServiceImpl implements DiscoverFeedFutureService {
    @Override
    public DiscoverFeedFuture queryTypesAsync(Callable<?> callable, ResultType topicType) {
        return DiscoverFeedFuture.builder().resultType(topicType).future(ZYFThreadPool.submitCallableTask(callable, topicType)).build();
    }

    @Override
    public DiscoverFeedFuture sortInterventionAsync(Callable<?> callable, ResultType topicType) {
        return DiscoverFeedFuture.builder().resultType(topicType).future(ZYFThreadPool.submitCallableTask(callable, topicType)).build();
    }

    @Override
    public DiscoverFeedFuture fallbackAsync(Callable<?> callable, ResultType topicType) {
        return DiscoverFeedFuture.builder().resultType(topicType).future(ZYFThreadPool.submitCallableTask(callable, topicType)).build();
    }

    @Override
    public DiscoverFeedFuture mergeResultsAsync(Callable<?> callable, ResultType topicType) {
        return DiscoverFeedFuture.builder().resultType(topicType).future(ZYFThreadPool.submitCallableTask(callable, topicType)).build();
    }
}
