package org.zyf.javabasic.workflowpipline.worker.sink;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;

/**
 * @program: zyfboot-javabasic
 * @description: 数据同步到ES
 * @author: zhangyanfeng
 * @create: 2024-02-15 14:17
 **/
@Log4j2
@WorkerInfo(name = "elasticsearchSinkWorker")
@Service
public class ElasticsearchSinkWorker extends AbstractSinkWorker implements InitializingBean {
    /**
     * 默认测试表
     */
    static final String DEFAULT_INDEX = "zyf_test";

    @Override
    protected boolean sink(WorkflowExecutionContext context) {

        String index;
        if (context.getRequest().isTestData()) {
            //测试数据写入 alias_real_time_task_test表
            index = context.getBizWorkflowConfig("", DEFAULT_INDEX);
            log.info("ElasticsearchSinkWorker  test  index={}", index);
        } else {
            index = context.getBizWorkflowConfig("");
        }

        // 插入/更新
        //sinkESService.sink(context, getName(), index);
        log.info("数据已经落入es");

        return true;
    }

    @Override
    protected boolean sink(WorkflowExecutionContext context, String uniqueId) {
        String index;
        if (context.getRequest().isTestData()) {
            index = context.getBizWorkflowConfig("", DEFAULT_INDEX, uniqueId);
            log.info("ElasticsearchSinkWorker  test  index={}", index);
        } else {
            index = context.getSceneConfigWithIdentifier("", uniqueId);
        }

        // 插入/更新
        // sinkESService.sink(context, getName(), index);
        log.info("数据已经落入es");

        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}
