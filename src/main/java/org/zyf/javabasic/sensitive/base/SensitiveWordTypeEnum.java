package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:41
 */
public enum SensitiveWordTypeEnum {
    NOT_CONTAIN(1),
    NOT_CONTAIN_BOTH(2);

    private final int value;

    private SensitiveWordTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SensitiveWordTypeEnum findByValue(int value) {
        switch (value) {
            case 1:
                return NOT_CONTAIN;
            case 2:
                return NOT_CONTAIN_BOTH;
            default:
                return null;
        }
    }
}
