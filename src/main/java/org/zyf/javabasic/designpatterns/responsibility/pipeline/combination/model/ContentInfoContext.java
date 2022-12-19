package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yanfengzhang
 * @description 用户输入的文本信息上下文
 * @date 2022/4/2  21:05
 */
@EqualsAndHashCode(callSuper = true)
@Builder
@Data
public class ContentInfoContext extends PipelineContext {
    /**
     * 用户输入文本原稿(清洗中会不断被更新内容)
     */
    private String content;
    /**
     * 经过清洗后的文本(任意清洗中都进行变更)
     */
    private String cleanContent;
    /**
     * 用户文本属性
     */
    private ContentAttr contentAttr;

    @Override
    public String getName() {
        return "数据清洗(用户文本)构建上下文";
    }
}
