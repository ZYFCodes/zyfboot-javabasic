package org.zyf.javabasic.workflowpipline.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 处理类型
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:32
 **/
public enum OrgTypeEnum {
    /**
     * 单值
     */
    SINGLE("single", "单值"),
    /**
     * 多值
     */
    MULTI("multi", "多值"),
    /**
     * KV
     */
    KV("kv", "KV"),
    /**
     * KKV
     */
    KKV("kkv", "KKV");

    private String code;
    private String desc;

    private static final Map<String, OrgTypeEnum> ENUM_ID_MAP = Arrays.stream(OrgTypeEnum.values())
            .collect(Collectors.toMap(OrgTypeEnum::getCode, e -> e));

    OrgTypeEnum(String code, String desc) {
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


    public static String queryOrgType(String name) {

        if (StringUtils.isEmpty(name)) {
            return null;
        }

        for (OrgTypeEnum value : values()) {
            if (StringUtils.endsWithIgnoreCase(value.name(), name)) {
                return value.getCode();
            }
        }

        return null;
    }

    public static OrgTypeEnum getByName(final String name) {

        return ENUM_ID_MAP.get(name);
    }
}