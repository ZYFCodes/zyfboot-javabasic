package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  19:39
 */
public enum SensitiveValidateTypeEnum {
    KEYWORD(0),
    SIAMPLE_REGULAR(1),
    AND_REGULAR(2),
    N_AND_REGULAR(3),
    HOMONYM(4);

    private int code;

    private SensitiveValidateTypeEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static SensitiveValidateTypeEnum getByCode(int code) {
        SensitiveValidateTypeEnum[] var1 = values();
        int var2 = var1.length;

        for (int var3 = 0; var3 < var2; ++var3) {
            SensitiveValidateTypeEnum sensitiveValidateTypeEnum = var1[var3];
            if (sensitiveValidateTypeEnum.getCode() == code) {
                return sensitiveValidateTypeEnum;
            }
        }

        return null;
    }
}
