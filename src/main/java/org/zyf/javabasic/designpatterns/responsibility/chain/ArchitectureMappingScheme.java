package org.zyf.javabasic.designpatterns.responsibility.chain;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 架构映射战略设计方案
 * @date 2022/4/1  23:07
 */
@Data
@Builder
public class ArchitectureMappingScheme {
    /**
     * 系统上下文
     */
    private String systemContext;
    /**
     * 业务架构 ： 业务组件 + 业务架构视图
     */
    private String businessrchitecture;
    /**
     * 应用架构 : 业务组件 + 应用架构视图
     */
    private String applicationArchitecture;
    /**
     * 子领域架构 ： 核心域、支撑域、通用域
     */
    private String subFieldAnalysis;
}
