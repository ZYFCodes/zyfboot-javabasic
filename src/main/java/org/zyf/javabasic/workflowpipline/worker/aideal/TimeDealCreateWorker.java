package org.zyf.javabasic.workflowpipline.worker.aideal;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;
import org.zyf.javabasic.workflowpipline.model.KnowledgeMetadata;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: zyfboot-javabasic
 * @description: 时效性分析处理
 * @author: zhangyanfeng
 * @create: 2024-02-15 13:43
 **/
@Slf4j
@WorkerInfo(name = "timeDealCreateWorker")
@Service
public class TimeDealCreateWorker extends AbstractAIDealWorker implements InitializingBean {
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

            //模拟：根据标题成对应的情绪分
            ArticleTimeliness timeliness = new ArticleTimeliness(data.getGmtCreate());
            // 分析文章中的时间表达式
            timeliness.analyzeTimeExpressions(data.getTitle(), data.getContent());
            data.setStartValidTs(timeliness.getStartValidTs());
            data.setEndValidTs(timeliness.getEndValidTs());
            data.setStartShownTs(timeliness.getStartShownTs());
            data.setEndShownTs(timeliness.getEndShownTs());
            item.setData(data);
        }

        context.getResult().setItems(typedItems);
        return context;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }

    class ArticleTimeliness {

        private Long startValidTs;
        private Long endValidTs;
        private Long startShownTs;
        private Long endShownTs;

        // 构造函数，初始化有效开始时间为文章发布时间
        public ArticleTimeliness(Long publicationTimestamp) {
            this.startValidTs = publicationTimestamp;
            // 转换时间戳为LocalDateTime来增加一年时间，得到有效截止时间
            LocalDateTime publicationDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(publicationTimestamp), ZoneId.systemDefault());
            this.endValidTs = publicationDate.plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }

        // 从文章标题和内容中提取时间表达式并更新语义时间字段
        public void analyzeTimeExpressions(String articleTitle, String articleContent) {
            String combinedText = articleTitle + " " + articleContent;

            // 正则表达式匹配简单的时间表达式（这里仅为示例，实际情况可能复杂得多）
            // 匹配格式如“2023年3月5日”
            Pattern pattern = Pattern.compile("\\d{4}年\\d{1,2}月\\d{1,2}日");
            Matcher matcher = pattern.matcher(combinedText);

            // 日期格式化器，假设日期格式为 "yyyy年MM月dd日"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

            while (matcher.find()) {
                String dateStr = matcher.group();
                if (startShownTs == null) {
                    // 如果开始语义时间还未设置，将它设置为第一个找到的时间
                    startShownTs = LocalDate.parse(dateStr, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
                } else {
                    // 如果开始语义时间已经设置，尝试更新截止语义时间
                    endShownTs = LocalDate.parse(dateStr, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
                }
            }
        }

        public Long getStartValidTs() {
            return startValidTs;
        }

        public Long getEndValidTs() {
            return endValidTs;
        }

        public Long getStartShownTs() {
            return startShownTs;
        }

        public Long getEndShownTs() {
            return endShownTs;
        }
    }
}
