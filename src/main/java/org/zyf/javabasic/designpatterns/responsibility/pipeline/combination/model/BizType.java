package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model;

/**
 * @author yanfengzhang
 * @description 业务类型 外卖 闪购 医药 文娱 电商 打车 骑车 物流
 * @date 2022/12/15  20:27
 */
public enum BizType {
    TAKEAWAY(1, "任意资质"),
    FALASH_SALE(2, "匹配全部资质"),
    MREDICINE(3, "任意资质"),
    ENTERTAINMENT(4, "匹配全部资质"),
    E_COMMERCE(5, "任意资质"),
    TAXI(6, "匹配全部资质"),
    RIDE(7, "任意资质"),
    LOGISTICS(8, "匹配全部资质");

    /**
     * 业务类型
     */
    private Integer type;
    /**
     * 业务类型描述
     */
    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    BizType(Integer code) {
        this.type = code;
    }

    BizType(Integer code, String desc) {
        this.desc = desc;
        this.type = code;
    }

    /**
     * 根据业务类型type获取对应的业务类型
     *
     * @param type 业务类型type
     * @return
     */
    public static BizType getEnumById(Integer type) {
        for (BizType BizType : BizType.values()) {
            if (BizType.getType().equals(type)) {
                return BizType;
            }
        }
        return null;
    }

    /**
     * 根据业务类型描述获取对应的业务类型
     *
     * @param desc 业务类型描述
     * @return
     */
    public static BizType getEnumByDesc(String desc) {
        for (BizType BizType : BizType.values()) {
            if (BizType.getDesc().equals(desc)) {
                return BizType;
            }
        }
        return null;
    }

    /**
     * 判断业务类型type是否在指定范围
     *
     * @param type 业务类型type
     * @return true-在指定范围
     */
    public static boolean isBizType(Integer type) {
        if (null == type) {
            return false;
        }
        for (BizType tempEnum : BizType.values()) {
            if (tempEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
