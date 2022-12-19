package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model;

public enum RegularTypeEnum {
    /**
     * 简单正则
     */
    SIAMPLE_REGULAR(1),
    /**
     * 与 正则
     */
    AND_REGULAR(2),
    /**
     * 与非 正则
     */
    N_AND_REGULAR(3);

    private int code;

    RegularTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static RegularTypeEnum getByCode(int code) {
        for (RegularTypeEnum regularTypeEnum : RegularTypeEnum.values()) {
            if (regularTypeEnum.getCode() == code) {
                return regularTypeEnum;
            }
        }
        return null;
    }
}
