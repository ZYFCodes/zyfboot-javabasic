package org.zyf.javabasic.spring.beandefinition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @program: zyfboot-javabasic
 * @description: 定义一个配置类，并在其中启用组件扫描。
 * @author: zhangyanfeng
 * @create: 2024-05-02 11:08
 **/
@Configuration
@ComponentScan("org.zyf.javabasic.spring.beandefinition")
public class ZyfAppConfig {
    // 配置类的其他内容
    @Bean
    public ZyfService zyfService() {
        return new ZyfService();
    }
}
