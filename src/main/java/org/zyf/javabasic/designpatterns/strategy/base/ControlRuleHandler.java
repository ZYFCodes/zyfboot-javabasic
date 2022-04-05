package org.zyf.javabasic.designpatterns.strategy.base;

import java.util.Collection;

/**
 * @author yanfengzhang
 * @description 规则控制基本实现接口定义
 * @date 2022/3/3  23:58
 */
public interface ControlRuleHandler {
    /**
     * 根据当前配置类目信息进行分析处理
     *
     * @param cagegoryDealInfo 需要的处理的类目信息
     * @return 最后可售卖的类目信息
     * @throws Exception 业务异常
     */
    Collection<Long> getCagegoryIds(CagegoryDealInfo cagegoryDealInfo) throws Exception;
}
