package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 按配置条件实际生效的敏感词
 * @date 2022/4/4  22:18
 */
@Builder
@Data
public class SensitveEffectiveContext extends PipelineContext {
    /**
     * 是否命中敏感词,最终生效的
     */
    private Boolean isHit;
    /**
     * 用户输入文本原稿
     */
    private String content;
    /**
     * 用户清洗后的文本内容（清洗内容后的结果）
     */
    private String cleanContent;
    /**
     * 命中的敏感词
     */
    private List<SensitiveWord> hitWords;
    /**
     * 命中的敏感词被加白
     */
    private List<SensitiveWord> whitedWords;
    /**
     * 命中的敏感词被合规放行
     */
    private List<SensitiveWord> complianceIgnoreWords;
    /**
     * 命中的敏感词中被对应规则放行
     */
    private List<SensitiveWord> ruleIgnoreWords;

    @Override
    public String getName() {
        return "模型实例(敏感词生效)构建上下文";
    }
}
