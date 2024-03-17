package org.zyf.javabasic.workflowpipline.worker.sink;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;

/**
 * @program: zyfboot-javabasic
 * @description: 数据同步到HBase
 * @author: zhangyanfeng
 * @create: 2024-02-15 14:18
 **/
@Log4j2
@WorkerInfo(name = "hbaseSinkWorker")
@Service
public class HbaseSinkWorker extends AbstractSinkWorker implements InitializingBean {
    @Override
    protected boolean sink(WorkflowExecutionContext context) {
        log.info("数据已经落入hbase");
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}
