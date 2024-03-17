package org.zyf.javabasic.workflowpipline.enums;

import org.zyf.javabasic.workflowpipline.node.*;

/**
 * @program: zyfboot-javabasic
 * @description: 节点执行方式
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:30
 **/
public enum ExecMode {
    /**
     * 并行执行
     */
    parallel(new ParallelNodeExecutor()),
    /**
     * 异步执行，不做结果返回
     */
    async(new AsyncNodeExecutor()),
    /**
     * 串行执行
     */
    serial(new SerialNodeExecutor()),
    /**
     * worker执行节点
     */
    worker(new WorkerNodeExecutor());

    /**
     * 节点类型对应的执行器
     */
    private NodeExecutor executor;

    ExecMode(NodeExecutor executor) {
        this.executor = executor;
    }

    public NodeExecutor getExecutor() {
        return executor;
    }
}

