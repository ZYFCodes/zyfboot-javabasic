package org.zyf.javabasic.thread.coupon.service.impl;

import org.springframework.stereotype.Component;
import org.zyf.javabasic.thread.coupon.CouponResult;
import org.zyf.javabasic.thread.coupon.service.ThirdPartyCouponProvider;

/**
 * @program: zyfboot-javabasic
 * @description: B示例 Provider 实现（模拟三方接口）
 * @author: zhangyanfeng
 * @create: 2025-11-16 15:12
 **/
@Component
public class ProviderB implements ThirdPartyCouponProvider {
    @Override
    public String getName() { return "ProviderB"; }

    @Override
    public CouponResult doRequest() throws Exception {
        Thread.sleep(300); // 模拟慢响应（>200ms）
        return new CouponResult(getName(), "B权益20积分", false);
    }

    @Override
    public CouponResult fallback() {
        return new CouponResult(getName(), "默认B权益", true);
    }
}
