package org.zyf.javabasic.thread.coupon.service;

import org.zyf.javabasic.thread.coupon.CouponResult;

import java.util.concurrent.Callable;

/**
 * @program: zyfboot-javabasic
 * @description: 定义统一接口规范（Provider 接口）
 * @author: zhangyanfeng
 * @create: 2025-11-16 15:08
 **/
public interface ThirdPartyCouponProvider extends Callable<CouponResult> {
    String getName();
    CouponResult doRequest() throws Exception;
    CouponResult fallback();

    @Override
    default CouponResult call() {
        try {
            return doRequest();
        } catch (Exception e) {
            return fallback();
        }
    }
}

