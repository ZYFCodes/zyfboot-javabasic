package org.zyf.javabasic.bizthreadpool.biz.service.model;

import lombok.Builder;
import lombok.Data;
import org.zyf.javabasic.bizthreadpool.enums.ResultType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: zyfboot-javabasic
 * @description: 发现流请求
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:20
 **/
@Data
@Builder
public class DiscoverFeedRequest {
    @NotNull
    private ResultType topicType;

    @NotBlank
    private String topicId;

    /**
     * 当前访问用户userId
     */
    private String userId;
    /**
     * 表示当前翻页的最后一条数据的createTime, 如果是第一页设置为null
     */
    private Long lastTime;

    /**
     * 页码数
     */
    private int pageNo = 1;

    private int pageSize = 20;

}
