package org.zyf.javabasic.designpatterns.template.biz;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/12  16:56
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BatchDeployStrategyCommand extends BaseCommand {
    /**
     * 发布操作
     */
    private final static int OPERATION_DEPLOY = 1;
    /**
     * 撤销操作
     */
    private final static int OPERATION_UNDEPLOY = 2;

    private Integer processBizType;
    private Integer processDataType;
    private List<Long> strategyIdList;
    /**
     * 操作：1-发布策略 2-撤销策略
     */
    private Integer operation;

    public boolean isDeploy() {
        if (operation == null) {
            return false;
        }

        return operation == OPERATION_DEPLOY;
    }

    public boolean isUndeploy() {
        if (operation == null) {
            return false;
        }

        return operation == OPERATION_UNDEPLOY;
    }
}

