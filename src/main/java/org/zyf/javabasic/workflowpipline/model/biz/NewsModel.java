package org.zyf.javabasic.workflowpipline.model.biz;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 代表新闻文章结构的基本基类
 * @author: zhangyanfeng
 * @create: 2024-02-15 10:09
 **/
@Data
@Builder
public class NewsModel {
    /**
     * 新闻文章的唯一标识符。
     */
    private String id;

    /**
     * 新闻标题。
     */
    private String title;

    /**
     * 新闻简要摘要或描述。
     */
    private String summary;

    /**
     * 新闻全文内容。
     */
    private String content;

    /**
     * 编写新闻文章的作者姓名。
     */
    private String author;

    /**
     * 发布新闻文章的来源。
     */
    private String source;

    /**
     * 新闻发布的日期和时间。
     */
    private Long publishedDate;

    /**
     * 新闻最后更新的日期和时间。
     */
    private Long lastUpdated;

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
     * 新闻的发布状态（例如，已发布、草稿）。
     */
    private String status;

    /**
     * 新闻文章的编写语言。
     */
    private String language;

    /**
     * 从新闻标题派生的，适用于URL的字符串。
     */
    private String slug;

    /**
     * 指示新闻是否为私有或公开的标志。
     */
    private boolean isPrivate;

    /**
     * 如果新闻为私有，可查看新闻的用户组列表。
     */
    private List<String> viewableBy;

    /**
     * 使用所有字段初始化NewsModel的构造函数。
     */
    public NewsModel(String id, String title, String summary, String content, String author,
                     String source, Long publishedDate, Long lastUpdated, String category,
                     List<String> tags, String imageUrl, String videoUrl, String status,
                     String language, String slug, boolean isPrivate, List<String> viewableBy) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.author = author;
        this.source = source;
        this.publishedDate = publishedDate;
        this.lastUpdated = lastUpdated;
        this.category = category;
        this.tags = tags;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.status = status;
        this.language = language;
        this.slug = slug;
        this.isPrivate = isPrivate;
        this.viewableBy = viewableBy;
    }

    public String getKnowledgeStatus(String status) {
        if (StringUtils.equalsIgnoreCase(status, "ONLINE")) {
            return "ONLINE";
        }
        return "OFFLINE";
    }
}
