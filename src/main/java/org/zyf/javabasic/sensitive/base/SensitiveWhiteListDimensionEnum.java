package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/25  20:26
 */
public enum SensitiveWhiteListDimensionEnum {
    WM_POI(1),
    WM_POI_BRAND(2),
    PRODUCT_SP_TAG(3),
    PLATFORM_BRAND(4);

    private final int value;

    private SensitiveWhiteListDimensionEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static SensitiveWhiteListDimensionEnum findByValue(int value) {
        switch (value) {
            case 1:
                return WM_POI;
            case 2:
                return WM_POI_BRAND;
            case 3:
                return PRODUCT_SP_TAG;
            case 4:
                return PLATFORM_BRAND;
            default:
                return null;
        }
    }
}
