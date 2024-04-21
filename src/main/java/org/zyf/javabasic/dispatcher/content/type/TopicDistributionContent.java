package org.zyf.javabasic.dispatcher.content.type;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.enums.DistributionType;

/**
 * @program: zyfboot-javabasic
 * @description: 话题分发内容信息
 * @author: zhangyanfeng
 * @create: 2024-04-21 15:30
 **/
@Data
public class TopicDistributionContent extends DistributionContent {

    private static final long serialVersionUID = 7298721296039236389L;

    /**
     * 判断主题分发内容是否有效
     *
     * @return 如果主题分发内容有效返回 true，否则返回 false
     */
    @Override
    public boolean isValid() {
        return StringUtils.isNotBlank(super.getContentString());
    }

    /**
     * 判断是否为主题分发类型
     *
     * @return 如果是主题分发返回 true，否则返回 false
     */
    public boolean isTopic() {
        return true;
    }

    /**
     * 获取分发类型
     *
     * @return 分发分发
     */
    public DistributionType getDistributionType() {
        return DistributionType.TOPIC;
    }

    /**
     * 获取主题分发内容
     *
     * @return 主题分发内容
     */
    public String getTopicContent() {
        return super.getContentString();
    }
}
