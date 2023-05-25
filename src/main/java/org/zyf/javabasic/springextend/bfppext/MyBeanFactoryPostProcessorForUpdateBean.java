//package org.zyf.javabasic.springextend.bfppext;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.MutablePropertyValues;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @author yanfengzhang
// * @description
// * @date 2023/5/17  23:00
// */
//@Component
//public class MyBeanFactoryPostProcessorForUpdateBean implements BeanFactoryPostProcessor {
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("myTestMsgBean");
//        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
//        propertyValues.add("message", "Hello, World!");
//    }
//}
