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

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @program: zyfboot-javabasic
 * @description: 情绪分生成分析
 * @author: zhangyanfeng
 * @create: 2024-02-15 13:40
 **/
@Slf4j
@WorkerInfo(name = "moodScoreCreateWorker")
@Service
public class MoodScoreCreateWorker extends AbstractAIDealWorker implements InitializingBean {
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

            //模拟：根据标题成对应的情绪分
            data.setMoodScore(getSentimentScore(content));
            item.setData(data);
        }

        context.getResult().setItems(typedItems);
        return context;
    }


    private double getSentimentScore(String text) {
        if (text == null || text.isEmpty()) {
            return 0.0; // 文本为空时情绪分为0
        }

        final List<String> POSITIVE_WORDS = Arrays.asList(
                "好", "出色", "快乐", "幸福", "赞", "发现", "可能存在生命"
        );

        final List<String> NEGATIVE_WORDS = Arrays.asList(
                "坏", "糟糕", "悲伤", "愤怒", "批评", "冲击", "不稳定",
                "下调", "影响", "衰退", "下降", "担忧"
        );

        double score = 0.0;

        // 对于文本中的每个正面词，增加分数
        for (String word : POSITIVE_WORDS) {
            if (text.contains(word)) {
                score += 1.0;
            }
        }
        // 对于文本中的每个负面词，减少分数
        for (String word : NEGATIVE_WORDS) {
            if (text.contains(word)) {
                score -= 1.0;
            }
        }

        return score;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}
