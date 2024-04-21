package org.zyf.javabasic.dispatcher.core.model;

import lombok.Data;

/**
 * @program: zyfboot-javabasic
 * @description: 表示数据分发的策略
 * @author: zhangyanfeng
 * @create: 2024-04-21 17:11
 **/
@Data
public class DistributionStrategy {
    // 分发方式，例如邮件、短信、推送等
    private String distributionMethod;

    // 分发优先级
    private int priority;

    // 是否允许重复分发
    private boolean allowDuplicate;

    /**
     * 构造一个 DistributionStrategy 对象。
     *
     * @param distributionMethod 分发方式
     * @param priority           分发优先级
     * @param allowDuplicate     是否允许重复分发
     */
    public DistributionStrategy(String distributionMethod, int priority, boolean allowDuplicate) {
        this.distributionMethod = distributionMethod;
        this.priority = priority;
        this.allowDuplicate = allowDuplicate;
    }
}
