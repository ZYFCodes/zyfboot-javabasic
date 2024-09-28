package org.zyf.javabasic.workflowpipline.node;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.workflowpipline.enums.ExecMode;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @program: zyfboot-javabasic
 * @description: 执行节点信息
 * @author: zhangyanfeng
 * @create: 2024-02-14 10:34
 **/
@Data
public final class ExecutionNode {
    /**
     * 节点的名称，用于标识执行节点的类型或任务
     */
    private String nodeName;

    /**
     * 节点的执行类型，表示节点执行的方式或模式。通常情况下，这里是指 ExecMode.worker
     */
    private ExecMode execMode;

    /**
     * 当前节点用于标识特定场景或情景的字符串，用于区分同一个节点在不同配置中的使用。确保全局唯一性。
     */
    private String scenarioIdentifier = StringUtils.EMPTY;

    /**
     * 该节点的子节点列表，用于表示节点的下一级子节点。
     * 每个子节点也是一个 ExecutionNode 类型的对象。
     */
    private List<ExecutionNode> executionNodes = Lists.newLinkedList();

    /**
     * 用于根据传入的节点配置信息构建节点列表。
     * 节点配置信息以字符串的形式表示，通过解析字符串构建节点的层级结构。
     */
    public static List<ExecutionNode> buildExecutionNodes(String workerConfig) {
        List<String> workerList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(workerConfig);
        Iterator<String> workerIterator = workerList.iterator();
        return buildExecutionNode(workerIterator.next(), null, null, workerIterator);
    }

    /**
     * 递归方法，用于构建执行节点的层级结构。
     * 接受节点名称、已构建的节点列表、父节点堆栈和未处理的节点名称迭代器作为参数，并根据这些参数构建节点的层级结构。
     *
     * @param name            初始节点名称
     * @param executionNodes  已经构建好的节点信息
     * @param parents         父级节点堆栈
     * @param tailWorkerNames 剩余未处理的节点名称
     * @return
     */
    private static List<ExecutionNode> buildExecutionNode(String name, List<ExecutionNode> executionNodes, Stack<ExecutionNode> parents,
                                                          Iterator<String> tailWorkerNames) {
        if (null == parents) {
            parents = new Stack<>();
        }
        if (null == executionNodes) {
            executionNodes = Lists.newLinkedList();
        }
        ExecutionNode executionNode = buildExecutionNodeByName(name);
        if (parents.isEmpty()) {
            executionNodes.add(executionNode);
        } else {
            parents.peek().getExecutionNodes().add(executionNode);
        }
        if (tailWorkerNames.hasNext()) {
            buildExecutionNode(tailWorkerNames.next(), executionNodes, parents, tailWorkerNames);
        }
        return executionNodes;
    }

    /**
     * 私有静态方法，用于构建单个执行节点。
     * 根据节点名称解析出节点的名称和场景标识符，并创建一个新的 ExecutionNode 对象。
     */
    private static ExecutionNode buildExecutionNodeByName(String name) {
        ExecutionNode executionNode = new ExecutionNode();
        String[] nameParts = StringUtils.split(name, ":");
        String nodeName = nameParts[0];
        String scenarioIdentifier = (nameParts.length > 1) ? nameParts[1] : StringUtils.EMPTY;

        executionNode.setNodeName(nodeName);
        executionNode.setExecMode(ExecMode.worker);
        executionNode.setScenarioIdentifier(scenarioIdentifier);

        return executionNode;
    }
}
