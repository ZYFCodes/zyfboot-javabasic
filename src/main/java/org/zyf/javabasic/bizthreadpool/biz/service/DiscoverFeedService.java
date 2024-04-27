package org.zyf.javabasic.bizthreadpool.biz.service;

import org.zyf.javabasic.bizthreadpool.biz.service.model.DiscoverFeedRequest;
import org.zyf.javabasic.bizthreadpool.biz.service.model.DiscoverFeedRes;

/**
 * @program: zyfboot-javabasic
 * @description: 发现流对外服务
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:22
 **/
public interface DiscoverFeedService {

    /**
     * 小红书推荐流
     * @param request 请求
     * @return 推荐流信息
     */
    public DiscoverFeedRes getRecommendFeeds(DiscoverFeedRequest request);

    /**
     * 小红书直播流
     * @param request 请求
     * @return 推荐流信息
     */
    public DiscoverFeedRes getLiveFeeds(DiscoverFeedRequest request);
}
