package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:54
 */
public enum BusinessEnum {
    MEDIC_SP(1, "药品标品");

    private int code;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    BusinessEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    /**
     * @param code
     * @return
     */
    public static BusinessEnum getByCode(int code) {
        for (BusinessEnum sensitiveValidateTypeEnum : BusinessEnum.values()) {
            if (sensitiveValidateTypeEnum.getCode() == code) {
                return sensitiveValidateTypeEnum;
            }
        }
        return null;
    }

}

