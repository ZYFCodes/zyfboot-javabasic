package org.zyf.javabasic.bizthreadpool.core;

import lombok.extern.log4j.Log4j2;
import org.zyf.javabasic.bizthreadpool.enums.ResultType;

import java.util.concurrent.*;

/**
 * @program: zyfboot-javabasic
 * @description: 线程池配置
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:11
 **/
@Log4j2
public class ZYFThreadPool {
    /**
     * 线程工作方法
     */
    private static ThreadFactory highThreadFactory = r -> {
        Thread thread = new Thread(r);
        thread.setName("BizHighThread" + thread.getName());
        return thread;
    };

    /**
     * 统一线程池资源（设置的相对高一些，防止被用完）
     */
    private final static ZYFThreadPoolTaskExecutor ZYF_EXECUTOR = new ZYFThreadPoolTaskExecutor(
            Runtime.getRuntime().availableProcessors(),
            Runtime.getRuntime().availableProcessors() * 4, 60L, TimeUnit.SECONDS,
            new SynchronousQueue<>(), highThreadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

    /**
     * 提交Callable有Future值的任务
     */
    public static <T> Future<T> submitCallableTask(Callable<T> task, ResultType topicType) {
        log.info("当前提交任务处理类型为：{}-{}", topicType, topicType.getDescription());
        try {
            return ZYF_EXECUTOR.submit(task);
        } catch (Exception e) {
            log.error("高优先任务资源不够, workQueueSize:{}, workingThreadSize:{}, threadPoolSize: {}",
                    ZYF_EXECUTOR.getQueue().size(), ZYF_EXECUTOR.getActiveCount(),
                    ZYF_EXECUTOR.getPoolSize());
            throw new RuntimeException(e);
        }
    }

    /**
     * 添加不需要Future返回结果的高优先级任务
     */
    public static void submitRunnableTask(Runnable task) {
        try {
            ZYF_EXECUTOR.execute(task);
        } catch (Exception e) {
            log.error("ZYF_EXECUTOR execute task exception, workQueueSize:{}, workingThreadSize:{}, threadPoolSize: {}",
                    ZYF_EXECUTOR.getQueue().size(), ZYF_EXECUTOR.getActiveCount(),
                    ZYF_EXECUTOR.getPoolSize());
            throw new RuntimeException(e);
        }
    }
}
