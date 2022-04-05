package org.zyf.javabasic.designpatterns.responsibility.base;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 活动信息
 * @date 2022/3/30 22:38
 */
@Data
@Builder
public class ActivityDto {
    /**
     * 活动主键
     */
    private Long id;
    /**
     * 对应活动名称
     */
    private String activityName;
    /**
     * 对应商品信息
     */
    private List<Long> skuIds;
    /**
     * 对应附加商品
     */
    private Long spuId;
    /**
     * 活动类型：1.降价活动;2.限时活动;3.买赠活动;4.首购优惠活动;5.自动续费活动;6.折上优惠活动
     */
    private Integer activityType;
    /**
     * 活动开始时间  "2019-06-26 19:00:00"
     */
    private String startTime;
    /**
     * 活动结束时间  "2019-06-26 23:00:00"
     */
    private String endTime;
    /**
     * 活动价格设定
     */
    private BigDecimal activityPrice;
    /**
     * 活动城市
     */
    private String cityIds;
    /**
     * 活动状态：上线、下线
     */
    private Integer status;
    /**
     * 活动对应的配置信息
     */
    private String activityConfig;
    /**
     * 活动唯一标示
     */
    private Long activityUuid;
    /**
     * 活动创建人
     */
    private String creator;
    /**
     * 活动最新操作人
     */
    private String lastOperator;
    /**
     * 活动适用人群类型：例如会员等机制
     */
    private String userTypes;
}
