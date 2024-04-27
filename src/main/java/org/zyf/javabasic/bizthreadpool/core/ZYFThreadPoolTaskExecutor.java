package org.zyf.javabasic.bizthreadpool.core;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @program: zyfboot-javabasic
 * @description: 线程池任务执行器
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:06
 **/
public class ZYFThreadPoolTaskExecutor implements ExecutorService, InitializingBean {
    private final Object poolSizeMonitor = new Object();
    private int corePoolSize = 1;
    private int maxPoolSize = Integer.MAX_VALUE;
    private int keepAliveSeconds = 60;
    private boolean allowCoreThreadTimeOut = false;
    private int queueCapacity = Integer.MAX_VALUE;
    private ThreadPoolExecutor threadPoolExecutor;
    private String threadPoolName = "";
    private RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.AbortPolicy();

    public ZYFThreadPoolTaskExecutor() {
    }

    public ZYFThreadPoolTaskExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this.threadPoolExecutor = new ZYFThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public ZYFThreadPoolTaskExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        this.threadPoolExecutor = new ZYFThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ZYFThreadPoolTaskExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        this.threadPoolExecutor = new ZYFThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public ZYFThreadPoolTaskExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        this.threadPoolExecutor = new ZYFThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    public void setCorePoolSize(int corePoolSize) {
        synchronized(this.poolSizeMonitor) {
            this.corePoolSize = corePoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setCorePoolSize(corePoolSize);
            }

        }
    }

    public int getCorePoolSize() {
        synchronized(this.poolSizeMonitor) {
            return this.corePoolSize;
        }
    }

    public void setMaxPoolSize(int maxPoolSize) {
        synchronized(this.poolSizeMonitor) {
            this.maxPoolSize = maxPoolSize;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setMaximumPoolSize(maxPoolSize);
            }

        }
    }

    public int getMaxPoolSize() {
        synchronized(this.poolSizeMonitor) {
            return this.maxPoolSize;
        }
    }

    public void setKeepAliveSeconds(int keepAliveSeconds) {
        synchronized(this.poolSizeMonitor) {
            this.keepAliveSeconds = keepAliveSeconds;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.setKeepAliveTime((long)keepAliveSeconds, TimeUnit.SECONDS);
            }

        }
    }

    public int getKeepAliveSeconds() {
        synchronized(this.poolSizeMonitor) {
            return this.keepAliveSeconds;
        }
    }

    public void setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        synchronized(this.poolSizeMonitor) {
            this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
            if (this.threadPoolExecutor != null) {
                this.threadPoolExecutor.allowCoreThreadTimeOut(allowCoreThreadTimeOut);
            }

        }
    }

    public void setQueueCapacity(int queueCapacity) {
        this.queueCapacity = queueCapacity;
    }

    protected BlockingQueue<Runnable> createQueue(int queueCapacity) {
        return (BlockingQueue)(queueCapacity > 0 ? new LinkedBlockingQueue(queueCapacity) : new SynchronousQueue());
    }

    public ThreadPoolExecutor getThreadPoolExecutor() throws IllegalStateException {
        Assert.state(this.threadPoolExecutor != null, "ThreadPoolTaskExecutor not initialized");
        return this.threadPoolExecutor;
    }

    public int getPoolSize() {
        return this.getThreadPoolExecutor().getPoolSize();
    }

    public int getActiveCount() {
        return this.getThreadPoolExecutor().getActiveCount();
    }

    public void execute(Runnable task) {
        Executor executor = this.getThreadPoolExecutor();

        try {
            executor.execute(task);
        } catch (RejectedExecutionException var4) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        }
    }

    public Future<?> submit(Runnable task) {
        ExecutorService executor = this.getThreadPoolExecutor();

        try {
            return executor.submit(task);
        } catch (RejectedExecutionException var4) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        }
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return this.threadPoolExecutor.invokeAll(tasks);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return this.threadPoolExecutor.invokeAll(tasks, timeout, unit);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
        return this.threadPoolExecutor.invokeAny(tasks);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.threadPoolExecutor.invokeAny(tasks, timeout, unit);
    }

    public void shutdown() {
        this.threadPoolExecutor.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.threadPoolExecutor.shutdownNow();
    }

    public boolean isShutdown() {
        return this.threadPoolExecutor.isShutdown();
    }

    public boolean isTerminated() {
        return this.threadPoolExecutor.isTerminated();
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.threadPoolExecutor.awaitTermination(timeout, unit);
    }

    public <T> Future<T> submit(Callable<T> task) {
        ExecutorService executor = this.getThreadPoolExecutor();

        try {
            return executor.submit(task);
        } catch (RejectedExecutionException var4) {
            throw new TaskRejectedException("Executor [" + executor + "] did not accept task: " + task, var4);
        }
    }

    public <T> Future<T> submit(Runnable task, T result) {
        return this.threadPoolExecutor.submit(task, result);
    }

    public boolean prefersShortLivedTasks() {
        return this.threadPoolExecutor.prestartCoreThread();
    }

    public void afterPropertiesSet() throws Exception {
        ThreadFactory threadFactory = new ThreadFactory() {
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("ZYFThreadPoolTaskExecutor" + ZYFThreadPoolTaskExecutor.this.threadPoolName);
                return thread;
            }
        };
        BlockingQueue<Runnable> queue = this.createQueue(this.queueCapacity);
        ThreadPoolExecutor executor = new ZYFThreadPoolExecutor(this.corePoolSize, this.maxPoolSize, (long)this.keepAliveSeconds, TimeUnit.SECONDS, queue, threadFactory, this.rejectedExecutionHandler);
        if (this.allowCoreThreadTimeOut) {
            executor.allowCoreThreadTimeOut(true);
        }

        this.threadPoolExecutor = executor;
    }

    public void setThreadPoolExecutor(ZYFThreadPoolExecutor threadPoolExecutor) {
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public void setRejectedExecutionHandler(RejectedExecutionHandler rejectedExecutionHandler) {
        this.rejectedExecutionHandler = rejectedExecutionHandler;
    }

    public void setThreadPoolName(String threadPoolName) {
        this.threadPoolName = threadPoolName;
    }

    public long getTaskCount() {
        return this.getThreadPoolExecutor().getTaskCount();
    }

    public BlockingQueue<Runnable> getQueue() {
        return this.getThreadPoolExecutor().getQueue();
    }
}
