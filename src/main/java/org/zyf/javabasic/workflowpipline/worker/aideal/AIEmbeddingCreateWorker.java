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

import java.util.*;

/**
 * @program: zyfboot-javabasic
 * @description: 向量相关处理生成
 * @author: zhangyanfeng
 * @create: 2024-02-15 10:37
 **/
@Slf4j
@WorkerInfo(name = "aIEmbeddingCreateWorker")
@Service
public class AIEmbeddingCreateWorker extends AbstractAIDealWorker implements InitializingBean {
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
            if (StringUtils.isBlank(title)) {
                continue;
            }

            //模拟：根据标题成对应的向量信息
            data.setEmbedding(embedding(title));
            item.setData(data);
        }

        context.getResult().setItems(typedItems);
        return context;
    }

    private List<Double> embedding(String text) {
        // 假设的词汇表，包含了所有可能的单词
        final List<String> VOCABULARY = Arrays.asList(
                "科学家", "发现", "新", "太阳", "系外", "行星", "地球", "液态", "水", "迹象", "国际货币基金组织", "报告", "新冠疫情", "冲击", "政治", "不稳定",
                "全球", "经济", "增长率", "下调", "3.2%", "交易", "市场", "国际",
                "投资", "影响", "经济学家", "政府", "措施", "经济衰退", "国家统计局",
                "数据", "十年", "生育率", "下降", "跌至", "1.3", "人口", "更替",
                "2.1", "政策", "制定者", "担忧", "推出", "鼓励", "生育", "育儿津贴",
                "产假"
        );

        // 初始化向量
        List<Double> vector = new ArrayList<>(Collections.nCopies(VOCABULARY.size(), 0.0));

        // 分词（这里简化处理，实际应用应考虑更复杂的分词方法）
        String[] words = text.split("\\s+|，|。|、");

        // One-Hot编码，如果词汇表中包含单词，则相应位置为1.0，否则为0.0
        for (String word : words) {
            int index = VOCABULARY.indexOf(word);
            if (index != -1) {
                vector.set(index, 1.0);
            }
        }

        return generateUniqueRandomDoubleList(8);
    }

    public List<Double> generateUniqueRandomDoubleList(int size) {
        Random random = new Random();
        Set<Double> uniqueNumbers = new HashSet<>();

        // 继续生成随机数直到集合达到所需的大小
        while (uniqueNumbers.size() < size) {
            // 假设我们希望生成的随机数在0到100之间
            double randomNumber = 100.0 * random.nextDouble();
            uniqueNumbers.add(randomNumber);
        }

        // 将生成的随机数集合转换为列表
        return new ArrayList<>(uniqueNumbers);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}
