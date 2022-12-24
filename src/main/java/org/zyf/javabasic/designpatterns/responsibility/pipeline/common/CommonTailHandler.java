package org.zyf.javabasic.designpatterns.responsibility.pipeline.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.PipelineContext;

import java.time.LocalDateTime;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/21  16:10
 */
@Component
public class CommonTailHandler implements ContextHandler<PipelineContext, Object> {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object handle(PipelineContext context) {
        logger.info("管道执行完毕：管道名称为【{}】, context={}", context.getName(), context);
        /*设置处理结束时间*/
        context.setEndTime(LocalDateTime.now());
        return null;
    }
}
