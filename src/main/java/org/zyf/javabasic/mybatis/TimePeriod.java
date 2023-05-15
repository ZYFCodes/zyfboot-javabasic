package org.zyf.javabasic.mybatis;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/14  10:58
 */
@Data
public class TimePeriod {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public TimePeriod(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
