package org.zyf.javabasic.workflowpipline.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 值类型信息
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:36
 **/
public enum ValueTypeEnum {
    /**
     * 字符串
     */
    STRING("string", "字符串"),
    /**
     * 整型
     */
    INT("int", "整型"),
    /**
     * 长整型
     */
    LONG("long", "长整型"),
    /**
     * 浮点
     */
    DOUBLE("double", "浮点");

    private static final Map<String, ValueTypeEnum> ENUM_ID_MAP = Arrays.stream(ValueTypeEnum.values())
            .collect(Collectors.toMap(ValueTypeEnum::getCode, e -> e));

    private String code;
    private String desc;

    ValueTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static ValueTypeEnum queryValueType(String code) {
        if (StringUtils.isEmpty(code)) {
            return null;
        }

        for (ValueTypeEnum value : values()) {
            if (StringUtils.endsWithIgnoreCase(value.getCode(), code)) {
                return value;
            }
        }
        return null;
    }
}
