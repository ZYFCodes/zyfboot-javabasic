package org.zyf.javabasic.aop.complex.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.aop.complex.annotation.ZYFActivityDealer;
import org.zyf.javabasic.aop.complex.constants.ActivityBizConstants;
import org.zyf.javabasic.aop.complex.constants.ActivityBizMethod;
import org.zyf.javabasic.aop.complex.factory.ActivityServiceStrategyFactory;
import org.zyf.javabasic.aop.complex.service.activity.ActivityService;
import org.zyf.javabasic.common.ActivityBizException;

import java.lang.reflect.Method;

/**
 * @author yanfengzhang
 * @description 活动业务对应的切面处理类
 * @date 2020/10/30  23:06
 */
@Component
@Aspect
@Slf4j
public class ActivityBizAspect {

    @Pointcut("@annotation(org.zyf.javabasic.aop.complex.annotation.ZYFActivityDealer)")
    public void execute() {
    }

    @Before("execute()")
    public void printLog(JoinPoint point) {
        System.out.println("的地方v官方大肆宣传" + point.getArgs());
        log.info("hhhh{}", point.getArgs());
    }

    @Around("execute()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        log.info("AOP处理开始：");
        Signature signature = point.getSignature();
        log.info("signature={}", JSON.toJSONString(signature));
        MethodSignature methodSignature = (MethodSignature) signature;
        log.info("methodSignature={}", JSON.toJSONString(methodSignature));
        Method targetMethod = methodSignature.getMethod();
        ZYFActivityDealer zyfActivityDealer = targetMethod.getAnnotation(ZYFActivityDealer.class);
        if (null != zyfActivityDealer) {
            log.info("活动业务切面生效！");
            /*业务上存在对应注解，则需要进入活动对应的业务处理*/
            return handleActivityBizMethod(point, zyfActivityDealer);
        }
        log.info("AOP处理结束：");
        return point.proceed();
    }

    /**
     * @param point             指定不变，不可更改
     * @param zyfActivityDealer 对应的新建注解
     * @return 具体业务结果
     * @throws Throwable 相关异常
     * @description 处理对应的活动业务处理方法
     */
    private Object handleActivityBizMethod(ProceedingJoinPoint point, ZYFActivityDealer zyfActivityDealer) throws Throwable {
        log.info("AOP操作：zyfActivityDealer={}", zyfActivityDealer);
        /*1.获取当前指定的活动类型：降价活动、限时活动、买赠活动、首购优惠活动、自动续费活动、折上优惠活动*/
        String activityType = zyfActivityDealer.activityType();
        if (StringUtils.isBlank(activityType)) {
            throw new ActivityBizException("活动类型异常:获取活动类型activityType失败, 请检查指定是否正确!", ActivityBizConstants.ActivityBizCode.ANNO_EMPTY_BIZTYPE_ERR);
        }

        /*2.按照活动类型返回需要提供服务的活动类型*/
        ActivityService activityService = ActivityServiceStrategyFactory.getActivityBytype(activityType);
        if (null == activityService) {
            throw new ActivityBizException("活动类型异常: 获取handler失败，请联系管理员查看!", ActivityBizConstants.ActivityBizCode.NO_HANDLER_FOUND);
        }

        /*3.确定对应活动需要执行的业务方法*/
        ActivityBizMethod activityBizMethod = zyfActivityDealer.activityMethod();
        String handleMethodName = activityBizMethod.getMethodName();
        if (StringUtils.isBlank(handleMethodName)) {
            throw new ActivityBizException("活动业务执行方法异常: 获取对应活动业务方法失败，请联系管理员查看!", ActivityBizConstants.ActivityBizCode.HANDLER_NO_VALID_METHOD_ERR);
        }

        /*4.根据对应活动类型进行反射获取对应的执行的业务方法*/
        Method activityBizMethodForHandler = null;
        try {
            activityBizMethodForHandler = activityService.getClass().getMethod(handleMethodName, Object[].class);
        } catch (NoSuchMethodException e) {
            throw new ActivityBizException("活动业务执行方法异常: 获取handler指定method失败，请联系管理员查看!", ActivityBizConstants.ActivityBizCode.HANDLER_NO_VALID_METHOD_ERR);
        }

        /*5.按照指定活动和对应业务方法执行对应的方法*/
        try {
            Object[] args = point.getArgs();
            Object result = activityBizMethodForHandler.invoke(activityService, new Object[]{args});
            if (null == result || activityBizMethod.doBizMethodAfterHandler()) {
                return point.proceed();
            } else {
                return result;
            }
        } catch (Exception e) {
            log.error("处理活动AOP切面存在异常: ", e);
            throw new ActivityBizException("服务端异常:副本逻辑异常，请联系管理员查看!", ActivityBizConstants.ActivityBizCode.HANDLER_BIZ_ERR);
        }
    }
}
