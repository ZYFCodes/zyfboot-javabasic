package org.zyf.javabasic.dispatcher.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.zyf.javabasic.dispatcher.enums.TimingType;

/**
 * @program: zyfboot-javabasic
 * @description: 表示数据分发的时机配置
 * @author: zhangyanfeng
 * @create: 2024-04-21 17:18
 **/
@Data
@AllArgsConstructor
public class DispatchTiming {
    /**
     * 分发时机类型
     */
    private TimingType timingType;
    /**
     * 定时分发的时间，格式为 yyyy-MM-dd HH:mm:ss
     */
    private String scheduledTime;

    /**
     * 创建一个立即分发的 DispatchTiming 实例。
     *
     * @return 立即分发的 DispatchTiming 实例
     */
    public static DispatchTiming immediate() {
        return new DispatchTiming(TimingType.IMMEDIATE, null);
    }

    /**
     * 创建一个定时分发的 DispatchTiming 实例。
     *
     * @param scheduledTime 定时分发的时间，格式为 yyyy-MM-dd HH:mm:ss
     * @return 定时分发的 DispatchTiming 实例
     */
    public static DispatchTiming scheduled(String scheduledTime) {
        return new DispatchTiming(TimingType.SCHEDULED, scheduledTime);
    }
}