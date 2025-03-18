package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import static org.junit.Assert.assertNull;

/**
 * @program: zyfboot-javabasic
 * @description: 微信支付初始化失败时应返回 null
 * @author: zhangyanfeng
 * @create: 2025-03-09 15:37
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FactoryConstructorTest {
    @Test
    public void testWechatPaymentFactoryGracefulFail() {
        WechatPaymentNew instance = WechatPaymentNew.createInstance();
        assertNull("微信支付初始化失败时应返回 null", instance);
    }
}
