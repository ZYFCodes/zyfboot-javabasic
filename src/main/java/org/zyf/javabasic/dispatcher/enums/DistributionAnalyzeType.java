package org.zyf.javabasic.dispatcher.enums;

import org.zyf.javabasic.dispatcher.content.DistributionContent;
import org.zyf.javabasic.dispatcher.content.type.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: zyfboot-javabasic
 * @description: 分发类型分析
 * @author: zhangyanfeng
 * @create: 2024-04-21 17:53
 **/
public enum DistributionAnalyzeType {
    /**
     * 图片的文本分析
     */
    IMAGE(ImageDistributionContent.class, DistributionType.IMAGE),

    /**
     * 股票的文本分析
     */
    STOCK(StockDistributionContent.class, DistributionType.STOCK),

    /**
     * 基金的文本分析
     */
    FUND(FundDistributionContent.class, DistributionType.FUND),

    /**
     * 视频分析
     */
    VIDEO(VideoDistributionContent.class, DistributionType.VIDEO),

    /**
     * 音频分析
     */
    AUDIO(AudioDistributionContent.class, DistributionType.AUDIO),

    /**
     * 话题的文本分析
     */
    TOPIC(TopicDistributionContent.class, DistributionType.TOPIC);

    /**
     * 对应 DistributionContent 的类
     */
    private Class<? extends DistributionContent> distributionContentClass;

    /**
     * 对应的实际分发类型
     */
    private DistributionType realDistributionType;

    /**
     * DistributionAnalyzeType 构造方法
     *
     * @param contentClass         分发内容对应的类
     * @param realDistributionType 实际的分发类型
     */
    DistributionAnalyzeType(Class<? extends DistributionContent> contentClass, DistributionType realDistributionType) {
        this.distributionContentClass = contentClass;
        this.realDistributionType = realDistributionType;
    }

    /**
     * 获取 DistributionContent 类
     *
     * @return DistributionContent 类
     */
    public Class<? extends DistributionContent> getDistributionContentClass() {
        return distributionContentClass;
    }

    /**
     * 获取对应的实际分发类型
     *
     * @return 实际的分发类型
     */
    public DistributionType getRealDistributionType() {
        return realDistributionType;
    }

    /**
     * 获取所有分析类型的名称集合
     *
     * @return 所有分析类型的名称集合
     */
    public static Set<String> getAllAnalyzeTypes() {
        Set<String> allAnalyzeTypes = new HashSet<>();
        for (DistributionAnalyzeType type : values()) {
            allAnalyzeTypes.add(type.name());
        }
        return allAnalyzeTypes;
    }

    /**
     * 根据实际分发类型获取对应的分析类型
     *
     * @param distributionType 实际的分发类型
     * @return 分析类型
     */
    public static DistributionAnalyzeType getByDistributionType(DistributionType distributionType) {
        for (DistributionAnalyzeType type : values()) {
            if (type.realDistributionType == distributionType) {
                return type;
            }
        }
        return null;
    }
}
