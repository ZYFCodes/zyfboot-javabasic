package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 命中敏感词信息
 * @date 2022/4/5  22:23
 */
@Builder
@Data
public class SensitiveWord {
    /**
     * 敏感词
     */
    private String sensitive;
    /***********************命中敏感词信息处理************************/
    /**
     * 敏感词编号
     */
    private Long sensitiveId;
    /**
     * 敏感词归类：词库编号、正则编号等
     */
    private Integer kind;
}
