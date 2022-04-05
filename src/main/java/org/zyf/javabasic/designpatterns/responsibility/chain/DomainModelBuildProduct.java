package org.zyf.javabasic.designpatterns.responsibility.chain;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 领域模型构建产物
 * @date 2022/4/1  23:11
 */
@Data
@Builder
public class DomainModelBuildProduct {
    /**
     * 领域分析模型：业务服务约束 + 领域模型概念图
     */
    private String domainAnalysisModel;
    /**
     * 领域设计模型：以聚合为核心的静态设计类图 + 由角色构造型组成的动态序列图 + 序列图脚本
     */
    private String domainDesignModel;
    /**
     * 领域实现模型：实现业务功能的产品代码（示例） + 验证业务功能的测试代码（示例）
     */
    private String domainImplementationModel;
}
