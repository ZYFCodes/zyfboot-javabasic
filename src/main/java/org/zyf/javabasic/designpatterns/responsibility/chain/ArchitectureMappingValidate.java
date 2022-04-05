package org.zyf.javabasic.designpatterns.responsibility.chain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 架构映射战略设计方案分析处理
 * @date 2022/4/2  23:21
 */
public class ArchitectureMappingValidate extends DDDAnalysisHandler {
    /**
     * 架构映射战略设计方案分析
     *
     * @param dddProductReport 领域驱动设计产物报告提交内容
     * @return 将对应分析输出到 领域驱动设计产物报告提交内容校验结果反馈 中
     */
    @Override
    public void validateDDDHandler(DDDProductReport dddProductReport, DDDReportValidateRes dddReportValidateRes) {
        /*1.如果上个节点的处理结果未通过则本流程不进行处理*/
        if (!dddReportValidateRes.isLegal()) {
            if (next != null) {
                next.validateDDDHandler(null, dddReportValidateRes);
            }
            return;
        }

        /*2.校验报告相关完整性分析*/
        ArchitectureMappingScheme architectureMappingScheme = dddProductReport.getArchitectureMappingScheme();
        validateArchitectureMapping(architectureMappingScheme.getSystemContext(), "架构映射战略设计方案:系统上下文分析不符合要求，请按规定进行填写！", dddReportValidateRes.getDetailReasons());
        validateArchitectureMapping(architectureMappingScheme.getBusinessrchitecture(), "架构映射战略设计方案:业务架构分析不符合要求，请按规定进行填写！", dddReportValidateRes.getDetailReasons());
        validateArchitectureMapping(architectureMappingScheme.getApplicationArchitecture(), "架构映射战略设计方案:应用架构分析不符合要求，请按规定进行填写！！", dddReportValidateRes.getDetailReasons());
        validateArchitectureMapping(architectureMappingScheme.getSubFieldAnalysis(), "架构映射战略设计方案:子领域架构分析不符合要求，请按规定进行填写！", dddReportValidateRes.getDetailReasons());

        /*3.输出校验结果*/
        if (CollectionUtils.isNotEmpty(dddReportValidateRes.getDetailReasons())) {
            dddReportValidateRes.setLegal(false);
        }

        if (next != null) {
            next.validateDDDHandler(dddProductReport, dddReportValidateRes);
        }
    }

    /**
     * 校验架构映射
     *
     * @param analysisSpec 架构映射内容
     * @param detailReason 分析原因
     */
    private void validateArchitectureMapping(String analysisSpec, String detailReason, List<String> detailReasons) {
        if (StringUtils.isBlank(analysisSpec)) {
            detailReasons.add(detailReason);
        }
    }
}
