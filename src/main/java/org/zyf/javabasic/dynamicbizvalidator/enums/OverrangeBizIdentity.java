package org.zyf.javabasic.dynamicbizvalidator.enums;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/26  23:55
 */
public enum OverrangeBizIdentity {
    SHANGOU(1, "闪购"),
    YIYAO(2, "医药"),
    WAIMAI(3, "外卖");

    /**
     * 超范围制定业务身份处理:1-闪购,2-医药,3-外卖
     */
    private Integer code;
    /**
     * 超范围制定业务身份描述
     */
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    OverrangeBizIdentity(Integer code) {
        this.code = code;
    }

    OverrangeBizIdentity(Integer code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * 根据超范围制定业务身份处理code获取对应的身份枚举
     *
     * @param code 超范围制定业务身份处理code
     * @return
     */
    public static OverrangeBizIdentity getEnumById(Integer code) {
        for (OverrangeBizIdentity overrangeBizIdentity : OverrangeBizIdentity.values()) {
            if (overrangeBizIdentity.getCode().equals(code)) {
                return overrangeBizIdentity;
            }
        }
        return null;
    }

    /**
     * 根据超范围制定业务身份处理描述获取对应的身份枚举
     *
     * @param desc 超范围制定业务身份处理描述
     * @return
     */
    public static OverrangeBizIdentity getEnumByDesc(String desc) {
        for (OverrangeBizIdentity overrangeBizIdentity : OverrangeBizIdentity.values()) {
            if (overrangeBizIdentity.getDesc().equals(desc)) {
                return overrangeBizIdentity;
            }
        }
        return null;
    }

    /**
     * 判断超范围制定业务身份code是否在指定范围
     *
     * @param code 超范围制定业务身份处理code
     * @return true-在指定范围
     */
    public static boolean isOverrangeBizIdentity(Integer code) {
        if (null == code) {
            return false;
        }
        for (OverrangeBizIdentity tempEnum : OverrangeBizIdentity.values()) {
            if (tempEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
