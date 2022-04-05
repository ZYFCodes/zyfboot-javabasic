package org.zyf.javabasic.designpatterns.responsibility.pipeline;

/**
 * @author yanfengzhang
 * @description 管道中的上下文处理器
 * T：管道中的上下文 R：管道中的上下文处理结果
 * @date 2022/4/2  20:48
 */

public interface ContextHandler<T extends PipelineContext, R> {
    /**
     * 处理输入的上下文数据
     *
     * @param context 处理时的上下文数据
     * @param dealRes 增加字段deliver为true则表示由下一个ContextHandler继续处理；为false则表示处理结束Content information
     */
    void handle(T context, R dealRes);
}
