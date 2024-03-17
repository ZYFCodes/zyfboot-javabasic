package org.zyf.javabasic.workflowpipline.model.biz;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 新闻附加基本信息
 * @author: zhangyanfeng
 * @create: 2024-02-15 10:06
 **/
@Data
@Builder
public class NewExtInfo {
    /**
     * 新闻的分类（例如，政治、体育等）。
     */
    private String category;

    /**
     * 与新闻文章相关联的标签列表。
     */
    private List<String> tags;

    /**
     * 与新闻相关的图片URL。
     */
    private String imageUrl;

    /**
     * 与新闻相关的视频URL。
     */
    private String videoUrl;

    /**
     * 指示新闻是否为私有或公开的标志。
     */
    private boolean isPrivate;

    /**
     * 如果新闻为私有，可查看新闻的用户组列表。
     */
    private List<String> viewableBy;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
