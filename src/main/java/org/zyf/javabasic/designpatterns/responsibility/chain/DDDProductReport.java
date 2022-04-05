package org.zyf.javabasic.designpatterns.responsibility.chain;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 领域驱动设计产物报告提交内容
 * @date 2022/4/1  22:56
 */
@Data
@Builder
public class DDDProductReport {
    /**
     * 全局分析规格说明书
     */
    private GlobalAnalysisSpec globalAnalysisSpec;
    /**
     * 架构映射战略设计方案
     */
    private ArchitectureMappingScheme architectureMappingScheme;
    /**
     * 领域模型构建产物
     */
    private DomainModelBuildProduct domainModelBuildProduct;
}
