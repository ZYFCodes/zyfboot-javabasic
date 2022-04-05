package org.zyf.javabasic.designpatterns.strategy.combination;

/**
 * @author yanfengzhang
 * @description 订单来源
 * @date 2022/3/8  23:16
 */
public enum OrderSourceEnum {
    PC(0, "pc端订单"),
    MOBILE(1, "手机端订单");

    /**
     * 订单来源方
     */
    private Integer source;
    /**
     * 描述
     */
    private String desc;

    public Integer getsource() {
        return source;
    }

    public String getDesc() {
        return desc;
    }

    OrderSourceEnum(Integer source) {
        this.source = source;
    }

    OrderSourceEnum(Integer source, String desc) {
        this.desc = desc;
        this.source = source;
    }

    /**
     * 根据订单来源类型source获取对应的订单来源类型枚举
     *
     * @param source 订单来源类型source
     * @return
     */
    public static OrderSourceEnum getEnumBySource(Integer source) {
        for (OrderSourceEnum orderSourceEnum : OrderSourceEnum.values()) {
            if (orderSourceEnum.getsource().equals(source)) {
                return orderSourceEnum;
            }
        }
        return null;
    }

    /**
     * 根据订单来源类型描述获取对应的订单来源类型枚举
     *
     * @param desc 订单来源类型描述
     * @return
     */
    public static OrderSourceEnum getEnumByDesc(String desc) {
        for (OrderSourceEnum orderSourceEnum : OrderSourceEnum.values()) {
            if (orderSourceEnum.getDesc().equals(desc)) {
                return orderSourceEnum;
            }
        }
        return null;
    }

    /**
     * 判断类型是否为指定类型
     *
     * @param source 订单来源类型source
     * @return
     */
    public static boolean isAppealTypeEnum(Integer source) {
        if (source == null) {
            return false;
        }
        for (OrderSourceEnum tempEnum : OrderSourceEnum.values()) {
            if (tempEnum.getsource().equals(source)) {
                return true;
            }
        }
        return false;
    }
}
