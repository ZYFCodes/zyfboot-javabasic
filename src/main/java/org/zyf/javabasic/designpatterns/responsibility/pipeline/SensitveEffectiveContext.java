package org.zyf.javabasic.designpatterns.responsibility.pipeline;

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
     * 用户输入文本原稿（清洗内容后的结果）
     */
    private String content;
    /**
     * 文本来源(门店、品牌、社区、网址)
     */
    private Integer source;
    /**
     * 文本来源ID信息
     * 例如：来源为门店，此处为门店ID；来源为品牌，此处为品牌ID
     */
    private Integer sourceId;
    /**
     * 是否命中敏感词
     */
    private Boolean isHit;
    /**
     * 命中的敏感词
     */
    private List<SensitiveWord> hitWords;
    /**
     * 数据节点流转信号
     */
    private Boolean deliver;
    /**
     * 数据节点流转终止原因
     */
    private String reason;

    @Override
    public String getName() {
        return "模型实例(敏感词生效)构建上下文";
    }
}
