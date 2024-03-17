package org.zyf.javabasic.workflowpipline.node;

import lombok.extern.log4j.Log4j2;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;

/**
 * @program: zyfboot-javabasic
 * @description: 节点执行器实现模版
 * @author: zhangyanfeng
 * @create: 2024-02-14 10:35
 **/
@Log4j2
public abstract class AbstractNodeExecutor implements NodeExecutor {
    /**
     * 提交执行处理
     */
    @Override
    public WorkflowExecutionContext execute(Workflow workflow,
                                            ExecutionNode executionNode,
                                            WorkflowExecutionContext context) throws Exception {
        return doExecute(workflow, executionNode, context);
    }


    /**
     * 执行节点逻辑
     */
    public abstract WorkflowExecutionContext doExecute(Workflow workflow,
                                                       ExecutionNode executionNode,
                                                       WorkflowExecutionContext context) throws Exception;
}