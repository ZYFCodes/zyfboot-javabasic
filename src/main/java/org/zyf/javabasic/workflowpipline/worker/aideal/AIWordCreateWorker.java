package org.zyf.javabasic.workflowpipline.worker.aideal;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;
import org.zyf.javabasic.workflowpipline.model.KnowledgeMetadata;
import org.zyf.javabasic.workflowpipline.model.common.WordDeal;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 关键词实体词处理
 * @author: zhangyanfeng
 * @create: 2024-02-15 12:30
 **/
@Slf4j
@WorkerInfo(name = "aIWordCreateWorker")
@Service
public class AIWordCreateWorker extends AbstractAIDealWorker implements InitializingBean {
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

            String title = data.getTitle();
            String content = data.getContent();

            //模拟：根据标题和内容生成对应的关键词和实体词
            WordDeal info = getWordDealRes(title, content);
            data.setKeyword(info.getKeyword());
            data.setEntityWord(info.getEntityWord());
            item.setData(data);
        }

        context.getResult().setItems(typedItems);
        return context;
    }

    private WordDeal getWordDealRes(String title, String content) {
        return WordDeal.builder()
                .keyword(extractKeywords(title, content))
                .entityWord(extractEntities(content)).build();
    }

    /**
     * 生成关键词通常是通过自然语言处理（NLP）技术来实现的。在实际应用中涉及到文本分析、词频统计、词性标注、以及可能的机器学习模型。
     * 在编程语言中，这通常需要依赖于专门的库来处理复杂的NLP任务。
     * <p>
     * 如果只是简单的关键词提取，可以考虑以下几个简易步骤：
     * 分词：将文章标题和内容分割成单词或短语。
     * 去除停用词：停用词是在文本处理中通常被忽略的词汇，如“的”、“和”、“在”等。
     * 词频统计：统计每个单词的出现频率。
     * 提取关键词：选择最频繁的词汇作为关键词。
     * 下面基于Java的代码示例演示如何实现基本的关键词提取
     */
    private List<String> extractKeywords(String title, String content) {
        // 示例的停用词列表
        final Set<String> STOP_WORDS = Sets.newHashSet("的", "和", "在", "是", "有", "我", "了", "不");
        // 合并标题和内容
        String fullText = title + " " + content;

        // 正则表达式用于匹配非字母数字字符，用于分词
        Pattern pattern = Pattern.compile("[^\\p{IsAlphabetic}\\p{IsDigit}]+");

        // 分词并转为小写
        List<String> words = Arrays.stream(pattern.split(fullText.toLowerCase()))
                .filter(word -> !word.isEmpty() && !STOP_WORDS.contains(word)) // 过滤停用词和空字符串
                .collect(Collectors.toList());

        // 统计词频
        Map<String, Long> frequencyMap = words.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        // 根据词频降序排序，提取关键词
        List<String> keywords = frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        return keywords;
    }


    /**
     * 模拟一个非常基础的实体词识别器，用预定义列表中的实体与文本进行匹配
     */
    private List<String> extractEntities(String text) {
        // 模拟的实体列表
        final List<String> PERSON_ENTITIES = Arrays.asList("张三", "李四", "王五", "天文学家", "经济学家", "政策制定者");
        final List<String> LOCATION_ENTITIES = Arrays.asList("北京", "上海", "深圳", "地球");
        final List<String> ORGANIZATION_ENTITIES = Arrays.asList("苹果公司", "谷歌", "微软", "国际货币基金组织", "国家统计局");
        final List<String> ASTRONOMY_ENTITIES = Arrays.asList("系外行星", "红矮星", "K2-18b");
        final List<String> ECONOMIC_INDICATORS = Arrays.asList("全球经济增长率", "生育率");
        final List<String> NUMERIC_VALUES = Arrays.asList("3.2%", "1.3", "2.1");
        final List<String> SOCIAL_CONCEPTS = Arrays.asList("新冠疫情", "经济衰退", "产假", "育儿津贴");
        List<String> identifiedEntities = new ArrayList<>();

        // 根据列表识别不同类型的实体
        identifiedEntities.addAll(findEntitiesInText(text, PERSON_ENTITIES));
        identifiedEntities.addAll(findEntitiesInText(text, LOCATION_ENTITIES));
        identifiedEntities.addAll(findEntitiesInText(text, ORGANIZATION_ENTITIES));
        identifiedEntities.addAll(findEntitiesInText(text, ASTRONOMY_ENTITIES));
        identifiedEntities.addAll(findEntitiesInText(text, ECONOMIC_INDICATORS));
        identifiedEntities.addAll(findEntitiesInText(text, NUMERIC_VALUES));
        identifiedEntities.addAll(findEntitiesInText(text, SOCIAL_CONCEPTS));

        return identifiedEntities;
    }

    // 一个辅助方法用来在文本中查找实体
    private List<String> findEntitiesInText(String text, List<String> entities) {
        List<String> foundEntities = new ArrayList<>();
        for (String entity : entities) {
            if (text.contains(entity)) {
                foundEntities.add(entity);
            }
        }
        return foundEntities;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}