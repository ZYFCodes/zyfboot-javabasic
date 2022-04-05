package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 命中敏感词信息
 * @date 2022/4/5  17:23
 */
@Builder
@Data
public class SensitiveWord {
    /**
     * 敏感词
     */
    private String sensitive;
    /**
     * 敏感词编号
     */
    private Long sensitiveId;
    /**
     * 敏感词归类：词库编号、正则编号等
     */
    private Integer kind;
    /**
     * 敏感词是否加白
     */
    private Boolean whitened;
    /**
     * 敏感词加白方式
     */
    private Integer whitenedType;
}
