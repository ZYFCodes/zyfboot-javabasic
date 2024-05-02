package org.zyf.javabasic.spring.beandefinition;

import org.springframework.stereotype.Component;

/**
 * @program: zyfboot-javabasic
 * @description: 定义一个简单的组件类，并使用 @Component 注解进行标记
 * @author: zhangyanfeng
 * @create: 2024-05-02 11:03
 **/
@Component
public class ZyfComponent {
    public void sayHello() {
        System.out.println("Hello from ZyfComponent!");
    }
}
