package org.zyf.javabasic.test.aspect;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import javax.annotation.Resource;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/29  15:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductStoreAspectTest {

    @Resource
    private AspectService aspectService;

    @Test
    public void test1(){
        System.out.println(aspectService.getBoolean());
    }
}
