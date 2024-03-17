package org.zyf.javabasic.workflowpipline.worker.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.zyf.javabasic.workflowpipline.constants.PipelineConstant;
import org.zyf.javabasic.workflowpipline.core.BaseExecutorWorker;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 内容过滤处理分析基类
 * @author: zhangyanfeng
 * @create: 2024-02-15 13:51
 **/
@Slf4j
public abstract class AbstractFilterWorker extends BaseExecutorWorker {
    @Override
    public WorkflowExecutionContext work(WorkflowExecutionContext context) {
        if (CollectionUtils.isEmpty(context.getRequest().getItems())) {
            log.info("AbstractFilterWorker items is empty, skip filter，return");
            return context;
        }
        List<Item> filterPreItems = context.getResult().getItems();
        doFilter(context);
        List<Item> filterAfterItems = context.getResult().getItems();
        logFilterItem(filterPreItems, filterAfterItems);

        if (CollectionUtils.isEmpty(filterAfterItems)) {
            context.setContextAttr(PipelineConstant.PIPELINE_FOECE_BREAK, "true");
        }
        return context;
    }

    /**
     * 具体过滤逻辑
     */
    protected abstract WorkflowExecutionContext doFilter(WorkflowExecutionContext context);

    /**
     * 打印过滤item信息
     */
    private void logFilterItem(List<Item> filterPreItems, List<Item> filterAfterItems) {
        List<String> filterItems = filterPreItems.stream()
                .filter(item -> !filterAfterItems.contains(item))
                .map(item -> item.getItemType() + ":" + item.getItemId())
                .collect(Collectors.toList());
        log.info("AbstractFilterWorker|worker={}|size={}|filterId={}|", this.getName(), filterItems.size(), String.join(",", filterItems));
    }
}
