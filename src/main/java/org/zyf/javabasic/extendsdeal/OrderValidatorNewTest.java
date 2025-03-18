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
 * @description: 正确做法：模板方法模式，约束子类行为边界
 * @author: zhangyanfeng
 * @create: 2025-03-09 14:59
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderValidatorNewTest {
    @Autowired
    private CouponOrderNewValidator validator;

    @Test
    public void testValidate_ByCouponValidator_shouldIncludeRiskCheck() {
        validator.validate();

        // ✅ 日志应包含：“库存校验”、“用户风控校验”、“优惠券规则校验”
    }
}
