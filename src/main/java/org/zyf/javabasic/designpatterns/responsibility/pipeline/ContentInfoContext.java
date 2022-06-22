package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 用户输入的文本信息上下文
 * @date 2022/4/2  21:05
 */
@Builder
@Data
public class ContentInfoContext extends PipelineContext {
    /**
     * 用户输入文本原稿(清洗中会不断被更新内容)
     */
    private String content;
    /**
     * 文本归类（商品名称、商品描述、商家公告、商家名称、经营描述、代言信息等）
     */
    private Integer belong;
    /**
     * 文本城市编号
     */
    private Integer cityCode;
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
     * 业务方接入来源
     */
    private Integer bizType;
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
        return "模型实例(用户文本)构建上下文";
    }
}
