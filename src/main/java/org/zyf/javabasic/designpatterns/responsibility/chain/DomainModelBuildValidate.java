package org.zyf.javabasic.designpatterns.responsibility.chain;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 领域模型构建产物分析处理
 * @date 2022/4/2  23:32
 */
public class DomainModelBuildValidate extends DDDAnalysisHandler {
    /**
     * 领域模型构建产物分析
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
        DomainModelBuildProduct domainModelBuildProduct = dddProductReport.getDomainModelBuildProduct();
        validateDomainModelBuild(domainModelBuildProduct.getDomainAnalysisModel(), "领域模型构建产物分析:领域分析模型不符合要求，请按规定进行填写！", dddReportValidateRes.getDetailReasons());
        validateDomainModelBuild(domainModelBuildProduct.getDomainDesignModel(), "领域模型构建产物分析:领域设计模型不符合要求，请按规定进行填写！", dddReportValidateRes.getDetailReasons());
        validateDomainModelBuild(domainModelBuildProduct.getDomainImplementationModel(), "领域模型构建产物分析:领域实现模型不符合要求，请按规定进行填写！！", dddReportValidateRes.getDetailReasons());

        /*3.输出校验结果*/
        if (CollectionUtils.isNotEmpty(dddReportValidateRes.getDetailReasons())) {
            dddReportValidateRes.setLegal(false);
        }

        if (next != null) {
            next.validateDDDHandler(dddProductReport, dddReportValidateRes);
        }
    }

    /**
     * 校验领域模型
     *
     * @param model        领域模型
     * @param detailReason 分析原因
     */
    private void validateDomainModelBuild(String model, String detailReason, List<String> detailReasons) {
        if (StringUtils.isBlank(model)) {
            detailReasons.add(detailReason);
        }
    }
}
