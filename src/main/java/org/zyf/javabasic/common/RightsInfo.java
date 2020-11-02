package org.zyf.javabasic.common;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/11/2  20:09
 */
@Data
public class RightsInfo {
    /**
     * 自增ID
     */
    private Integer id;
    /**
     * 权益模板ID
     */
    private Integer templateId;
    /**
     * 1.红包兑换机会  2.增量包售卖
     */
    private Integer type;
    /**
     * 权益模板名称
     */
    private String name;
    /**
     * 默认权益展示名称
     */
    private String viewName;
    /**
     * 默认权益ICON
     */
    private String icon;
    /**
     * 默认权益冻结ICON
     */
    private String grayIcon;
    /**
     * 默认权益描述
     */
    private String description;
    /**
     * 参见通用字典rights_send_strategy_type定义，0：及时发放，1：周期性发放
     */
    private Integer sendStrategyType;
    /**
     * 只针对非及时发放生效，按月，按季，按年发放
     */
    private String sendStrategy;
    /**
     * 参见通用字典use_strategy_type定义，0：无限制，1：限制次数，2：限制时间
     */
    private Integer useStrategyType;
    /**
     * 只针对有使用限制权益生效
     */
    private String useStrategy;
    /**
     * 权益版本 结合use_strategy_type，用户端权益版本判断用户端权益是否更新
     */
    private Integer version;
    /**
     * 针对权益的履约方分配
     */
    private Integer sourceId;
    /**
     * 创建人Mis号
     */
    private String creator;
    /**
     * 最后修改人Mis号
     */
    private String lastOperator;
    /**
     * 权益附加属性JSON
     */
    private String extraProperty;
    /**
     * 是否是可售卖权益
     */
    private Integer canSale;
    /**
     * 是否是可赠送权益 0 否 1 可赠送权益
     */
    private Integer canGiven;
    /**
     * 是否可用
     */
    private Integer valid;
    /**
     * 创建时间
     */
    private Long ctime;
    /**
     * 修改时间
     */
    private Long utime;
}
