package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:30
 */
public enum SensitiveMerchantTypeEnum {
    ALL(0),
    QZ(1),
    NON_QZ(2);

    private final int value;

    private SensitiveMerchantTypeEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SensitiveMerchantTypeEnum findByValue(int value) {
        switch (value) {
            case 0:
                return ALL;
            case 1:
                return QZ;
            case 2:
                return NON_QZ;
            default:
                return null;
        }
    }
}

