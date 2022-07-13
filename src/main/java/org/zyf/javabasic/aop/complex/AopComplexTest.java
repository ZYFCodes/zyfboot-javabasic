package org.zyf.javabasic.aop.complex;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.aop.complex.service.verify.VerifyAopService;

import javax.annotation.Resource;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/8  17:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AopComplexTest {

    @Resource
    private VerifyAopService verifyAopService;

    @Test
    public void test1() {
//        System.out.println("verifyAopService.queryLimitTimeActivityDetail");
//        verifyAopService.queryLimitTimeActivityDetail((long) 34);
//
//        System.out.println("verifyAopService.addOrUpdatePriceCutActivityDetail");
//        verifyAopService.addOrUpdatePriceCutActivityDetail(null);

        System.out.println("verifyAopService.addOrUpdatePriceCutActivityDetail");
        verifyAopService.deletePriceCutActivityDetail(null);
        System.out.println("fff" + verifyAopService.deletePriceCutActivityDetail(null));
    }
}
