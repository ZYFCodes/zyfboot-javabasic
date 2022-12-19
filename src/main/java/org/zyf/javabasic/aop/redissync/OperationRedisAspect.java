package org.zyf.javabasic.aop.redissync;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;

import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 异步同步redis同步切面操作
 * @date 2022/7/13  23:23
 */
@Component
@Aspect
@Slf4j
public class OperationRedisAspect {

    @Pointcut("@annotation(org.zyf.javabasic.aop.redissync.OperationRedisSync) && @annotation(operationRedisSync)")
    private void operationRedisSyncDeal(OperationRedisSync operationRedisSync) {

    }

    @AfterReturning(pointcut = "operationRedisSyncDeal(operationRedisSync)", argNames = "joinPoint, operationRedisSync")
    public void operationRedisSyncDealer(JoinPoint joinPoint, OperationRedisSync operationRedisSync) {
        /*1.如果要求指明的异步操作没有处理的话直接返回*/
        int dealInfoType = operationRedisSync.dealInfoType();
        int dealBizType = operationRedisSync.dealBizType();
        if (!OperationRedisSyncType.isOperationRedisSyncType(dealInfoType) || !OperationRedisSyncBiz.isOperationRedisSyncBiz(dealBizType)) {
            return;
        }

        /*2.需要异步处理的信息名称没有指定的不进行相关业务操作*/
        int index = 0;
        if (dealInfoType == OperationRedisSyncType.DELETE.getType()) {
            index = OperationRedisAspectUtils.fetchParamIndex((CodeSignature) joinPoint.getSignature(), operationRedisSync.deleteId());
        } else {
            index = OperationRedisAspectUtils.fetchParamIndex((CodeSignature) joinPoint.getSignature(), operationRedisSync.dealInfo());
        }
        if (index < 0) {
            return;
        }

        /*3.根据dealBizType进行数据适配转换到业务侧进行实际的内容处理*/
        bizOperationRedisSync(dealBizType, dealInfoType, joinPoint.getArgs()[index]);
    }

    /**
     * 只是模拟：根据业务处理内容进行数据适配转换到业务侧进行实际的内容处理
     *
     * @param dealBizType  具体处理的业务
     * @param dealInfoType 具体异步操作的内容
     * @param bizDealInfo  具体业务信息详细
     */
    private void bizOperationRedisSync(int dealBizType, int dealInfoType, Object bizDealInfo) {
        if (Objects.isNull(bizDealInfo)) {
            return;
        }

        /*以下只做基本的模拟操作，实际要求按业务做相关的适配*/
        if (dealBizType == OperationRedisSyncBiz.SENSITIVE.getType() && dealInfoType == OperationRedisSyncType.CREATE.getType()) {
            /*如果是对应敏感词业务的新增逻辑，调用命敏感词异步同步redis相关操作*/
            SensitiveWord sensitiveWord = (SensitiveWord) bizDealInfo;
            log.info("敏感词业务的新增逻辑-调用命敏感词异步同步redis相关操作开始");
            log.info("敏感词业务的新增异步同步redis逻辑：sensitiveWord={},key-{},value:{}",
                    sensitiveWord,
                    sensitiveWord.getSensitiveId() + "-" + sensitiveWord.getSensitive() + "-" + sensitiveWord.getKind()
                    , sensitiveWord);
            log.info("敏感词业务的新增逻辑-调用命敏感词异步同步redis相关操作完成");
        }

        if (dealBizType == OperationRedisSyncBiz.SENSITIVE.getType() && dealInfoType == OperationRedisSyncType.DELETE.getType()) {
            /*如果是对应敏感词业务的新增逻辑，调用命敏感词异步同步redis相关操作*/
            Long sensitiveWordId = (Long) bizDealInfo;
            log.info("敏感词业务的删除逻辑-调用命敏感词异步同步redis相关操作开始");
            log.info("敏感词业务的删除异步同步redis逻辑：sensitiveWordId={}", sensitiveWordId);
            log.info("敏感词业务的删除逻辑-调用命敏感词异步同步redis相关操作完成");
        }
    }
}
