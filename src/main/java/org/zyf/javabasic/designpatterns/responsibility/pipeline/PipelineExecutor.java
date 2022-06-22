package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author yanfengzhang
 * @description 管道执行器
 * @date 2022/4/17  22:38
 */
@Component
@Log4j2
public class PipelineExecutor {
    /**
     * 引用 PipelineRouteConfig 中的 pipelineRouteMap
     */
    @Resource
    private Map<Class<? extends PipelineContext>, List<Class<? extends ContextHandler<? extends PipelineContext, ?>>>> pipelineRouteMap;

    /**
     * 同步处理输入的上下文数据<br/>
     * 如果处理时上下文数据流通到最后一个处理器且最后一个处理器返回 true，则返回 true，否则返回 false
     *
     * @param context 输入的上下文数据
     * @return 处理过程中管道是否畅通，畅通返回 true，不畅通返回 false
     */
    public boolean acceptSync(PipelineContext context) {
        Objects.requireNonNull(context, "上下文数据不能为 null");
        // 拿到数据类型
        Class<? extends PipelineContext> dataType = context.getClass();
        // 获取数据处理管道
        List<Class<? extends ContextHandler<? extends PipelineContext, ?>>> pipeline = pipelineRouteMap.get(dataType);
        if (CollectionUtils.isEmpty(pipeline)) {
            log.error("{} 的管道为空", dataType.getSimpleName());
            return false;
        }

        // 管道是否畅通
        boolean lastSuccess = true;

        for (Class<? extends ContextHandler<? extends PipelineContext, ?>> handler : pipeline) {
            try {
                Class<?>[] classes = handler.getClasses();


                // 当前处理器处理数据，并返回是否继续向下处理
                //lastSuccess = handler.handle(context,);
            } catch (Throwable ex) {
                lastSuccess = false;
                log.error("[{}] 处理异常，handler={}", context.getName(), handler.getClass().getSimpleName(), ex);
            }


            // 不再向下处理
            if (!lastSuccess) {
                break;
            }
        }


        return lastSuccess;
    }
}
