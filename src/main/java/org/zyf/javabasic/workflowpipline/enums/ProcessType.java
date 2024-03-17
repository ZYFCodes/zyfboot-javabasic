package org.zyf.javabasic.workflowpipline.enums;

/**
 * @program: zyfboot-javabasic
 * @description: 加工处理方式
 * @author: zhangyanfeng
 * @create: 2024-02-13 19:35
 **/
public enum ProcessType {
    HIVE("HIVE", "离线处理"),

    FINAI("FINAI", "实时加工"),

    HIVE_FINAI("HIVE_FINAI", "算法处理后再次加工");

    ProcessType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private String code;

    private String desc;
}