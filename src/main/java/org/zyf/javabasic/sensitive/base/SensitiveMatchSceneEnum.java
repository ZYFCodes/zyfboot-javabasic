package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  19:38
 */
public enum SensitiveMatchSceneEnum {
    BEFORE(1),
    CENTER(2),
    AFTER(3);

    private final int value;

    private SensitiveMatchSceneEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SensitiveMatchSceneEnum findByValue(int value) {
        switch (value) {
            case 1:
                return BEFORE;
            case 2:
                return CENTER;
            case 3:
                return AFTER;
            default:
                return null;
        }
    }
}
