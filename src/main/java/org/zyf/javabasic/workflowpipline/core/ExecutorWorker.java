package org.zyf.javabasic.workflowpipline.core;

/**
 * @program: zyfboot-javabasic
 * @description: 执行任务实现接口
 * @author: zhangyanfeng
 * @create: 2024-02-14 09:56
 **/
public interface ExecutorWorker {
    /**
     * 用于执行数据处理步骤
     * 接受一个 WorkflowExecutionContext 对象作为参数，表示执行任务的上下文信息，
     * 然后执行相应的任务操作，并返回更新后的上下文信息。
     */
    WorkflowExecutionContext work(WorkflowExecutionContext context);

    /**
     * 用于执行推荐步骤
     * 与上面的方法类似，但还接受一个额外的参数 scenarioIdentifier，用于标识特定的场景或情景。
     */
    WorkflowExecutionContext work(WorkflowExecutionContext context, String scenarioIdentifier);


    /**
     * 用于根据 @WorkerInfo 注解校验执行任务所需的参数
     * 接受一个 WorkflowExecutionContext 对象作为参数，表示执行任务的上下文信息，
     * 然后校验参数是否满足任务的要求，并返回校验结果。
     */
    boolean checkParams(WorkflowExecutionContext ctx);


    /**
     * 用于获取执行任务的名称，通常是通过 @WorkerInfo 注解中的 name 属性指定的
     * 返回一个字符串，表示执行任务的名称。
     */
    String getName();
}

