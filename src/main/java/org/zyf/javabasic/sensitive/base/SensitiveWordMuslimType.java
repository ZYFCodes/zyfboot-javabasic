package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:29
 */
public enum SensitiveWordMuslimType {
    MUSLIM_INVALID(-1),
    MUSLIM_IDENTIFY(0),
    NON_MUSLIM(1),
    MUSLIM_POI(2),
    MUSLIM_NON_POI(3);

    private final int value;

    private SensitiveWordMuslimType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SensitiveWordMuslimType findByValue(int value) {
        switch (value) {
            case -1:
                return MUSLIM_INVALID;
            case 0:
                return MUSLIM_IDENTIFY;
            case 1:
                return NON_MUSLIM;
            case 2:
                return MUSLIM_POI;
            case 3:
                return MUSLIM_NON_POI;
            default:
                return null;
        }
    }
}

