package org.zyf.javabasic.aop.redissync;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/19  23:48
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class SensitiveOperationServiceTest {

    @Autowired
    private SensitiveOperationService sensitiveOperationService;

    @Test
    public void testOperationRedisSync() {
        log.info("=====测试新建敏感词异步同步redis=====");
        sensitiveOperationService.createSensitive(SensitiveWord.builder()
                .sensitiveId(11L).sensitive("刘亦菲").kind(1).build(), "zhangyanfeng");

        log.info("=====测试删除敏感词异步同步redis=====");
        sensitiveOperationService.deleteSensitive(11L, "zhangyanfeng");
    }
}
