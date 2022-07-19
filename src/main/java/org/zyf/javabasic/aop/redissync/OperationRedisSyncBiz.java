package org.zyf.javabasic.aop.redissync;

import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 异步同步redis业务类型
 * @date 2022/7/18  23:05
 */
public enum OperationRedisSyncBiz {
    SENSITIVE(1, "敏感词业务"),
    OVERRANGE(2, "超范围资质业务"),
    MEMBER_CHANNEL(3, "会员渠道业务"),
    RED_PACKAGE(4, "红包发布业务");

    /**
     * 异步同步redis业务类型：0-敏感词业务；1-超范围资质业务；2-会员渠道业务；3-红包发布业务
     */
    private Integer type;
    /**
     * 异步同步redis业务类型描述
     */
    private String desc;

    public Integer getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    OperationRedisSyncBiz(Integer type) {
        this.type = type;
    }

    OperationRedisSyncBiz(Integer type, String desc) {
        this.desc = desc;
        this.type = type;
    }

    /**
     * 根据异步同步redis业务类型处理code获取对应的枚举
     *
     * @param code 异步同步redis业务类型处理code
     * @return
     */
    public static OperationRedisSyncBiz getEnumById(Integer code) {
        for (OperationRedisSyncBiz operationRedisSyncBiz : OperationRedisSyncBiz.values()) {
            if (Objects.equals(operationRedisSyncBiz.getType(), code)) {
                return operationRedisSyncBiz;
            }
        }
        return null;
    }

    /**
     * 根据异步同步redis业务类型处理描述获取对应的枚举
     *
     * @param desc 异步同步redis业务类型处理描述
     * @return
     */
    public static OperationRedisSyncBiz getEnumByDesc(String desc) {
        for (OperationRedisSyncBiz operationRedisSyncBiz : OperationRedisSyncBiz.values()) {
            if (operationRedisSyncBiz.getDesc().equals(desc)) {
                return operationRedisSyncBiz;
            }
        }
        return null;
    }

    /**
     * 判断异步同步redis业务类型code是否在指定范围
     *
     * @param code 异步同步redis业务类型处理code
     * @return true-在指定范围
     */
    public static boolean isOperationRedisSyncBiz(Integer code) {
        if (null == code) {
            return false;
        }
        for (OperationRedisSyncBiz tempEnum : OperationRedisSyncBiz.values()) {
            if (Objects.equals(tempEnum.getType(), code)) {
                return true;
            }
        }
        return false;
    }
}
