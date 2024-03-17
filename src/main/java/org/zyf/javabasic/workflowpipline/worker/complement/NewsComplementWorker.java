package org.zyf.javabasic.workflowpipline.worker.complement;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.zyf.javabasic.workflowpipline.annotations.WorkerInfo;
import org.zyf.javabasic.workflowpipline.core.Workflow;
import org.zyf.javabasic.workflowpipline.core.WorkflowExecutionContext;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;
import org.zyf.javabasic.workflowpipline.enums.ItemType;
import org.zyf.javabasic.workflowpipline.enums.ProcessType;
import org.zyf.javabasic.workflowpipline.model.KnowledgeMetadata;
import org.zyf.javabasic.workflowpipline.model.biz.NewExtInfo;
import org.zyf.javabasic.workflowpipline.model.biz.NewsModel;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 新闻相关信息补全整合为知识力元数据
 * @author: zhangyanfeng
 * @create: 2024-02-15 13:49
 **/
@Slf4j
@WorkerInfo(name = "newsComplementWorker")
@Service
public class NewsComplementWorker extends AbstractComplementWorker implements InitializingBean {
    @Override
    protected WorkflowExecutionContext doWork(WorkflowExecutionContext context) {
        // 抽取item
        List<Item> items = getTypedItems(context, ItemType.NEWS.name());
        if (CollectionUtils.isEmpty(items)) {
            return context;
        }

        // 模拟：按相关策略进行新闻类相关信息的源头查询，并进行转换成基本的知识力元数据 NewsConverter.fillInfo
        List<String> itemIds = items.stream().map(Item::getItemId).collect(Collectors.toList());
        List<NewsModel> news = getNewsModels(itemIds);
        if (CollectionUtils.isEmpty(news)) {
            return context;
        }

        //整合传入的知识库初始加工信息
        Map<String, Object> originData = items.stream()
                .collect(Collectors.toMap(
                        Item::getItemId,
                        item -> Objects.nonNull(item.getData()) ? item.getData() : KnowledgeMetadata.builder().build(),
                        // Keep the existing value in case of a duplicate key
                        (existingValue, newValue) -> existingValue
                ));
        ;

        // 将实际新闻类相关信息与传入加工data进行比对，整合成知识力模型返回
        List<Item> itemComplements = Lists.newArrayList();
        news.stream().forEach(newsModel -> {
            Item item = new Item();
            item.setItemId(newsModel.getId());
            item.setItemType(ItemType.NEWS.name());
            //如果本身对数据有更新则直接按传入加工已有数据初始化
            KnowledgeMetadata data = Item.getData(originData.get(newsModel.getId()), KnowledgeMetadata.class);
            if (Objects.isNull(data)) {
                data = KnowledgeMetadata.builder().build();
            }
            //提炼新闻实际内容转为对应知识力元数据
            data.setId(newsModel.getId());
            data.setContentType(ItemType.NEWS.name());
            data.setUkId(ItemType.NEWS.name().toLowerCase() + "_" + newsModel.getId());
            data.setTitle(newsModel.getTitle());
            data.setContent(newsModel.getContent());
            data.setSummary(newsModel.getSummary());
            if (StringUtils.isNotBlank(newsModel.getTitle())) {
                data.setTitleLength(newsModel.getTitle().length());
            }
            if (StringUtils.isNotBlank(newsModel.getContent())) {
                data.setContentLength(newsModel.getContent().length());
            }

            data.setAuthor(newsModel.getAuthor());
            data.setGmtCreate(newsModel.getPublishedDate());
            data.setGmtModified(newsModel.getLastUpdated());
            data.setExtInfo(getExtInfo(newsModel));
            data.setStatus(newsModel.getKnowledgeStatus(newsModel.getStatus()));
            data.setSplitType("st");
            data.setProcessType(ProcessType.FINAI.name());
            item.setData(data);
            itemComplements.add(item);
        });

        context.getResult().setItems(itemComplements);
        return context;
    }

    private List<NewsModel> getNewsModels(List<String> itemIds) {
        NewsModel news = NewsModel.builder()
                .id(String.valueOf(new Random().nextLong()))
                .title("科学家发现新的太阳系外行星")
                .summary("科学家宣布在距离地球不远的星系中发现了一颗可能适宜生命存在的行星。")
                .content("近日，天文学家在距离地球仅40光年的系外行星上发现了液态水的迹象，这颗行星围绕一颗红矮星运行，被命名为“K2-18b”。科学家认为这颗行星上可能存在生命。")
                .author("张彦峰")
                .source("网易科技")
                .publishedDate(1701398032000L)
                .lastUpdated(1701399412000L)
                .category("科技")
                .tags(Arrays.asList("太空", "发现", "外星生命"))
                .imageUrl("http://example.com/news/k2-18b.png")
                .videoUrl("http://example.com/news/k2-18b.mp4")
                .status("published")
                .language("zh-CN")
                .slug("kexuejia-faxian-xin-de-taiyangxi-waixingxing")
                .isPrivate(false)
                .viewableBy(Arrays.asList("all"))
                .build();
        NewsModel economicNews = NewsModel.builder()
                .id(String.valueOf(new Random().nextLong()))
                .title("全球经济面临挑战，国际货币基金组织下调增长预期")
                .summary("面对持续的疫情影响和地缘政治紧张，国际货币基金组织调低了全球经济增长的预期。")
                .content("根据国际货币基金组织的最新报告，由于新冠疫情的不断冲击和多地区的政治不稳定，全球经济增长率预期被下调至3.2%。这一情况对交易市场和国际投资产生了影响，许多经济学家呼吁各国政府采取措施以避免经济衰退。")
                .author("张彦峰")
                .source("财经时报")
                .publishedDate(1707361012000L)
                .lastUpdated(1707367616000L)
                .category("经济")
                .tags(Arrays.asList("全球经济", "IMF", "增长预期"))
                .imageUrl("http://example.com/news/economic-outlook.png")
                .videoUrl("http://example.com/news/economic-outlook.mp4")
                .status("published")
                .language("zh-CN")
                .slug("quanjingji-mianlin-tiaozhan")
                .isPrivate(false)
                .viewableBy(Arrays.asList("all"))
                .build();
        NewsModel fertilityNews = NewsModel.builder()
                .id(String.valueOf(new Random().nextLong()))
                .title("国家统计局数据显示，生育率持续下降引发关注")
                .summary("最新统计数据揭示，国家生育率继续下降，引起政策制定者和学者的广泛关注。")
                .content("据国家统计局的数据显示，过去十年间，国家的生育率呈持续下降趋势。目前，生育率已跌至1.3，远低于维持人口更替水平的2.1。此现象引发了政策制定者的担忧，他们正在考虑推出一系列措施来鼓励生育，包括提高育儿津贴、延长产假等。")
                .author("张彦峰")
                .source("国家日报")
                .publishedDate(1707367616000L)
                .lastUpdated(1707378416000L)
                .category("社会")
                .tags(Arrays.asList("生育率", "人口政策", "统计数据"))
                .imageUrl("http://example.com/news/fertility-rate.png")
                .videoUrl("http://example.com/news/fertility-rate.mp4")
                .status("published")
                .language("zh-CN")
                .slug("shengyulv-chixuxiajiang")
                .isPrivate(false)
                .viewableBy(Arrays.asList("all"))
                .build();

        return Lists.newArrayList(news, economicNews, fertilityNews);
    }

    private String getExtInfo(NewsModel newsModel) {
        if (Objects.isNull(newsModel)) {
            return NewExtInfo.builder().toString();
        }

        return NewExtInfo.builder()
                .category(newsModel.getCategory())
                .tags(newsModel.getTags())
                .imageUrl(newsModel.getImageUrl())
                .videoUrl(newsModel.getVideoUrl())
                .isPrivate(newsModel.isPrivate())
                .viewableBy(newsModel.getViewableBy()).build().toString();


    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Workflow.registerWorker(getName(), this);
    }
}
