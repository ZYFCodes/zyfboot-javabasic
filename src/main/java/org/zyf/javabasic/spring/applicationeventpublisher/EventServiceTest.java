package org.zyf.javabasic.spring.applicationeventpublisher;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;

/**
 * @program: zyfboot-javabasic
 * @description: 测试类来验证事件是否被正确触发
 * @author: zhangyanfeng
 * @create: 2024-04-12 18:40
 **/
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Test
    public void testPublishCustomEvent() {
        eventService.publishCustomEvent("Hello, Custom Event!");
    }
}
