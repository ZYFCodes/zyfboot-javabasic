package org.zyf.javabasic.extendsdeal;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * @program: zyfboot-javabasic
 * @description: StaticInitCorrectTest
 * @author: zhangyanfeng
 * @create: 2025-03-09 17:02
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StaticInitCorrectTest {
    @Test
    public void testConfigLoaderFactoryInitialization() {
//        ProdConfigLoader loader = new ProdConfigLoader();
//        Assertions.assertEquals("COMMON_OK", loader.getCommonConfig());
//        Assertions.assertEquals("CERT_FOR_COMMON_OK", loader.getProdCert());
//
//        log.info("配置加载成功，common={}, cert={}", loader.getCommonConfig(), loader.getProdCert());
    }
}
