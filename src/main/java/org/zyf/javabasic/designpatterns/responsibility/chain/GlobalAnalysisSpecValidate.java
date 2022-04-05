package org.zyf.javabasic.designpatterns.responsibility.chain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 全局分析规格说明书分析处理
 * @date 2022/4/2  23:13
 */
public class GlobalAnalysisSpecValidate extends DDDAnalysisHandler {
    /**
     * 全局分析规格说明书分析
     *
     * @param dddProductReport 领域驱动设计产物报告提交内容
     * @return 将对应分析输出到 领域驱动设计产物报告提交内容校验结果反馈 中
     */
    @Override
    public void validateDDDHandler(DDDProductReport dddProductReport, DDDReportValidateRes dddReportValidateRes) {
        /*1.如果上个节点的处理结果未通过则本流程不进行处理*/
        if (!dddReportValidateRes.isLegal()) {
            return;
        }

        /*2.校验报告相关完整性分析*/
        GlobalAnalysisSpec globalAnalysisSpec = dddProductReport.getGlobalAnalysisSpec();
        validateGlobalSpec(globalAnalysisSpec.getValueNeeds(), "全局分析规格说明书:价值需求分析不符合要求，请按规定进行填写！", dddReportValidateRes.getDetailReasons());
        validateGlobalSpec(globalAnalysisSpec.getBusinessNeeds(), "全局分析规格说明书:业务需求分析不符合要求，请按规定进行填写！", dddReportValidateRes.getDetailReasons());
        validateGlobalSpec(globalAnalysisSpec.getBusinessProcess(), "全局分析规格说明书:业务流程涵盖内容存在问题，请进行流程梳理！", dddReportValidateRes.getDetailReasons());
        validateGlobalSpec(globalAnalysisSpec.getSubFieldAnalysis(), "全局分析规格说明书:子领域分析不符合要求，请按规定进行填写！", dddReportValidateRes.getDetailReasons());

        /*3.输出校验结果*/
        if (CollectionUtils.isNotEmpty(dddReportValidateRes.getDetailReasons())) {
            dddReportValidateRes.setLegal(false);
        }
        if (next != null) {
            next.validateDDDHandler(dddProductReport, dddReportValidateRes);
        }
    }

    /**
     * 校验全局分析规格说明书分析
     *
     * @param analysisSpec 全局分析规格说明书内容
     * @param detailReason 分析原因
     */
    private void validateGlobalSpec(String analysisSpec, String detailReason, List<String> detailReasons) {
        if (StringUtils.isBlank(analysisSpec)) {
            detailReasons.add(detailReason);
        }
    }
}
