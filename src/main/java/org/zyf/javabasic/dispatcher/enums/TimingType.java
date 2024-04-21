package org.zyf.javabasic.dispatcher.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @program: zyfboot-javabasic
 * @description: 分发时机类型枚举
 * @author: zhangyanfeng
 * @create: 2024-04-21 17:15
 **/
@Getter
@AllArgsConstructor
public enum TimingType {
    IMMEDIATE(1), // 立即分发
    SCHEDULED(2); // 定时分发

    private final int value;

    /**
     * 根据给定的数值返回对应的 TimingType 枚举类型。
     * 如果给定的数值不匹配任何枚举类型，则返回 null。
     *
     * @param value 要匹配的数值
     * @return 匹配的 TimingType 枚举类型，或者 null
     */
    public static TimingType fromValue(int value) {
        for (TimingType type : TimingType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        return null;
    }
}