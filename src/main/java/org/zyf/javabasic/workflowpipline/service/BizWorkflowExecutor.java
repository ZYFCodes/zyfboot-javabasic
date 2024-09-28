package org.zyf.javabasic.workflowpipline.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.core.ExecutionEngine;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowRequest;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowResult;

import javax.annotation.Resource;

/**
 * @program: zyfboot-javabasic
 * @description: 动态任务执行对外接口
 * @author: zhangyanfeng
 * @create: 2024-02-15 14:39
 **/
@Service
@Slf4j
public class BizWorkflowExecutor {

    @Resource
    private ExecutionEngine executionEngine;

    public WorkflowResult startDynamicExecution(WorkflowRequest request) {

        log.info("SceneProcessService startPipLineProcess request：{}", request);

        // 1.上下文构建（直接先模拟一个）
        WorkflowExecutionContext context = getProcessPipelineContext(request);

        // 3. 执行pipLine
        WorkflowResult workflowResult = executionEngine.process(context);

        log.info("SceneProcessService startPipLineProcess result：{}", workflowResult);

        return workflowResult;
    }

    private static WorkflowExecutionContext getProcessPipelineContext(WorkflowRequest request) {
        WorkflowExecutionContext workflowExecutionContext = new WorkflowExecutionContext();
        workflowExecutionContext.setRequest(request);
        return workflowExecutionContext;
    }
}
