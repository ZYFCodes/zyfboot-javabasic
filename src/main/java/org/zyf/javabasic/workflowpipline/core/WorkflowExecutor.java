package org.zyf.javabasic.workflowpipline.core;

import lombok.extern.slf4j.Slf4j;
import org.zyf.javabasic.workflowpipline.constants.PipelineConstant;
import org.zyf.javabasic.workflowpipline.node.ExecutionNode;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 工作流执行器具体运行
 * @author: zhangyanfeng
 * @create: 2024-02-14 10:02
 **/
@Slf4j
public class WorkflowExecutor {
    /**
     * 单例
     */
    public static final WorkflowExecutor INSTANCE = new WorkflowExecutor();

    public WorkflowExecutionContext pipelineExecute(Workflow workerflow, WorkflowExecutionContext context) {
        List<ExecutionNode> executionNodes = workerflow.initExecutionNodes(context);
        for (ExecutionNode executionNode : executionNodes) {
            try {
                context = executionNode.getExecMode().getExecutor().execute(workerflow, executionNode, context);
            } catch (Exception e) {
                log.error(executionNode.getNodeName() + e.getMessage());
            }
            // 是否终止pipeline
            boolean breakPipeline = Boolean.parseBoolean(context.getContextAttr(PipelineConstant.PIPELINE_FOECE_BREAK));
            if (breakPipeline) {
                log.warn("ProcessPipelineExecutor pipeline force break by " + executionNode.getNodeName());
                break;
            }
        }
        return context;
    }
}