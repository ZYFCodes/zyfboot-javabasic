//package org.zyf.javabasic.springextend.beanpostprocessorext.aop;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.stereotype.Component;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
///**
// * @author yanfengzhang
// * @description
// * @date 2023/4/18  14:28
// */
//@Component
//public class MyAopBeanPostProcessor implements BeanPostProcessor {
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        Class<?>[] interfaces = bean.getClass().getInterfaces();
//        Object beanProxy = bean;
//        /*一般会有注解啊，切面等等判断*/
//        if (interfaces.length > 0) {
//            beanProxy = Proxy.newProxyInstance(this.getClass().getClassLoader(), interfaces, new AopHandler(bean));
//        }
//        return beanProxy;
//    }
//
//    class AopHandler implements InvocationHandler {
//        private Object target;
//
//        public AopHandler(Object target) {
//            this.target = target;
//        }
//
//        @Override
//        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("MyAopBeanPostProcessor Test: before method invoke");
//            Object invoke = method.invoke(target, args);
//            System.out.println("MyAopBeanPostProcessor Test: after method invoke");
//            return invoke;
//        }
//    }
//}
