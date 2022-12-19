package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author yanfengzhang
 * @description 管道的上下文基本结构
 * @date 2022/4/2  20:43
 */

@Getter
@Setter
public class PipelineContext {
    /**
     * 模板名称
     */
    private String name;
    /**
     * 处理开始时间
     */
    private LocalDateTime startTime;
    /**
     * 处理结束时间
     */
    private LocalDateTime endTime;

    /**
     * 获取数据名称
     */
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
