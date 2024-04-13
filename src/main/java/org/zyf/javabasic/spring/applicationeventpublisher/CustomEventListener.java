package org.zyf.javabasic.spring.applicationeventpublisher;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @program: zyfboot-javabasic
 * @description: 事件监听器 CustomEventListener
 * @author: zhangyanfeng
 * @create: 2024-04-12 18:40
 **/
@Component
public class CustomEventListener implements ApplicationListener<CustomEvent> {

    @Override
    public void onApplicationEvent(CustomEvent event) {
        String message = event.getMessage();
        System.out.println("Received custom event with message: " + message);
    }
}
