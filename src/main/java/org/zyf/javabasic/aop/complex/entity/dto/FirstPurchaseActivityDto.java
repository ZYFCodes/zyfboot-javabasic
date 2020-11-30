package org.zyf.javabasic.aop.complex.entity.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author yanfengzhang
 * @description 首购优惠活动数据结构（只是样例）
 * @date 2020/11/4  23:26
 */
@Data
public class FirstPurchaseActivityDto {
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
    private String skuIds;
    /**
     * 对应附加商品
     */
    private Long spuId;
    /**
     * 活动类型：降价活动1、限时活动2、买赠活动3、首购优惠活动4、自动续费活动5、折上优惠活动6
     */
    private Integer activityType;
    /**
     * 活动开始时间
     */
    private Timestamp startTime;
    /**
     * 活动结束时间
     */
    private Timestamp endTime;
    /**
     * 活动价格设定
     */
    private Double activityPrice;
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
