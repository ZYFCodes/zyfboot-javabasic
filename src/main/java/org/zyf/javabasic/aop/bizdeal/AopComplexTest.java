package org.zyf.javabasic.aop.bizdeal;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.aop.bizdeal.entity.dto.PriceCutActivityDto;
import org.zyf.javabasic.aop.bizdeal.service.verify.VerifyAopService;

import javax.annotation.Resource;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/8  23:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class AopComplexTest {

    @Resource
    private VerifyAopService verifyAopService;

    @Test
    public void testPriceCutActivityDetail() {
        log.info("======测试降价活动删除AOP情况======");
        verifyAopService.deletePriceCutActivityDetail(null);

        log.info("======测试降价活动新增AOP情况======");
        verifyAopService.addOrUpdatePriceCutActivityDetail(PriceCutActivityDto.builder().build());
    }
}
