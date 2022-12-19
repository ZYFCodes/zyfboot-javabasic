package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description 用户文本属性
 * @date 2022/12/14  17:31
 */
@Data
@Builder
public class ContentAttr {
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
     * 外卖 闪购 医药 文娱 电商 打车 骑车 物流
     */
    private Integer bizType;
}
