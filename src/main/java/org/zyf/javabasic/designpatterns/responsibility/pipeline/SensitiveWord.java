package org.zyf.javabasic.designpatterns.responsibility.pipeline;

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
    /***********************命中敏感词是否放行处理************************/
    /**
     * 敏感词是否加白
     */
    private Boolean whitened;
    /**
     * 敏感词加白方式
     */
    private Integer whitenedType;
    /**
     * 敏感词是否满足生效规则：符合规则的词可以放行，不认为命中
     */
    private Boolean hitRule;
    /**
     * 敏感词是否为合规管控处理放行信息
     */
    private Boolean hitCompliance;
}
