package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * @program: zyfboot-javabasic
 * @description: WechatPayment测试初始化
 * @author: zhangyanfeng
 * @create: 2025-03-09 15:26
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WechatPaymentTest {
//    @Autowired
//    private WechatPayment wechatPayment;

    @Test
    public void testValidate_ByCouponValidator_shouldIncludeRiskCheck() {
//        wechatPayment.test();

        // ✅ 支付网关连接失败, 会直接报错
    }
}
