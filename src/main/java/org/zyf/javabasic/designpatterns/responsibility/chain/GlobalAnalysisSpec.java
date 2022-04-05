package org.zyf.javabasic.designpatterns.responsibility.chain;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 全局分析规格说明书
 * @date 2022/4/1  23:01
 */
@Data
@Builder
public class GlobalAnalysisSpec {
    /**
     * 价值需求
     * 分析：利益相关者 + 系统愿景 + 系统范围（当前状态、未来状态、业务目标）
     */
    private String valueNeeds;
    /**
     * 业务需求
     * 业务描述与子领域规划分析（核心域、支撑域、通用域）
     */
    private String businessNeeds;
    /**
     * 业务流程
     * 核心业务流程 + 主要业务流程
     */
    private String businessProcess;
    /**
     * 子领域分析
     * 子领域==业务场景+业务服务（编号、名称、描述、触发事件、基本流程、替代流程、验收标准）
     */
    private String subFieldAnalysis;
}
