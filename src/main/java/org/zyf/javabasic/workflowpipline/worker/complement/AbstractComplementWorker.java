package org.zyf.javabasic.workflowpipline.worker.complement;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.workflowpipline.core.BaseExecutorWorker;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 内容补全任务基类
 * @author: zhangyanfeng
 * @create: 2024-02-15 13:47
 **/
@Slf4j
public abstract class AbstractComplementWorker extends BaseExecutorWorker {

    @Override
    public WorkflowExecutionContext work(WorkflowExecutionContext context) {
        if (CollectionUtils.isEmpty(context.getRequest().getItems())) {
            log.info("AbstractComplementWorker items is empty, game over");
            return context;
        }

        return doWork(context);
    }

    /**
     * 子类补全
     */
    protected abstract WorkflowExecutionContext doWork(WorkflowExecutionContext context);


    /**
     * 根据类型拿到所有的item
     */
    protected List<Item> getTypedItems(WorkflowExecutionContext context, String... itemType) {
        return context.getResult().getItems().stream()
                .filter((item) -> StringUtils.equalsAnyIgnoreCase(item.getItemType(), itemType))
                .collect(Collectors.toList());
    }

    protected void addResult(WorkflowExecutionContext context, List<Item> typedItems) {
        List<Item> resultItems = context.getResult().getItems();

        if (CollectionUtils.isEmpty(resultItems)) {
            context.getResult().setItems(typedItems);
        }
    }
}

