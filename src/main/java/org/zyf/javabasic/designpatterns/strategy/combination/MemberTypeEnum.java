package org.zyf.javabasic.designpatterns.strategy.combination;

/**
 * @author yanfengzhang
 * @description 订单会员类型
 * @date 2022/3/8  23:22
 */
public enum MemberTypeEnum {
    NONACTIVATED(0, "非会员，未激活"),
    NEWCOMER(1, "新晋会员"),
    SILVER(2, "白银会员"),
    GOLD(3, "黄金会员"),
    PLATINUM(4, "铂金会员");

    /**
     * 订单会员类型
     */
    private Integer type;
    /**
     * 描述
     */
    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    MemberTypeEnum(Integer type) {
        this.type = type;
    }

    MemberTypeEnum(Integer type, String desc) {
        this.desc = desc;
        this.type = type;
    }

    /**
     * 根据订单会员类型type获取对应的订单会员类型枚举
     *
     * @param type 订单会员类型type
     * @return
     */
    public static MemberTypeEnum getEnumBytype(Integer type) {
        for (MemberTypeEnum memberTypeEnum : MemberTypeEnum.values()) {
            if (memberTypeEnum.getType().equals(type)) {
                return memberTypeEnum;
            }
        }
        return null;
    }

    /**
     * 根据订单会员类型描述获取对应的订单会员类型枚举
     *
     * @param desc 订单会员类型描述
     * @return
     */
    public static MemberTypeEnum getEnumByDesc(String desc) {
        for (MemberTypeEnum memberTypeEnum : MemberTypeEnum.values()) {
            if (memberTypeEnum.getDesc().equals(desc)) {
                return memberTypeEnum;
            }
        }
        return null;
    }

    /**
     * 判断类型是否为指定类型
     *
     * @param type 订单会员类型type
     * @return
     */
    public static boolean isAppealTypeEnum(Integer type) {
        if (type == null) {
            return false;
        }
        for (MemberTypeEnum tempEnum : MemberTypeEnum.values()) {
            if (tempEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
