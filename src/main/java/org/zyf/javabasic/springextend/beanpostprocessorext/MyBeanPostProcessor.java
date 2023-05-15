//package org.zyf.javabasic.springextend.beanpostprocessorext;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//
///**
// * @author yanfengzhang
// * @description
// * @date 2023/4/16  23:53
// */
//public class MyBeanPostProcessor implements BeanPostProcessor {
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        // 在 Bean 初始化前的处理逻辑
//        if (bean instanceof MyBean) {
//            MyBean myBean = (MyBean) bean;
//            // 对 MyBean 的属性进行自定义注入
//            myBean.setMyProperty("自定义属性值");
//            return myBean;
//        }
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        // 在 Bean 初始化后的处理逻辑
//        return bean;
//    }
//}
