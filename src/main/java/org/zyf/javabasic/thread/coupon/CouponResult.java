package org.zyf.javabasic.thread.coupon;

/**
 * @program: zyfboot-javabasic
 * @description: 定义统一返回结果结构
 * @author: zhangyanfeng
 * @create: 2025-11-16 15:07
 **/
public class CouponResult {
    private final String provider;
    private final String content;
    private final boolean fromFallback;

    public CouponResult(String provider, String content, boolean fromFallback) {
        this.provider = provider;
        this.content = content;
        this.fromFallback = fromFallback;
    }

    public String getProvider() { return provider; }
    public String getContent() { return content; }
    public boolean isFromFallback() { return fromFallback; }

    @Override
    public String toString() {
        return String.format("[%s] %s%s",
                provider,
                content,
                fromFallback ? " (fallback)" : ""
        );
    }
}

