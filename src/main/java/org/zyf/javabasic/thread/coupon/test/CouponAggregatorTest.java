package org.zyf.javabasic.thread.coupon.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.thread.coupon.CouponResult;
import org.zyf.javabasic.thread.coupon.service.CouponAggregatorService;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 单元测试验证
 * @author: zhangyanfeng
 * @create: 2025-11-16 15:21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CouponAggregatorTest {

    @Autowired
    private CouponAggregatorService aggregatorService;

    @Test
    public void testCouponFetch() {
        List<CouponResult> results = aggregatorService.fetchAvailableCoupons(200);
        results.forEach(System.out::println);
    }
}

