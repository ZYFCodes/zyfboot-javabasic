package org.zyf.javabasic.spring.applicationeventpublisher;

import org.springframework.context.ApplicationEvent;

/**
 * @program: zyfboot-javabasic
 * @description: 自定义事件
 * @author: zhangyanfeng
 * @create: 2024-04-12 18:38
 **/
public class CustomEvent extends ApplicationEvent {
    private String message;

    public CustomEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
