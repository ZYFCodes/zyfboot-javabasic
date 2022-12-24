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
 * @date 2022/12/21  15:59
 */
@Component
public class CommonHeadHandler implements ContextHandler<PipelineContext, Object> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object handle(PipelineContext context) {
        logger.info("管道开始执行：管道名称为【{}】, context={}", context.getName(), context);
        /*设置开始时间*/
        context.setStartTime(LocalDateTime.now());
        return null;
    }
}
