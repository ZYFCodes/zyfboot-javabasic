package org.zyf.javabasic.aop.basic.printlog;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/13  12:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ZyfInfoServiceTest {
    @Autowired
    private ZyfInfoService zyfInfoService;

    @Test
    public void test() {
        zyfInfoService.getZyfInfo(ZyfInfoRequest.builder().needBlogInfo(true).needBasicInfo(true).build());
    }
}
