package org.zyf.javabasic.workflowpipline.worker.sink;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.zyf.javabasic.workflowpipline.core.BaseExecutorWorker;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 内容落库处理
 * @author: zhangyanfeng
 * @create: 2024-02-15 14:09
 **/
@Slf4j
public abstract class AbstractSinkWorker extends BaseExecutorWorker {
    @Override
    public WorkflowExecutionContext work(WorkflowExecutionContext context, String uniqueId) {
        List<Item> items = context.getResult().getItems();
        if (CollectionUtils.isEmpty(items)) {
            log.info(getName() + " items is empty");
            return context;
        }

        boolean success = sink(context, uniqueId);

        log.info(getName() + " sink success:" + success);

        return context;
    }

    @Override
    public WorkflowExecutionContext work(WorkflowExecutionContext context) {
        return work(context, StringUtils.EMPTY);
    }

    /**
     * 子类实现插入不同数据库
     */
    protected boolean sink(WorkflowExecutionContext context, String uniqueId) {
        return sink(context);
    }

    protected abstract boolean sink(WorkflowExecutionContext context);

//
//    protected void logSinkData(ProcessPipelineContext context, Map<String, Object> dataMap) {
//        addInfo(context, LogKey.SINK_DATA, getName(), dataMap.keySet());
//    }
//
//    /**
//     * 业务日志
//     */
//    public static void addInfo(ProcessPipelineContext context, String key, Object... msg) {
//        final String VERTICAL = "|";
//        String scene = context.getRequest().getScene();
//
//        StringBuilder sb = new StringBuilder(key).append(VERTICAL);
//        for (Object s : msg) {
//            sb.append(s).append(VERTICAL);
//        }
//        info(VERTICAL, scene, VERTICAL, sb.toString());
//    }
//
//    public static void info(String message, Object... params) {
//        log.info(format(message, params));
//    }
//
//    /**
//     * 日志信息参数化格式化
//     */
//    public static String format(String message, Object... params) {
//        if (params != null && params.length != 0 && message.contains("{0}")) {
//            return MessageUtil.formatMessage(message, params);
//        }
//        StringBuilder log = new StringBuilder();
//        log.append(message + " ");
//        if (params == null) {
//            log.append(params);
//        } else {
//            for (Object o : params) {
//                log.append(o instanceof String || o instanceof Character || o instanceof Boolean || o instanceof Number ? o : JSON.toJSONString(o));
//            }
//        }
//        return log.toString();
//    }
}