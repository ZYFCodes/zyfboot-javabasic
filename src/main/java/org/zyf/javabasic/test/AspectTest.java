package org.zyf.javabasic.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.test.aspect.AspectService;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/21  16:10
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan("org.zyf.javabasic.test.aspect")
public class AspectTest {
    @Autowired
    private AspectService aspectService;

    @Test
    public void testAspectService() {
        System.out.println(aspectService.getBoolean());
    }
}
