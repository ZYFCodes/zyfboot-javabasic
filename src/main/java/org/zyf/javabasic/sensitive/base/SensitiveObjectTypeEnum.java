package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:25
 */
public enum SensitiveObjectTypeEnum {
    SENSITIVE_WORD(1),
    SENSITIVE_CATEGORY(2);

    private final int value;

    private SensitiveObjectTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SensitiveObjectTypeEnum findByValue(int value) {
        switch (value) {
            case 1:
                return SENSITIVE_WORD;
            case 2:
                return SENSITIVE_CATEGORY;
            default:
                return null;
        }
    }
}
