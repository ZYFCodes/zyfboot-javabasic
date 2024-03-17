package org.zyf.javabasic.workflowpipline.core;

import org.apache.commons.collections.CollectionUtils;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;

import java.util.Objects;

/**
 * @program: zyfboot-javabasic
 * @description: 基本执行任务基类
 * @author: zhangyanfeng
 * @create: 2024-02-15 10:20
 **/
public abstract class BaseExecutorWorker implements ExecutorWorker {
    @Override
    public WorkflowExecutionContext work(WorkflowExecutionContext context, String scenarioIdentifier) {
        return work(context);
    }

    @Override
    public boolean checkParams(WorkflowExecutionContext ctx) {
        WorkerInfo workerInfo = this.getClass().getAnnotation(WorkerInfo.class);
        if (Objects.isNull(workerInfo)) {
            return true;
        }

        return !workerInfo.skipIfItemsIsEmpty() || CollectionUtils.isNotEmpty(ctx.getResult().getItems());
    }

    /**
     * 获取worker的名称(通过@WorkerInfo中name属性指定)
     */
    @Override
    public String getName() {
        WorkerInfo workerInfo = this.getClass().getAnnotation(WorkerInfo.class);
        if (Objects.isNull(workerInfo)) {
            return this.getClass().getSimpleName();
        }
        return workerInfo.name();
    }
}