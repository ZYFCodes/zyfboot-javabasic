package org.zyf.javabasic.thread.coupon.service.impl;

/**
 * @program: zyfboot-javabasic
 * @description: A示例 Provider 实现（模拟三方接口）
 * @author: zhangyanfeng
 * @create: 2025-11-16 15:11
 **/
import org.springframework.stereotype.Component;
import org.zyf.javabasic.thread.coupon.CouponResult;
import org.zyf.javabasic.thread.coupon.service.ThirdPartyCouponProvider;

@Component
public class ProviderA implements ThirdPartyCouponProvider {
    @Override
    public String getName() { return "ProviderA"; }

    @Override
    public CouponResult doRequest() throws Exception {
        Thread.sleep(100); // 模拟正常响应
        return new CouponResult(getName(), "A红包10元", false);
    }

    @Override
    public CouponResult fallback() {
        return new CouponResult(getName(), "默认A红包", true);
    }
}

