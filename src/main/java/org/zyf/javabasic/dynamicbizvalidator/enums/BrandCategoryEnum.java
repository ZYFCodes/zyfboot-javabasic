package org.zyf.javabasic.dynamicbizvalidator.enums;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/26  23:54
 */
public enum BrandCategoryEnum {
    /**
     * 外卖
     */
    WM(1, "外卖门类"),
    /**
     * 闪购
     */
    SG(2, "闪购门类");

    private int value;
    private String desc;

    BrandCategoryEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return this.value;
    }

    public String getDesc() {
        return desc;
    }

    public static BrandCategoryEnum findByValue(int value) {
        switch (value) {
            case 1:
                return WM;
            case 2:
                return SG;
            default:
                return null;
        }
    }
}
