//package org.zyf.javabasic.springextend.bfppext;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanDefinition;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.beans.factory.support.BeanDefinitionRegistry;
//import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
//import org.springframework.stereotype.Component;
//
///**
// * @author yanfengzhang
// * @description
// * @date 2023/5/17  23:36
// */
//@Component
//public class MyBeanFactoryPostProcessorForNewDefinRegi implements BeanDefinitionRegistryPostProcessor {
//
//    @Override
//    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
//        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(AdditionalBean.class).getBeanDefinition();
//        beanDefinitionRegistry.registerBeanDefinition("additionalBean", beanDefinition);
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        // 这里留空
//    }
//}
