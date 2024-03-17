package org.zyf.javabasic.workflowpipline.node;

import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.core.WorkerExecutor;

/**
 * @program: zyfboot-javabasic
 * @description: 任务执行器
 * @author: zhangyanfeng
 * @create: 2024-02-15 10:01
 **/
public class WorkerNodeExecutor extends AbstractNodeExecutor {
    @Override
    public WorkflowExecutionContext doExecute(Workflow workflow, ExecutionNode executionNode, WorkflowExecutionContext context) throws Exception {
        if (!workflow.getWorkerMap().containsKey(executionNode.getNodeName())) {
            throw new Exception("动态工作流配置有误");
        }
        return WorkerExecutor.executeWorker(workflow.getWorkerMap().get(executionNode.getNodeName()), context, executionNode.getScenarioIdentifier());
    }
}