package org.zyf.javabasic.springextend.beanpostprocessorext.aop;

import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/18  23:26
 */
@Component
public class MyAopServiceImpl implements MyAopService {
    @Override
    public void myAopMethod() {
        System.out.println("MyAopBeanPostProcessormy Test: aop method impl......");
    }
}
