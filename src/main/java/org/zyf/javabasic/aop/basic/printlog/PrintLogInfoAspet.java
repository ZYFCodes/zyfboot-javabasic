package org.zyf.javabasic.aop.basic.printlog;

import com.sankuai.inf.octo.mns.model.HostEnv;
import com.sankuai.inf.octo.mns.util.ProcessInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.config.SwitchCommonConfig;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/15  23:02
 */
@Slf4j
@Aspect
@Component
public class PrintLogInfoAspet {

    /**
     * 方法以 @PrintLogInfo 注解作为切面入口
     */
    @Pointcut("@annotation(org.zyf.javabasic.aop.basic.printlog.PrintLogInfo)")
    public void printBasicLog() {

    }

    /**
     * 方法入参前打印对应的入参信息内容
     * @param joinPoint 切入点信息
     * @throws Throwable 异常信息
     */
    @Before("printBasicLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        /*1.判断是否需要开启打印日志功能*/
        if(!isPrintLog()){
            return;
        }

        /*2.从方法上提取当前配置的注解信息*/
        PrintLogInfo printLogInfo = targetMethod.getAnnotation(PrintLogInfo.class);



    }

    /**
     * 日志打印生效环境和机器提取提取，用于测试指定环境和机器来定位问题
     * @return false-默认不进行打印
     */
    private boolean isPrintLog(){
        HostEnv hostEnv=ProcessInfoUtil.getHostEnv();
        String localIpV4 = ProcessInfoUtil.getLocalIpV4();

        /*1.没有配置默认不打印相关数据*/
        if(Objects.isNull(hostEnv)&& StringUtils.isBlank(localIpV4)){
            return false;
        }

        /*2.如果配置了指定机器打印的话，优先对机器执行打印*/
        if(SwitchCommonConfig.getIpsForPrintLog().contains(localIpV4)){
            return true;
        }

        /*3.当机器没有生效的时候，看生效环境*/
        if(SwitchCommonConfig.getEnvsForPrintLog().contains(hostEnv)){
            return true;
        }

        return false;
    }


    public String getAspectMethodLogDescJP(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        return getAspectMethodLogDesc(targetName, methodName, arguments);
    }

    public String getAspectMethodLogDescPJ(ProceedingJoinPoint proceedingJoinPoint) throws Exception {
        String targetName = proceedingJoinPoint.getTarget().getClass().getName();
        String methodName = proceedingJoinPoint.getSignature().getName();
        Object[] arguments = proceedingJoinPoint.getArgs();
        return getAspectMethodLogDesc(targetName, methodName, arguments);
    }

    public String getAspectMethodLogDesc(String targetName, String methodName, Object[] arguments) throws Exception {
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(PrintLogInfo.class).description());
                    break;
                }
            }
        }
        return description.toString();
    }
}
