package org.zyf.javabasic.designpatterns.strategy.combination;

/**
 * @author yanfengzhang
 * @description 订单支付方式枚举
 * @date 2022/3/8  23:19
 */
public enum OrderPayMethodEnum {
    WEIXIN(0, "微信支付"),
    ZHIFUBAO(1, "支付宝支付"),
    WALLET(2, "本地钱包支付");

    /**
     * 订单支付方式
     */
    private Integer payType;
    /**
     * 描述
     */
    private String desc;

    public Integer getPayType() {
        return payType;
    }

    public String getDesc() {
        return desc;
    }

    OrderPayMethodEnum(Integer payType) {
        this.payType = payType;
    }

    OrderPayMethodEnum(Integer payType, String desc) {
        this.desc = desc;
        this.payType = payType;
    }

    /**
     * 根据订单支付类型payType获取对应的订单支付类型枚举
     *
     * @param payType 订单支付类型payType
     * @return
     */
    public static OrderPayMethodEnum getEnumBypayType(Integer payType) {
        for (OrderPayMethodEnum orderPayMethodEnum : OrderPayMethodEnum.values()) {
            if (orderPayMethodEnum.getPayType().equals(payType)) {
                return orderPayMethodEnum;
            }
        }
        return null;
    }

    /**
     * 根据订单支付类型描述获取对应的订单支付类型枚举
     *
     * @param desc 订单支付类型描述
     * @return
     */
    public static OrderPayMethodEnum getEnumByDesc(String desc) {
        for (OrderPayMethodEnum orderPayMethodEnum : OrderPayMethodEnum.values()) {
            if (orderPayMethodEnum.getDesc().equals(desc)) {
                return orderPayMethodEnum;
            }
        }
        return null;
    }

    /**
     * 判断类型是否为指定类型
     *
     * @param payType 订单支付类型payType
     * @return
     */
    public static boolean isAppealTypeEnum(Integer payType) {
        if (payType == null) {
            return false;
        }
        for (OrderPayMethodEnum tempEnum : OrderPayMethodEnum.values()) {
            if (tempEnum.getPayType().equals(payType)) {
                return true;
            }
        }
        return false;
    }
}
