package org.zyf.javabasic.bizthreadpool.biz.service.model;

import lombok.Builder;
import lombok.Data;
import org.zyf.javabasic.workflowpipline.dataprocess.Item;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 发现流返回结果
 * @author: zhangyanfeng
 * @create: 2024-04-27 12:21
 **/
@Data
@Builder
public class DiscoverFeedRes {
    /**
     * 内容Feed流
     */
    private List<Item> feedItems;

    /**
     * 判断是否有下一页
     */
    private boolean hasNext;

    /**
     * feedItems最后一个Item的时间戳
     */
    private Long lastTime;
}
