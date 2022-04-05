package org.zyf.javabasic.designpatterns.responsibility.chain;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 报告完整性分析处理
 * @date 2022/4/2  23:05
 */
public class ReportIntegrityValidate extends DDDAnalysisHandler {

    /**
     * 报告完整性分析
     *
     * @param dddProductReport 领域驱动设计产物报告提交内容
     * @return 将对应分析输出到 领域驱动设计产物报告提交内容校验结果反馈 中
     */
    @Override
    public void validateDDDHandler(DDDProductReport dddProductReport, DDDReportValidateRes dddReportValidateRes) {
        List<String> detailReasons = Lists.newArrayList();
        if (Objects.isNull(dddProductReport)) {
            detailReasons.add("在进行组织评审前请按要求提交DDD领域驱动设计产物报告！");
            dddReportValidateRes.setLegal(false);
            dddReportValidateRes.setDetailReasons(detailReasons);
        } else {
            /*1.校验报告相关完整性分析*/
            validateIntegrity(dddProductReport.getGlobalAnalysisSpec(), "DDD领域驱动设计产物报告中全局分析规格说明书未补充！", detailReasons);
            validateIntegrity(dddProductReport.getArchitectureMappingScheme(), "DDD领域驱动设计产物报告中架构映射战略设计方案未补充！", detailReasons);
            validateIntegrity(dddProductReport.getDomainModelBuildProduct(), "DDD领域驱动设计产物报告中领域模型构建产物未补充！", detailReasons);

            /*2.输出校验结果*/
            if (CollectionUtils.isNotEmpty(detailReasons)) {
                dddReportValidateRes.setLegal(false);
                dddReportValidateRes.setDetailReasons(detailReasons);
            }
        }


        if (next != null) {
            next.validateDDDHandler(dddProductReport, dddReportValidateRes);
        }
    }

    /**
     * 校验报告相关完整性分析
     *
     * @param dddProduct   DDD产物报告细项
     * @param detailReason 分析原因
     */
    private void validateIntegrity(Object dddProduct, String detailReason, List<String> detailReasons) {
        if (Objects.isNull(dddProduct)) {
            detailReasons.add(detailReason);
        }
    }
}
