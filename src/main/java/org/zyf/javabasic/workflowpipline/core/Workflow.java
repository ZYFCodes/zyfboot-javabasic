package org.zyf.javabasic.workflowpipline.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.workflowpipline.constants.PipelineConstant;
import org.zyf.javabasic.workflowpipline.node.ExecutionNode;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 工作流基本定义
 * @author: zhangyanfeng
 * @create: 2024-02-14 09:59
 **/
@Data
public class Workflow {
    /**
     * 用于存储所有注册的执行任务接口。执行任务接口可以执行特定的任务或者操作。
     */
    private static List<ExecutorWorker> workers = Lists.newLinkedList();

    /**
     * 用于存储执行任务接口的名称和对应的执行任务接口对象
     */
    private static Map<String, ExecutorWorker> workerMap = Maps.newHashMap();

    /**
     * 用于存储工作流的执行节点结构。每个执行节点表示了工作流程中的一个执行步骤或者操作。
     */
    private List<ExecutionNode> executionNodes = Lists.newLinkedList();

    /**
     * 用于根据传入的执行上下文信息初始化并获取执行节点列表。
     * 根据执行上下文中的配置信息，构建工作流程的执行节点，并返回给调用方。
     */
    public List<ExecutionNode> initExecutionNodes(WorkflowExecutionContext context) {
        String pipelineCfg = context.getBizWorkflowConfig(PipelineConstant.PIPELINE_CONFIG);
        if (StringUtils.isBlank(pipelineCfg)) {
            return Collections.emptyList();
        }
        return ExecutionNode.buildExecutionNodes(pipelineCfg);
    }

    /**
     * 用于获取执行任务接口的名称和对应的执行任务接口对象的映射。
     * 通过调用这个方法，可以获取所有注册的执行任务接口信息。
     */
    public Map<String, ExecutorWorker> getWorkerMap() {
        return workerMap;
    }

    /**
     * 用于注册执行任务接口。
     * 当注册执行任务接口时，会将执行任务接口添加到 workers 列表中，
     * 并将执行任务接口的名称和对象添加到 workerMap 中，以便后续查找和使用。
     */
    public static void registerWorker(String workerName, ExecutorWorker executorWorker) {
        workers.add(executorWorker);
        workerMap.put(workerName, executorWorker);
    }
}

