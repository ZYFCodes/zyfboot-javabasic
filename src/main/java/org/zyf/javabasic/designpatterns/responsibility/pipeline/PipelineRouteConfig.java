package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 管道路由表整合配置
 * @date 2022/4/17  22:24
 */

@Configuration
public class PipelineRouteConfig implements ApplicationContextAware {
    /**
     * 数据类型->管道中处理器类型列表 的路由
     */
    private static final Map<Class<? extends PipelineContext>,
            List<Class<? extends ContextHandler<? extends PipelineContext, ?>>>> PIPELINE_ROUTE_MAP = new HashMap<>(3);

    /**
     * 在这里配置各种上下文类型对应的处理管道：键为上下文类型，值为处理器类型的列表
     */
    static {
        /*数据清洗*/
        PIPELINE_ROUTE_MAP.put(ContentInfoContext.class, Arrays.asList(TraditionalSimplifiedConversion.class, RemoveSpecialSymbols.class,
                RemoveEmoji.class, ExcludeHiddenCharacters.class, LanguageDistinguishLimit.class));

        /*敏感词校验*/
        PIPELINE_ROUTE_MAP.put(SensitveHitContext.class, Arrays.asList(SensitiveComplianceValidator.class, SensitiveThesaurusValidator.class,
                SensitiveRegularValidator.class, SensitivePrivacyValidator.class));

        /*敏感词生效*/
        PIPELINE_ROUTE_MAP.put(SensitveEffectiveContext.class, Arrays.asList(EffectiveRuleProcess.class, ComplianceControlProcess.class,
                WhitelistProcess.class));
    }

    private ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    /**
     * 在 Spring 启动时，根据路由表生成对应的管道映射关系
     */
    @Bean("pipelineRouteMap")
    public Map<Class<? extends PipelineContext>, List<? extends ContextHandler<? extends PipelineContext, ?>>> getHandlerPipelineMap() {
        return PIPELINE_ROUTE_MAP.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, this::toPipeline));
    }

    /**
     * 根据给定的管道中 ContextHandler 的类型的列表，构建管道
     */
    private List<? extends ContextHandler<? extends PipelineContext, ?>> toPipeline(Map.Entry<Class<? extends PipelineContext>,
            List<Class<? extends ContextHandler<? extends PipelineContext, ?>>>> entry) {
        return entry.getValue()
                .stream()
                .map(appContext::getBean)
                .collect(Collectors.toList());
    }
}
