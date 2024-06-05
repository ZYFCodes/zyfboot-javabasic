package org.zyf.javabasic.thread.threadLocal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.zyf.javabasic.thread.threadLocal.ContextManager.runWithExistingContext;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义线程池 ContextAwareThreadPoolExecutor
 * @author: zhangyanfeng
 * @create: 2024-06-02 20:23
 **/
public class ContextAwareThreadPoolExecutor extends ThreadPoolExecutor {

    public ContextAwareThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public static ContextAwareThreadPoolExecutor newFixedThreadPool(int nThreads) {
        return new ContextAwareThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());
    }

    @Override
    public void execute(Runnable command) {
        ContextManager context = ContextManager.getCurrentContext();
        super.execute(() -> runWithExistingContext(context, command::run));
    }
}
