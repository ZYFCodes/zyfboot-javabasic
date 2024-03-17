package org.zyf.javabasic.workflowpipline.worker.aideal;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;
import org.zyf.javabasic.workflowpipline.model.KnowledgeMetadata;

import java.util.List;
import java.util.Objects;

/**
 * @program: zyfboot-javabasic
 * @description: 文章质量评估
 * @author: zhangyanfeng
 * @create: 2024-02-15 13:41
 **/
@Slf4j
@WorkerInfo(name = "qualityScoreCreateWorker")
@Service
public class QualityScoreCreateWorker extends AbstractAIDealWorker implements InitializingBean {
    @Override
    protected WorkflowExecutionContext doWork(WorkflowExecutionContext context) {
        //抽取item
        List<Item> typedItems = context.getResult().getItems();
        if (CollectionUtils.isEmpty(typedItems)) {
            return context;
        }

        //模拟：通过请求算法服务，根据当前标题和文本给出对应的关键词和实体词信息
        for (Item item : typedItems) {
            KnowledgeMetadata data = Item.getData(item.getData(), KnowledgeMetadata.class);
            if (Objects.isNull(data)) {
                log.warn("AIWordCreateWorker Item data is null! item={}", item);
                continue;
            }

            String content = data.getContent();
            if (StringUtils.isBlank(content)) {
                continue;
            }

            //模拟：根据标题成对应的质量分
            data.setQualityScore(getQualityScore(content));
            item.setData(data);
        }

        context.getResult().setItems(typedItems);
        return context;
    }

    private double getQualityScore(String articleContent) {
        // 这里是一个非常简单的示例，实际的质量评分会更复杂
        if (articleContent == null || articleContent.isEmpty()) {
            return 0.0; // 空文章得分为0
        }

        double score = 5.0; // 基础分数

        // 增加一些简单的规则来调整分数
        if (StringUtils.containsAny(articleContent, "错误", "迹象", "不稳定", "担忧")) {
            score -= 1.0; // 文章提到“错误”，分数减少
        }
        if (articleContent.length() > 76) {
            score += 2.0; // 文章内容充实，分数增加
        }
        if (articleContent.length() < 80) {
            score -= 2.0; // 文章内容太短，分数减少
        }

        // 确保分数在0到10之间
        return Math.max(0, Math.min(score, 10));
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}

