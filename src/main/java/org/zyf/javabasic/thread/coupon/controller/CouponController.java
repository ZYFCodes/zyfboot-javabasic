package org.zyf.javabasic.thread.coupon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zyf.javabasic.thread.coupon.CouponResult;
import org.zyf.javabasic.thread.coupon.service.CouponAggregatorService;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: Controller 示例
 * @author: zhangyanfeng
 * @create: 2025-11-16 15:19
 **/
@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    private final CouponAggregatorService aggregatorService;

    public CouponController(CouponAggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/available")
    public List<CouponResult> getCoupons() {
        return aggregatorService.fetchAvailableCoupons(200);
    }
}

