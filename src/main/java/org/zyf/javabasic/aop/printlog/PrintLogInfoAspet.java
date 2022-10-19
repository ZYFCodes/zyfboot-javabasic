package org.zyf.javabasic.aop.printlog;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.zyf.javabasic.config.SwitchCommonConfig;
import org.zyf.javabasic.servicegovernance.enums.HostEnv;
import org.zyf.javabasic.servicegovernance.util.ProcessInfoUtil;

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

    StopWatch watch = new StopWatch();

    /**
     * 方法以 @PrintLogInfo 注解作为切面入口
     */
    @Pointcut("@annotation(org.zyf.javabasic.aop.printlog.PrintLogInfo)")
    public void printBasicLog() {

    }

    /**
     * 方法入参前打印对应的入参信息内容
     *
     * @param joinPoint 切入点信息
     * @throws Throwable 异常信息
     */
    @Before("printBasicLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        /*1.判断是否需要开启打印日志功能*/
        if (!isPrintLog()) {
            return;
        }

        /*2.从方法上提取当前配置的注解信息*/
        PrintLogInfo printLogInfo = targetMethod.getAnnotation(PrintLogInfo.class);
        String description = printLogInfo.description();
        if (StringUtils.isBlank(description)) {
            description = "未表明归属";
        }

        /*3.打印相关入参参数*/
        StringBuilder methodInfo = new StringBuilder();
        methodInfo.append(methodSignature.getDeclaringTypeName()).append(".").append(targetMethod.getName());
        watch.start(methodInfo.toString());
        StringBuilder logRequestInfo = new StringBuilder();
        logRequestInfo.append("\n").append("请求方法：").append(methodInfo).append("\n");
        logRequestInfo.append("方法日志描述信息:").append(description).append("\n");
        logRequestInfo.append("入参信息：").append(JSON.toJSONString(joinPoint.getArgs()));
        log.info(logRequestInfo.toString());
    }

    /**
     * 方法返回之前执行，打印才返回值以及方法消耗时间
     *
     * @param response 返回值
     */
    @AfterReturning(returning = "response", pointcut = "printBasicLog()")
    public void doAfterRunning(Object response) {
        /*1.判断是否需要开启打印日志功能*/
        if (!isPrintLog()) {
            return;
        }

        /*2.从方法上提取当前配置的注解信息*/
        StringBuilder logResponseInfo = new StringBuilder();
        logResponseInfo.append("\n").append("出参信息:").append(JSON.toJSONString(response)).append("\n");
        logResponseInfo.append("本次方法执行时间耗时:").append(watch.getTotalTimeMillis()).append("ms");
        log.info(logResponseInfo.toString());
    }

    /**
     * 日志打印生效环境和机器提取提取，用于测试指定环境和机器来定位问题
     *
     * @return false-默认不进行打印
     */
    private boolean isPrintLog() {
        /*1.全部全部统一生效或失效开关*/
        if (SwitchCommonConfig.openPrintLog()) {
            return true;
        }

        /*2.按生效条件分析是否打印*/
        HostEnv hostEnv = ProcessInfoUtil.getHostEnv();
        String localIpV4 = ProcessInfoUtil.getLocalIpV4();

        /*2.1没有配置默认不打印相关数据*/
        if (Objects.isNull(hostEnv) && StringUtils.isBlank(localIpV4)) {
            return false;
        }

        /*2.2如果配置了指定机器打印的话，优先对机器执行打印*/
        if (SwitchCommonConfig.getIpsForPrintLog().contains(localIpV4)) {
            return true;
        }

        /*2.3当机器没有生效的时候，看生效环境*/
        if (SwitchCommonConfig.getEnvsForPrintLog().contains(hostEnv)) {
            return true;
        }
        return false;
    }
}
