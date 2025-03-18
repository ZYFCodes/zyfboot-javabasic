package org.zyf.javabasic.extendsdeal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * @program: zyfboot-javabasic
 * @description: 错误设计测试验证
 * @author: zhangyanfeng
 * @create: 2025-03-09 16:38
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticInitErrorTest {

    @Test(expected = NullPointerException.class)
    public void testStaticInitOrderNPE() throws Exception {
        // 通过反射直接加载子类，触发静态块
        Class.forName("org.zyf.javabasic.extendsdeal.ProdConfigLoader");

        // 验证静态字段访问
        String cert = ProdConfigLoader.prodCert;
        log.info("Loaded prod cert: {}", cert);
    }
}
