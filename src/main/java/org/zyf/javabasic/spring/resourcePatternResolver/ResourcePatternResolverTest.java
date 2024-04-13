package org.zyf.javabasic.spring.resourcePatternResolver;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.io.IOException;

/**
 * @program: zyfboot-javabasic
 * @description: 简单的案例来验证 ResourcePatternResolver 的功能
 * @author: zhangyanfeng
 * @create: 2024-04-12 19:18
 **/
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ResourcePatternResolverTest {
    @Autowired
    private ResourcePatternResolver resourcePatternResolver;

    @Test
    public void testResourcePatternResolver() throws IOException {
        String locationPattern = "classpath:db/*.sql";
        Resource[] resources = resourcePatternResolver.getResources(locationPattern);
        for (Resource resource : resources) {
            System.out.println("Found resource: " + resource.getFilename());
        }
    }
}
