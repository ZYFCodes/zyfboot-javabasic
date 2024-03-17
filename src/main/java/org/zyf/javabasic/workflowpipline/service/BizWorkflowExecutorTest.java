package org.zyf.javabasic.workflowpipline.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.workflowpipline.constants.PipelineConstant;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowRequest;
import org.zyf.javabasic.workflowpipline.dataprocess.WorkflowResult;
import org.zyf.javabasic.workflowpipline.enums.ItemType;
import org.zyf.javabasic.workflowpipline.model.KnowledgeMetadata;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 测试动态执行工作引擎
 * @author: zhangyanfeng
 * @create: 2024-02-15 14:41
 **/
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BizWorkflowExecutorTest {

    @Resource
    private BizWorkflowExecutor bizWorkflowExecutor;

    @Test
    public void testProcessPipelineExecutor() {
        Item newItem = new Item();
        newItem.setItemId("1111111111111");
        newItem.setItemType(ItemType.NEWS.name());
        KnowledgeMetadata testData = KnowledgeMetadata.builder()
                .id("1111111111111")
                .contentType(ItemType.NEWS.name())
                .gmtCreate(1706861925016L).build();
        newItem.setData(testData);
        List<Item> items = Lists.newArrayList(newItem);
        WorkflowRequest request = new WorkflowRequest();
        request.setScene("ZYF_TEST_SCENE");
        request.setTestData(true);
        request.setItems(items);
        request.setExt(PipelineConstant.PIPELINE_CONFIG, "initCheckWorker,newsComplementWorker,multiContentSplitWorker," +
                "aIWordCreateWorker,moodScoreCreateWorker,qualityScoreCreateWorker,timeDealCreateWorker,aIEmbeddingCreateWorker," +
                "sensitiveContentFilterWorker,elasticsearchSinkWorker,dorisSinkWorker,hbaseSinkWorker");

        WorkflowResult workflowResult = bizWorkflowExecutor.startDynamicExecution(request);
        log.info("SceneProcessServiceTest request={}\n,processResult={}", JSON.toJSONString(request), JSON.toJSONString(workflowResult));
    }
}