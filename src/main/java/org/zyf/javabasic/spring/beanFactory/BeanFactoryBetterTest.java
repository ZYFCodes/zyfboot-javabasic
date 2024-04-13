package org.zyf.javabasic.spring.beanFactory;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * @program: zyfboot-javabasic
 * @description: 选择 BeanFactory 可能更为合适。
 * @author: zhangyanfeng
 * @create: 2024-04-13 12:59
 **/
@Log4j2
public class BeanFactoryBetterTest {
    public static void main(String[] args) {
        // 创建 BeanFactory
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));

        // 获取 Bean
        HelloWorld helloWorld1 = (HelloWorld) beanFactory.getBean("helloWorld");

        // 使用 Bean
        helloWorld1.sayHello();


        // 创建 ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 获取 Bean
        HelloWorld helloWorld2 = (HelloWorld) context.getBean("helloWorld");

        // 使用 Bean
        helloWorld2.sayHello();
    }
}
