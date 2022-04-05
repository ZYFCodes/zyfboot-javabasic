package org.zyf.javabasic.designpatterns.template.retry;

import lombok.extern.log4j.Log4j2;

/**
 * @author yanfengzhang
 * @description 模板方式处理重试逻辑
 * @date 2022/3/15  00:13
 */
@Log4j2
public abstract class RetryTemplate<R> {
    /**
     * 默认重试次数
     */
    private static final int DEFAULT_RETRY_TIME = 1;
    /**
     * 定义重试次数
     */
    private int retryTime = DEFAULT_RETRY_TIME;
    /**
     * 重试的睡眠时间
     */
    private int sleepMills = 0;

    /**
     * 业务处理的睡眠时间
     *
     * @return 睡眠时间
     */
    public int getSleepMills() {
        return sleepMills;
    }

    /**
     * 获取重试次数
     *
     * @return 重试次数
     */
    public int getRetryTime() {
        return retryTime;
    }

    /**
     * 规定业务处理的睡眠时间
     *
     * @param sleepMills 睡眠时间（毫秒）
     * @return 重试模版
     */
    public RetryTemplate<R> setSleepMills(int sleepMills) {
        if (sleepMills < 0) {
            throw new IllegalArgumentException("sleepMills should equal or bigger than 0");
        }

        this.sleepMills = sleepMills;
        return this;
    }

    /**
     * 规定业务处理的重试次数
     *
     * @param retryTime 业务处理的重试次数
     * @return 重试模版
     */
    public RetryTemplate<R> setRetryTime(int retryTime) {
        if (retryTime <= 0) {
            throw new IllegalArgumentException("retryTime should bigger than 0");
        }

        this.retryTime = retryTime;
        return this;
    }

    /**
     * 重试的业务执行代码
     * 失败时请抛出一个异常
     */
    protected abstract Object doBiz() throws Exception;

    /**
     * 重试的主要业务逻辑
     *
     * @return 实际业务处理返回内容
     * @throws InterruptedException 中断异常
     */
    public R execute() throws InterruptedException {
        for (int i = 0; i < retryTime; i++) {
            try {
                return (R) doBiz();
            } catch (Exception e) {
                log.warn("业务执行出现异常，e: ", e);
                Thread.sleep(sleepMills);
            }
        }
        return null;
    }
}
