package org.zyf.javabasic.thread.coupon.service;

import org.springframework.stereotype.Service;
import org.zyf.javabasic.thread.coupon.CouponResult;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 聚合服务层（核心实现）
 * @author: zhangyanfeng
 * @create: 2025-11-16 15:16
 **/
@Service
public class CouponAggregatorService {

    private final List<ThirdPartyCouponProvider> providers;
    private final ExecutorService executor;

    public CouponAggregatorService(List<ThirdPartyCouponProvider> providers) {
        this.providers = providers;
        this.executor = Executors.newFixedThreadPool(
                Math.min(providers.size(), Runtime.getRuntime().availableProcessors() * 2)
        );
    }

    public List<CouponResult> fetchAvailableCoupons(long timeoutMillis) {
        try {
            long start = System.currentTimeMillis();

            // 并发执行所有任务，并限制整体超时
            List<Future<CouponResult>> futures = executor.invokeAll(providers, timeoutMillis, TimeUnit.MILLISECONDS);

            List<CouponResult> results = futures.stream()
                    .filter(Future::isDone)
                    .map(f -> {
                        try {
                            return f.get();
                        } catch (Exception e) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            long cost = System.currentTimeMillis() - start;
            System.out.println("调用耗时: " + cost + "ms, 返回结果数量: " + results.size());
            return results;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return Collections.emptyList();
        }
    }
}

