package org.zyf.javabasic.bizthreadpool.core;

import lombok.extern.log4j.Log4j2;

import java.util.concurrent.*;

/**
 * @program: zyfboot-javabasic
 * @description: 执行线程池封装
 * @author: zhangyanfeng
 * @create: 2024-04-27 11:00
 **/
@Log4j2
public class ZYFThreadPoolExecutor extends ThreadPoolExecutor {
    public ZYFThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ZYFThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ZYFThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ZYFThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                                 BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public void execute(Runnable command) {
        super.execute(new TraceableRunnable(command));
    }

    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
    }

    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
    }

    static class TraceableRunnable implements Runnable {
        final Runnable innerTask;
        public TraceableRunnable(Runnable innerTask) {
            this.innerTask = innerTask;
        }

        public void run() {
            try {
                this.innerTask.run();
            } catch (Throwable var2) {
                log.warn("ZYFThreadPoolExecutor innerTask run error");
                throw new RuntimeException(var2);
            }
        }
    }
}
