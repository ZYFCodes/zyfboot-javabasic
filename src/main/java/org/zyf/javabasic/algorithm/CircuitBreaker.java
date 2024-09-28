package org.zyf.javabasic.algorithm;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @program: zyfboot-javabasic
 * @description: 实现一个熔断器公共组件接入其他服务？
 * @author: zhangyanfeng
 * @create: 2024-09-11 18:50
 **/

public class CircuitBreaker {
    private enum State {
        CLOSE, HALF_OPEN, OPEN
    }

    private State state = State.CLOSE;
    private final int failureThreshold;
    private final long resetTimeout; // in milliseconds
    private final AtomicInteger failureCount = new AtomicInteger(0);
    private LocalDateTime lastFailureTime;

    public CircuitBreaker(int failureThreshold, long resetTimeout) {
        this.failureThreshold = failureThreshold;
        this.resetTimeout = resetTimeout;
    }

    public <T> T execute(Supplier<T> businessLogic, Supplier<T> fallback) {
        synchronized (this) {
            if (state == State.OPEN) {
                if (canReset()) {
                    state = State.HALF_OPEN;
                } else {
                    System.out.println("熔断器打开，拒绝请求！");
                    return fallback.get();
                }
            }
        }

        try {
            T result = businessLogic.get();
            synchronized (this) {
                reset();
            }
            return result;
        } catch (Exception e) {
            synchronized (this) {
                failureCount.incrementAndGet();
                lastFailureTime = LocalDateTime.now();
                if (failureCount.get() >= failureThreshold) {
                    state = State.OPEN;
                    System.out.println("熔断器打开！");
                    return fallback.get();
                }
            }
            throw e; // rethrow the exception if it doesn't meet the threshold
        }
    }

    public boolean callService(RemoteService service) {
        synchronized (this) {
            if (state == State.OPEN) {
                if (canReset()) {
                    state = State.HALF_OPEN;
                } else {
                    System.out.println("熔断器打开，拒绝请求！");
                    return false;
                }
            }
        }

        boolean success = service.call();
        synchronized (this) {
            if (success) {
                reset();
                return true;
            } else {
                failureCount.incrementAndGet();
                lastFailureTime = LocalDateTime.now();
                if (failureCount.get() >= failureThreshold) {
                    state = State.OPEN;
                    System.out.println("熔断器打开！");
                    return false;
                }
            }
        }
        return success;
    }

    private synchronized void reset() {
        state = State.CLOSE;
        failureCount.set(0);
        lastFailureTime = null;
        System.out.println("熔断器重置为CLOSE状态！");
    }

    private synchronized boolean canReset() {
        if (lastFailureTime == null) {
            return false;
        }
        long timeSinceLastFailure = ChronoUnit.MILLIS.between(lastFailureTime, LocalDateTime.now());
        return timeSinceLastFailure >= resetTimeout;
    }

    public static class RemoteService {
        public boolean call() {
            // 模拟服务调用
            return Math.random() > 0.5;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 失败阈值为3，重置时间为5s
        CircuitBreaker breaker = new CircuitBreaker(3, 5000);
        RemoteService service = new RemoteService();

        // 模拟多次调用服务
        for (int i = 0; i < 10; i++) {
            System.out.println("第" + (i + 1) + "次请求：");
            breaker.callService(service);
            // 每次调用间隔1s
            Thread.sleep(1000);
        }
    }
}
