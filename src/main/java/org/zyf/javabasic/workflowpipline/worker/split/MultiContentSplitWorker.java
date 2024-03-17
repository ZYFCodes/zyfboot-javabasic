package org.zyf.javabasic.workflowpipline.worker.split;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;
import org.zyf.javabasic.workflowpipline.enums.ItemType;
import org.zyf.javabasic.workflowpipline.model.KnowledgeMetadata;
import org.zyf.javabasic.workflowpipline.utils.MD5Utils;
import org.zyf.javabasic.workflowpipline.worker.complement.AbstractComplementWorker;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @program: zyfboot-javabasic
 * @description: 对数据内容进行拆段分析
 * @author: zhangyanfeng
 * @create: 2024-02-15 14:31
 **/
@Slf4j
@WorkerInfo(name = "multiContentSplitWorker")
@Service
public class MultiContentSplitWorker extends AbstractComplementWorker implements InitializingBean {
    @Override
    protected WorkflowExecutionContext doWork(WorkflowExecutionContext context) {
        // 抽取item
        List<Item> items = getTypedItems(context, ItemType.NEWS.name());
        if (CollectionUtils.isEmpty(items)) {
            return context;
        }

        // 模拟：针对新闻容进行拆条处理
        List<Item> needAddItems = Lists.newArrayList();
        for (Item item : items) {
            KnowledgeMetadata data = Item.getData(item.getData(), KnowledgeMetadata.class);
            if (Objects.isNull(data)) {
                log.warn("MultiContentSplitWorker Item data is null! item={}", item);
                continue;
            }

            String content = data.getContent();
            if (StringUtils.isBlank(content)) {
                continue;
            }

            List<String> segments = new ContentSegmenter().segmentContent(content);
            if (CollectionUtils.isEmpty(segments)) {
                continue;
            }

            segments.stream().forEach(contentSeg -> {
                Item newItem = new Item();
                newItem.setItemId(item.getItemId());
                newItem.setItemType(ItemType.NEWS_CONTENT_SUG.name());
                newItem.setDynamicParams(item.getDynamicParams());
                newItem.setExt(item.getExt());
                newItem.setData(getNewKnowBaseModel(data, contentSeg));
                needAddItems.add(newItem);
            });
        }

        if (CollectionUtils.isEmpty(needAddItems)) {
            return context;
        }

        context.getResult().getItems().addAll(needAddItems);
        return context;
    }

    private KnowledgeMetadata getNewKnowBaseModel(KnowledgeMetadata curData, String contentSeg) {
        KnowledgeMetadata newKnowBaseModel = KnowledgeMetadata.builder().build();
        BeanUtils.copyProperties(curData, newKnowBaseModel);
        newKnowBaseModel.setContentType(ItemType.NEWS_CONTENT_SUG.name());
        newKnowBaseModel.setContent(contentSeg);
        newKnowBaseModel.setUkId(curData.getUkId() + MD5Utils.md5(contentSeg).substring(0, 4));
        newKnowBaseModel.setSplitType("content_ext");
        return newKnowBaseModel;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }

    static class ContentSegmenter {

        // 假设我们有一组关键短语来表示可能的新段落的开始
        private static final String[] KEY_PHRASES = {
                "此外", "另一方面", "接下来", "此外", "总之", "例如", "比如", "然而"
        };

        public static void main(String[] args) {
            String content = "这是关于历史的部分。然而，现在我们转向科学。"
                    + "关于科学的内容，我们可以看到以下事实。"
                    + "此外，数学也很有趣。比如，让我们考虑以下问题。";

            ContentSegmenter segmenter = new ContentSegmenter();
            List<String> segments = segmenter.segmentContent(content);

            for (int i = 0; i < segments.size(); i++) {
                System.out.println("段落 " + (i + 1) + ": " + segments.get(i));
            }
        }

        public List<String> segmentContent(String content) {
            // 将内容分割成句子，这里简单使用句号作为分隔符
            String[] sentences = content.split("\\. ");
            List<String> segments = new ArrayList<>();
            StringBuilder currentSegment = new StringBuilder();

            for (String sentence : sentences) {
                // 检查当前句子是否包含关键短语，如果是则开始一个新段落
                if (containsKeyPhrase(sentence)) {
                    if (currentSegment.length() > 0) {
                        segments.add(currentSegment.toString());
                        currentSegment = new StringBuilder();
                    }
                }
                // 添加句子到当前段落
                currentSegment.append(sentence).append(". ");
            }

            // 添加最后一个段落
            if (currentSegment.length() > 0) {
                segments.add(currentSegment.toString());
            }

            return segments;
        }

        private boolean containsKeyPhrase(String sentence) {
            for (String phrase : KEY_PHRASES) {
                if (sentence.contains(phrase)) {
                    return true;
                }
            }
            return false;
        }
    }
}
