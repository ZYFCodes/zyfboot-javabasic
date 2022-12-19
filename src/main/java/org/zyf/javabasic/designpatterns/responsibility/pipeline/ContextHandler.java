package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.PipelineContext;

/**
 * @author yanfengzhang
 * @description 管道中的上下文处理器
 * T：管道中的上下文 R：管道中的上下文处理结果
 * @date 2022/4/2  20:48
 */

public interface ContextHandler<T extends PipelineContext, R> {
    /**
     * @param context 处理时的上下文数据
     * @return dealRes R 处理结果
     */
    R handle(T context);
}
