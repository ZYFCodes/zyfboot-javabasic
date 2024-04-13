package org.zyf.javabasic.spring.applicationeventpublisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @program: zyfboot-javabasic
 * @description: 服务类 EventService
 * @author: zhangyanfeng
 * @create: 2024-04-12 18:39
 **/
@Service
public class EventService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    public void publishCustomEvent(String message) {
        CustomEvent customEvent = new CustomEvent(this, message);
        eventPublisher.publishEvent(customEvent);
    }
}
