package org.zyf.javabasic.workflowpipline.worker.check;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.BaseExecutorWorker;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowRequest;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowResult;

/**
 * @program: zyfboot-javabasic
 * @description: 初始化参数检查
 * @author: zhangyanfeng
 * @create: 2024-02-15 13:46
 **/
@Slf4j
@WorkerInfo(name = "initCheckWorker")
@Service
public class InitCheckWorker extends BaseExecutorWorker implements InitializingBean {
    @Override
    public WorkflowExecutionContext work(WorkflowExecutionContext context) {
        log.info("InitCheckWorker deal!");
        WorkflowRequest request = context.getRequest();
        log.info("InitCheckWorker request check items valid info done!");

        //假定检查数据没有问题，则当前默认结果中需要对齐进行后续的加工处理
        //模拟一个基本结果类型
        WorkflowResult result = new WorkflowResult();
        result.setItems(context.getRequest().getItems());
        context.setResult(result);

        return context;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}
