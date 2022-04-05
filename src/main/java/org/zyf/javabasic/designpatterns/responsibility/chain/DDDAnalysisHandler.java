package org.zyf.javabasic.designpatterns.responsibility.chain;

/**
 * @author yanfengzhang
 * @description 领域驱动设计报告自动检验处理
 * @date 2022/4/1  23:19
 */
public abstract class DDDAnalysisHandler {

    protected DDDAnalysisHandler next = null;

    /**
     * 校验DDD过程中输出产物是否符合要求
     *
     * @param dddProductReport     领域驱动设计产物报告提交内容
     * @param dddReportValidateRes 领域驱动设计产物报告提交内容校验结果反馈
     */
    public abstract void validateDDDHandler(DDDProductReport dddProductReport, DDDReportValidateRes dddReportValidateRes);

    /**
     * 建造者处理进行责任链连接
     */
    public static class Builder {
        private DDDAnalysisHandler header = null;
        private DDDAnalysisHandler tail = null;

        public Builder add(DDDAnalysisHandler dddAnalysisHandler) {
            if (this.header == null) {
                this.header = this.tail = dddAnalysisHandler;
            } else {
                tail.next = dddAnalysisHandler;
                tail = dddAnalysisHandler;
            }
            return this;
        }

        public DDDAnalysisHandler build() {
            return this.header;
        }
    }
}
