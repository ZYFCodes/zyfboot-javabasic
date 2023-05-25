package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:30
 */
public enum SensitiveRuleSceneEnum {
    SENSITIVE(0),
    QZ_IDEENTIFICATION(1);

    private final int value;

    private SensitiveRuleSceneEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SensitiveRuleSceneEnum findByValue(int value) {
        switch (value) {
            case 0:
                return SENSITIVE;
            case 1:
                return QZ_IDEENTIFICATION;
            default:
                return null;
        }
    }
}
