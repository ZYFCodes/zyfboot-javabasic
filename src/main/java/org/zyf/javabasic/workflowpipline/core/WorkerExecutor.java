package org.zyf.javabasic.workflowpipline.core;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: zyfboot-javabasic
 * @description: 实际任务执行
 * @author: zhangyanfeng
 * @create: 2024-02-14 10:13
 **/
@Slf4j
public class WorkerExecutor {
    /**
     * work执行
     */
    public static WorkflowExecutionContext executeWorker(ExecutorWorker worker,
                                                         final WorkflowExecutionContext context, String scenarioIdentifier) throws Exception {
        try {
            if (!worker.checkParams(context)) {
                return context;
            }
            return worker.work(context, scenarioIdentifier);
        } catch (Exception e) {
            boolean exceptionSkip = Boolean.parseBoolean(context.getContextAttr(worker.getName() + ".exception.skip"));
            // 跟worker配置决定是否进一步抛出异常
            if (!exceptionSkip) {
                log.error(worker.getName() + "Worker执行失败:", e);
                throw new Exception("系统繁忙或其他未处理的异常!");
            }
        }
        return context;
    }
}
