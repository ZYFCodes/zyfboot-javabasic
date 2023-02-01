package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums;

/**
 * @author yanfengzhang
 * @description 校验商品敏感词信息归类
 * @date 2023/1/4  23:00
 */
public enum SensitiveValidateField {
    NAME(1, "商品名称"),
    DESCRIPTION(2, "商品描述"),
    TAG_NAME(3, "分类和分类描述"),
    SPEC(4, "规格名称"),
    ATTR_NAME(5, "属性和属性值"),
    COMBO_MEAL(6, "套餐"),
    SPECIAL_EFFECT_PIC(7, "商品图片特效"),
    CUSTOM_ATTR_NAME(8, "自定义属性"),
    ORDINARY_ATTR_NAME(9, "商品普通属性和属性值"),
    SP_NAME(10, "标品名称"),
    POI_NAME(11, "商家名称"),
    POI_ANNOUNCEMENT(12, "商家公告");

    /**
     * 文本归类（商品名称、商品描述、商家公告、商家名称、经营描述、代言信息等）
     */
    private Integer type;
    /**
     * 文本归类描述
     */
    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    SensitiveValidateField(Integer code) {
        this.type = code;
    }

    SensitiveValidateField(Integer code, String desc) {
        this.desc = desc;
        this.type = code;
    }

    /**
     * 根据文本归类type获取对应的文本归类信息
     *
     * @param type 文本归类type
     * @return
     */
    public static SensitiveValidateField getEnumById(Integer type) {
        for (SensitiveValidateField sensitiveValidateField : SensitiveValidateField.values()) {
            if (sensitiveValidateField.getType().equals(type)) {
                return sensitiveValidateField;
            }
        }
        return null;
    }

    /**
     * 根据文本归类信息描述获取对应的文本归类信息
     *
     * @param desc 文本归类描述
     * @return
     */
    public static SensitiveValidateField getEnumByDesc(String desc) {
        for (SensitiveValidateField sensitiveValidateField : SensitiveValidateField.values()) {
            if (sensitiveValidateField.getDesc().equals(desc)) {
                return sensitiveValidateField;
            }
        }
        return null;
    }

    /**
     * 判断文本归类type是否在指定范围
     *
     * @param type 文本归类type
     * @return true-在指定范围
     */
    public static boolean isSensitiveValidateField(Integer type) {
        if (null == type) {
            return false;
        }
        for (SensitiveValidateField tempEnum : SensitiveValidateField.values()) {
            if (tempEnum.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
