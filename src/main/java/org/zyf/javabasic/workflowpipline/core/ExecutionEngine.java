package org.zyf.javabasic.workflowpipline.core;

import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowResult;

/**
 * @program: zyfboot-javabasic
 * @description: 具体执行引擎实现
 * @author: zhangyanfeng
 * @create: 2024-02-14 09:58
 **/
@Service
public class ExecutionEngine {
    /**
     * 执行工作流水线
     */
    public WorkflowResult process(WorkflowExecutionContext context) {
        WorkflowExecutionContext workflowExecutionContext = WorkflowExecutor
                .INSTANCE
                .pipelineExecute(new Workflow(), context);
        return workflowExecutionContext.getResult();
    }
}