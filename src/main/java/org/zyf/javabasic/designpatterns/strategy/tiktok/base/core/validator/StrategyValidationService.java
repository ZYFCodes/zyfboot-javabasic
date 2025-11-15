package org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.validator;

import org.zyf.javabasic.designpatterns.strategy.tiktok.base.core.dispatcher.StrategyDispatcher;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 通用策略验证服务接口
 * @author: zhangyanfeng
 * @create: 2025-11-02 19:00
 **/
public interface StrategyValidationService {
    void validateCoverage(StrategyDispatcher dispatcher, List<? extends Enum<?>[]> dimensions);
}
