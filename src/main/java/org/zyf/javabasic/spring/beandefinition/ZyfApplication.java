package org.zyf.javabasic.spring.beandefinition;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: zyfboot-javabasic
 * @description: ceshi
 * @author: zhangyanfeng
 * @create: 2024-05-02 11:09
 **/
public class ZyfApplication {
    public static void main(String[] args) {
        System.out.println("===================通过模式注解 + 组件扫描方式===================");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ZyfAppConfig.class);
        ZyfComponent myComponent = context.getBean(ZyfComponent.class);
        myComponent.sayHello();

        System.out.println("===================通过配置类 + @Bean 注解方式===================");
        ZyfService zyfService = context.getBean(ZyfService.class);
        zyfService.performAction();

        System.out.println("===================通过XML配置文件方式===================");
        ClassPathXmlApplicationContext contextXml = new ClassPathXmlApplicationContext("zyf_application_context.xml");
        ZyfService myServiceXml = context.getBean("zyfService", ZyfService.class);
        myServiceXml.performAction();

        System.out.println("===================通过编程方式===================");
        // 创建一个 BeanDefinition，并指定类名
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(ZyfService.class);

        // 将 BeanDefinition 注册到 Spring 容器中
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
        beanFactory.registerBeanDefinition("zyfService", beanDefinition);

        // 获取 Bean，并调用方法
        ZyfService myService = context.getBean("zyfService", ZyfService.class);
        myService.performAction();

        context.close();
    }
}
