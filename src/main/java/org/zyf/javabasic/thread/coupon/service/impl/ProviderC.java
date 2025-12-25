package org.zyf.javabasic.thread.coupon.service.impl;

import org.springframework.stereotype.Component;
import org.zyf.javabasic.thread.coupon.CouponResult;
import org.zyf.javabasic.thread.coupon.service.ThirdPartyCouponProvider;

/**
 * @program: zyfboot-javabasic
 * @description: C示例 Provider 实现（模拟三方接口）
 * @author: zhangyanfeng
 * @create: 2025-11-16 15:13
 **/
@Component
public class ProviderC implements ThirdPartyCouponProvider {
    @Override
    public String getName() { return "ProviderC"; }

    @Override
    public CouponResult doRequest() throws Exception {
        throw new RuntimeException("第三方接口异常");
    }

    @Override
    public CouponResult fallback() {
        return new CouponResult(getName(), "默认C优惠", true);
    }
}
