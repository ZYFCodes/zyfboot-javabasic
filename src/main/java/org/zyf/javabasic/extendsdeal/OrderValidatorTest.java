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
 * @description: 错误验证测试
 * @author: zhangyanfeng
 * @create: 2025-03-09 14:34
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderValidatorTest {

    @Autowired
    private CouponOrderValidator validator;

    @Test
    public void testValidate_ByCouponValidator_shouldSkipRiskCheck() {
        validator.validate();
        // ✅ 日志中将看不到“用户风控校验”字样，说明关键逻辑被绕过
    }
}
