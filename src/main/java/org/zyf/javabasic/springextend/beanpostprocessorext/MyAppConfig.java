package org.zyf.javabasic.springextend.beanpostprocessorext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/16  23:58
 */
@Configuration
public class MyAppConfig {
    @Bean
    public MyBeanPostProcessor myBeanPostProcessor() {
        return new MyBeanPostProcessor();
    }
}
