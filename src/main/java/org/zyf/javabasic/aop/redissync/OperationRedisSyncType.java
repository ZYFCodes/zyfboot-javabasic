package org.zyf.javabasic.aop.redissync;

import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 异步同步redis类型
 * @date 2022/7/18  23:44
 */
public enum OperationRedisSyncType {
    CREATE(1, "新增"),
    UPDATE(2, "修改"),
    DELETE(3, "删除");

    /**
     * 异步同步redis类型
     */
    private Integer type;
    /**
     * 异步同步redis类型描述
     */
    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    OperationRedisSyncType(Integer type) {
        this.type = type;
    }

    OperationRedisSyncType(Integer type, String desc) {
        this.desc = desc;
        this.type = type;
    }

    /**
     * 根据异步同步redis类型处理code获取对应的枚举
     *
     * @param code 异步同步redis类型处理code
     * @return
     */
    public static OperationRedisSyncType getEnumById(Integer code) {
        for (OperationRedisSyncType operationRedisSyncType : OperationRedisSyncType.values()) {
            if (Objects.equals(operationRedisSyncType.getType(), code)) {
                return operationRedisSyncType;
            }
        }
        return null;
    }

    /**
     * 根据异步同步redis类型处理描述获取对应的枚举
     *
     * @param desc 异步同步redis类型处理描述
     * @return
     */
    public static OperationRedisSyncType getEnumByDesc(String desc) {
        for (OperationRedisSyncType operationRedisSyncType : OperationRedisSyncType.values()) {
            if (operationRedisSyncType.getDesc().equals(desc)) {
                return operationRedisSyncType;
            }
        }
        return null;
    }

    /**
     * 判断异步同步redis类型code是否在指定范围
     *
     * @param code 异步同步redis类型处理code
     * @return true-在指定范围
     */
    public static boolean isOperationRedisSyncType(Integer code) {
        if (null == code) {
            return false;
        }
        for (OperationRedisSyncType tempEnum : OperationRedisSyncType.values()) {
            if (Objects.equals(tempEnum.getType(), code)) {
                return true;
            }
        }
        return false;
    }
}
