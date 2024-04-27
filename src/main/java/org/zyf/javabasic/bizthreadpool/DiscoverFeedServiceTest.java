package org.zyf.javabasic.bizthreadpool;

import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.zyf.javabasic.ZYFApplication;
import org.zyf.javabasic.bizthreadpool.biz.service.DiscoverFeedService;
import org.zyf.javabasic.bizthreadpool.biz.service.model.DiscoverFeedRequest;
import org.zyf.javabasic.bizthreadpool.enums.ResultType;
import org.zyf.javabasic.dispatcher.base.DispatcherInfo;

/**
 * @program: zyfboot-javabasic
 * @description: 测试分析
 * @author: zhangyanfeng
 * @create: 2024-04-27 13:01
 **/
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZYFApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DiscoverFeedServiceTest {

    @Autowired
    private DiscoverFeedService discoverFeedService;

    @Test
    public void testDistributeExecutor() {
        log.info("小红书发现页实际推荐的数据内容处理");
        DiscoverFeedRequest request = DiscoverFeedRequest.builder().userId("111111").topicType(ResultType.RECOMMEND).build();
        discoverFeedService.getRecommendFeeds(request);
        log.info("小红书发现页实际直播的数据内容处理");
        discoverFeedService.getLiveFeeds(DiscoverFeedRequest.builder().userId("111111").topicType(ResultType.LIVE).build());
    }
}
