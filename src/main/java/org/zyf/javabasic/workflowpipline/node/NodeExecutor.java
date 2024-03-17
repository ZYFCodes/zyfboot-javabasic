package org.zyf.javabasic.workflowpipline.node;

import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;

/**
 * @program: zyfboot-javabasic
 * @description: 节点执行逻辑
 * @author: zhangyanfeng
 * @create: 2024-02-14 09:55
 **/
public interface NodeExecutor {
    /**
     * 节点逻辑执行
     */
    WorkflowExecutionContext execute(Workflow workflow, ExecutionNode executionNode, WorkflowExecutionContext context) throws Exception;
}
