package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/4  16:18
 */
@Builder
@Data
public class SensitveResContext extends PipelineContext {
    /**
     * 用户输入文本原稿
     */
    private String content;
    /**
     * 清洗内容后的结果
     */
    private String cleanContent;
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
