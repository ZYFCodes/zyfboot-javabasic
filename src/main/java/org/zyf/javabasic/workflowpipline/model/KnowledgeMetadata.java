package org.zyf.javabasic.workflowpipline.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @program: zyfboot-javabasic
 * @description: 知识力元数据
 * @author: zhangyanfeng
 * @create: 2024-02-15 10:04
 **/
@Data
@Builder
public class KnowledgeMetadata {
    /**
     * 知识力唯一标识符。
     */
    private String id;

    /**
     * 唯一id，用于拆分数据唯一标识
     */
    private String ukId;

    /**
     * 知识力标题。
     */
    private String title;

    /**
     * 拆分标识：st为原内容，title_ext为标题拆分，content_ext为内容区分
     */
    private String splitType;

    /**
     * 知识力简要摘要或描述。
     */
    private String summary;

    /**
     * 知识力全文内容。
     */
    private String content;

    /**
     * 内容类型
     */
    private String contentType;

    /**
     * 内容长度
     */
    private int contentLength;

    /**
     * 标题长度
     */
    private int titleLength;

    /**
     * 使用场景
     */
    private String scene;

    /**
     * 编写知识力文章的作者姓名。
     */
    private String author;

    /**
     * 发布知识力文章的来源。
     */
    private String source;

    /**
     * 知识力发布的日期和时间。
     */
    private Long gmtCreate;

    /**
     * 知识力最后更新的日期和时间。
     */
    private Long gmtModified;

    /**
     * 知识力的分类。
     */
    private String category;

    /**
     * 与知识力文章相关联的标签列表。
     */
    private List<String> tags;

    /**
     * 知识力的状态（例如，上线/手动下线/自动下线/安全下线/时效下线）。
     */
    private String status;

    /**
     * 原始关键信息：相关的图片URL、视频URL
     */
    private String extInfo;

    /**
     * 关键词
     */
    private List<String> keyword;

    /**
     * 实体词
     */
    private List<String> entityWord;

    /**
     * 余弦向量信息
     */
    private List<Double> embedding;

    /**
     * 质量分
     */
    private Double qualityScore;

    /**
     * 情绪分
     */
    private Double moodScore;

    /**
     * 有效开始时间
     */
    private Long startValidTs;

    /**
     * 有效截止时间
     */
    private Long endValidTs;

    /**
     * 开始语意时间
     */
    private Long startShownTs;

    /**
     * 截止语意时间
     */
    private Long endShownTs;

    /**
     * 入库时间
     */
    private Long storageTime;
    /**
     * 入库更新时间
     */
    private Long updateTime;

    /**
     * 安全审核状态
     */
    private String secureStatus;

    /**
     * 加工类型
     */
    private String processType;
}
