package org.zyf.javabasic.workflowpipline.worker.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;
import org.zyf.javabasic.workflowpipline.model.KnowledgeMetadata;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 敏感词内容过滤
 * @author: zhangyanfeng
 * @create: 2024-02-15 13:53
 **/
@Slf4j
@WorkerInfo(name = "sensitiveContentFilterWorker")
@Service
public class SensitiveContentFilterWorker extends AbstractFilterWorker implements InitializingBean {
    @Override
    protected WorkflowExecutionContext doFilter(WorkflowExecutionContext context) {
        List<Item> items = context.getResult().getItems();
        // 过滤后的结果
        List<Item> result = items.stream()
                .filter(item -> {
                    KnowledgeMetadata data = Item.getData(item.getData(), KnowledgeMetadata.class);
                    if (Objects.isNull(data)) {
                        log.warn("AIWordCreateWorker Item data is null! item={}", item);
                        return false;
                    }
                    return !containsSensitiveWords(data.getTitle(), data.getContent());
                })
                .collect(Collectors.toList());

        context.getResult().setItems(result);
        return context;
    }

    public boolean containsSensitiveWords(String articleTitle, String articleContent) {
        Set<String> sensitiveWords = new HashSet<>();
        // 假设这是我们的敏感词列表
        sensitiveWords.add("敏感词1");
        sensitiveWords.add("敏感词2");
        for (String word : sensitiveWords) {
            if (articleTitle.contains(word) || articleContent.contains(word)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}
