package org.zyf.javabasic.test.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/21  16:11
 */
@Component
@Aspect
@Slf4j
public class ProductStoreAspect {
    /**
     * 切入点1：ps针对B端和C端缓存处理
     */
    @Pointcut("execution(* org.zyf.javabasic.test.aspec..*(..))")
    public void productStoreForB() {
    }

    @Around("productStoreForB()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        /*获取其基本的Signature信息*/
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        System.out.println(targetMethod);

        /*将当前基本信息进行组装处理*/
        String className = methodSignature.getDeclaringTypeName();
        String methodName = targetMethod.getName();
        String mockStoreInfo = className + ":" + methodName;
        System.out.println(mockStoreInfo);

        List<String> test= Lists.newArrayList();
        test.add("org.zyf.javabasic.test.aspect.AspectService:getBoolean");
        if (test.contains(mockStoreInfo)) {
            return new Object();
        }

        /*其他方式下按原方式返回你即可*/
        return point.proceed();
    }
}
