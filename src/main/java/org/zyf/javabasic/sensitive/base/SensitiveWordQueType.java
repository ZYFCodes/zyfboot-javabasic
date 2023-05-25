package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  21:04
 */
public enum SensitiveWordQueType {
    NEED(0),
    NONNEED(1);

    private final int value;

    private SensitiveWordQueType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SensitiveWordQueType findByValue(int value) {
        switch (value) {
            case 0:
                return NEED;
            case 1:
                return NONNEED;
            default:
                return null;
        }
    }
}
