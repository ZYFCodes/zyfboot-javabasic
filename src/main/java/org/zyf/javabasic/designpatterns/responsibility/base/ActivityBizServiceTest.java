package org.zyf.javabasic.designpatterns.responsibility.base;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/30 23:05
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActivityBizServiceTest {

    @Autowired
    private ActivityBizService activityBizService;

    @Test
    public void testActivityBizService() {
        ActivityDto activityDto = ActivityDto.builder()
                .activityName("张彦峰活动有敏感词")
                .activityType(1)
                .status(1)
                .activityConfig("张彦峰活动配置中有敏感词")
                .activityPrice(BigDecimal.valueOf(120))
                .startTime("2022-03-30 12:00:00")
                .endTime("2022-03-30 16:00:00").build();
        log.info("=======对活动常规性校验处理结果开始=======");
        List<String> checkRes = activityBizService.check(activityDto);
        if (CollectionUtils.isEmpty(checkRes)) {
            log.info("校验处理结果完成：该活动符合要求！");
            return;
        }
        log.info("校验处理结果完成，该活动不符合要求的主要原因如下：");
        checkRes.forEach(res -> {
            log.info("{}", res);
        });
    }

}
