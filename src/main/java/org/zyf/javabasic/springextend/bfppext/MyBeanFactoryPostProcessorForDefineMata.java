//package org.zyf.javabasic.springextend.bfppext;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @author yanfengzhang
// * @description
// * @date 2023/5/17  23:12
// */
//@Component
//public class MyBeanFactoryPostProcessorForDefineMata implements BeanDefinitionRegistryPostProcessor {
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition("myTestMsgBean");
//        beanDefinition.setAttribute("customData", "This is custom data");
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        // 这里留空
//    }
//}
