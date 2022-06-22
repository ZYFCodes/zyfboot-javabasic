package org.zyf.javabasic.aop.basic;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/8  21:43
 */
@Component
@Aspect
@Slf4j
public class VoidBooleanAspect {
    /**
     * 切入点1：ps针对B端和C端缓存处理
     */
    @Pointcut("execution(* org.zyf.javabasic.aop.basic..*.*(..))")
    public void productStoreForB() {
    }

    @Around("productStoreForB()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        /*获取其基本的Signature信息*/
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        //System.out.println(JSON.toJSONString(methodSignature));
        Method targetMethod = methodSignature.getMethod();
        //System.out.println(methodSignature.getReturnType().toString());
        //Class result = methodSignature.getReturnType(); //返回的具体类
        //AnnotatedType annotatedType = targetMethod.getAnnotatedReturnType();
        System.out.println("===================================================");
        System.out.println(methodSignature.getReturnType());
        System.out.println(targetMethod.getAnnotatedReturnType().getType().getTypeName());
        System.out.println("===================================================");

        //Object o = methodSignature.getMethod().getReturnType();
        System.out.println("kkkkkkkkk"+JSON.toJSON(methodSignature.getMethod().getReturnType()));

        //annotatedType.getType().getTypeName();
       // System.out.println(result);


        /*将当前基本信息进行组装处理*/
        String className = methodSignature.getDeclaringTypeName();
        String methodName = targetMethod.getName();
        String mockStoreInfo = className+":"+methodName;

        if(mockStoreInfo.equals("org.zyf.javabasic.aop.basic.VoidBooleanService:getBoolean")
        ||mockStoreInfo.equals("org.zyf.javabasic.aop.basic.VoidBooleanService:getBoolean2")){
            return true;
        }

        if(mockStoreInfo.equals("org.zyf.javabasic.aop.basic.VoidBooleanService:getVoid")){
            return true;
        }

        if(mockStoreInfo.equals("org.zyf.javabasic.aop.basic.VoidBooleanService:getInt1")
                ||mockStoreInfo.equals("org.zyf.javabasic.aop.basic.VoidBooleanService:getInt2")){
            return 0;
        }

        return point.proceed();
    }
}
