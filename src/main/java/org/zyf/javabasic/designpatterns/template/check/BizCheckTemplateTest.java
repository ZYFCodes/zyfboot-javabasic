package org.zyf.javabasic.designpatterns.template.check;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

import java.util.Arrays;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/3/17  00:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class BizCheckTemplateTest {

    @Autowired
    private AppealService appealService;

    @Test
    public void testAppealService() throws Exception {
        List<String> appealItems = Arrays.asList("隔壁搬来的邻居真他妈的吵！", "小区的健身房器械就不能在多一些练腿的？");
        AppealMatters appealMatters = AppealMatters.builder()
                .code("shvhf2736gbbnvhbb")
                .declarant("zyf")
                .type(2)
                .appealItems(appealItems).build();

        log.info("测试新建");
        appealService.saveAppealMatters(appealMatters);

        log.info("测试更新");
        appealMatters.setId(7L);
        appealMatters.setType(2);
        appealService.saveAppealMatters(appealMatters);

        log.info("测试删除");
        appealService.deleteAppealMatters(7L);
    }
}
