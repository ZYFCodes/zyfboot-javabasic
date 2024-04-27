package org.zyf.javabasic.bizthreadpool.biz.future;

import org.zyf.javabasic.bizthreadpool.biz.future.model.DiscoverFeedFuture;
import org.zyf.javabasic.bizthreadpool.enums.ResultType;

import java.util.concurrent.Callable;

/**
 * @program: zyfboot-javabasic
 * @description: 发现流异步服务基本类
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:14
 **/
public interface DiscoverFeedFutureService {
    /**
     * 执行查询类型的接口
     * 专注于按照查询条件查询发现流的类型，并返回一个异步结果。
     */
    DiscoverFeedFuture queryTypesAsync(Callable<?> callable, ResultType topicType);

    /**
     * 排序干预处理的接口
     * 用于对结果进行排序干预处理，改变其原有顺序，也是异步的。
     */
    DiscoverFeedFuture sortInterventionAsync(Callable<?> callable, ResultType topicType);

    /**
     * 兜底处理的接口
     * 提供了一个兜底策略，以便在主流程失败时使用。
     */
    DiscoverFeedFuture fallbackAsync(Callable<?> callable, ResultType topicType);

    /**
     * 合并结果处理的接口
     * 负责将不同来源的结果合并成一个单一的结果集。
     */
    DiscoverFeedFuture mergeResultsAsync(Callable<?> callable, ResultType topicType);

}
