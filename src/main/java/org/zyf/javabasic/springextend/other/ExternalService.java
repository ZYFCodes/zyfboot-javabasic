package org.zyf.javabasic.springextend.other;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @program: zyfboot-javabasic
 * @description: 示例：销毁资源
 * @author: zhangyanfeng
 * @create: 2025-03-10 08:45
 **/
@Service
public class ExternalService {

    private SomeService service;

    @PostConstruct
    public void initialize() {
        service = new SomeService();
        service.connect();
    }

    @PreDestroy
    public void cleanup() {
        if (service != null) {
            service.disconnect();
            System.out.println("外部服务连接已断开");
        }
    }
}
