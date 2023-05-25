//package org.zyf.javabasic.springextend.bfppext;
//
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
///**
// * @author yanfengzhang
// * @description
// * @date 2023/5/17  23:15
// */
//public class MyBeanFactoryPostProcessorForDefineMataTest {
//    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("zyf_application_context.xml");
//        ConfigurableApplicationContext configurableContext = (ConfigurableApplicationContext) context;
//        BeanDefinition beanDefinition = configurableContext.getBeanFactory().getBeanDefinition("myTestMsgBean");
//        Object customData = beanDefinition.getAttribute("customData");
//        System.out.println("Custom Data: " + customData);
//    }
//}
