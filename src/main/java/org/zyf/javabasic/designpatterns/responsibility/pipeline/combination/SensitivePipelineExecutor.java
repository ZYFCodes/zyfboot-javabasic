package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination;

import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.PipelineExecutor;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums.SensitiveClean;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentInfoContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.common.CommonHeadHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.common.CommonTailHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description 敏感词管道执行器
 * @date 2022/12/21  16:49
 */
@Component
@Log4j2
public class SensitivePipelineExecutor {

    private static Map<Integer, Class> contentCleanProcessor = Maps.newHashMap();
    private static Map<Integer, Class> sensitiveValidateProcessor = Maps.newHashMap();
    private static Map<Integer, Class> sensitiveEffectProcessor = Maps.newHashMap();
    private static final Logger LOGGER = LoggerFactory.getLogger(SensitivePipelineExecutor.class);

    static {
        /*1.数据清洗能力集合*/
        Reflections contentCleanRef = new Reflections("org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.dataclean");
        Set<Class<?>> contentCleanClassSet = contentCleanRef.getTypesAnnotatedWith(SensitiveClean.class);
        for (Class<?> cl : contentCleanClassSet) {
            Annotation[] annotations = cl.getAnnotations();
            for (Annotation a : annotations) {
                if (a instanceof SensitiveClean) {
                    SensitiveClean sensitiveClean = (SensitiveClean) a;
                    contentCleanProcessor.put(sensitiveClean.cleanCode(), cl);
                }
            }
        }
        /*2.敏感词校验能力集合*/
        Reflections sensitiveValidatenRef = new Reflections("org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.dataclean");
        Set<Class<?>> sensitiveValidateClassSet = sensitiveValidatenRef.getTypesAnnotatedWith(SensitiveClean.class);
        for (Class<?> cl : sensitiveValidateClassSet) {
            Annotation[] annotations = cl.getAnnotations();
            for (Annotation a : annotations) {
                if (a instanceof SensitiveClean) {
                    SensitiveClean sensitiveClean = (SensitiveClean) a;
                    contentCleanProcessor.put(sensitiveClean.cleanCode(), cl);
                }
            }
        }
        /*3.敏感词生效能力集合*/
        Reflections sensitiveEffectRef = new Reflections("org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.dataclean");
        Set<Class<?>> sensitiveEffectClassSet = sensitiveEffectRef.getTypesAnnotatedWith(SensitiveClean.class);
        for (Class<?> cl : sensitiveEffectClassSet) {
            Annotation[] annotations = cl.getAnnotations();
            for (Annotation a : annotations) {
                if (a instanceof SensitiveClean) {
                    SensitiveClean sensitiveClean = (SensitiveClean) a;
                    contentCleanProcessor.put(sensitiveClean.cleanCode(), cl);
                }
            }
        }
    }

    @Autowired
    private PipelineExecutor pipelineExecutor;
    @Autowired
    private CommonHeadHandler commonHeadHandler;
    @Autowired
    private CommonTailHandler commonTailHandler;

    /**
     * 根据用户文本获取对应数据清洗结果内容
     *
     * @param contentInfoContext
     * @return
     */
    public ContentCleanResContext getContentCleanRes(ContentInfoContext contentInfoContext) {
        /*【通用头处理器】处理*/
        commonHeadHandler.handle(contentInfoContext);
        ContentCleanResContext contentCleanResContext =null;
        for (Integer cleanCode : contentCleanProcessor.keySet()) {
            contentCleanResContext = (ContentCleanResContext) getInstance(cleanCode).handle(contentInfoContext);
            if(!contentCleanResContext.isCleanDone()){
                /*【通用尾处理器】处理*/
                commonTailHandler.handle(contentInfoContext);
                return contentCleanResContext;
            }
        }
        /*【通用尾处理器】处理*/
        commonTailHandler.handle(contentInfoContext);
        return contentCleanResContext;
    }


    private static ContextHandler getInstance(int cleanCode) {
        if (contentCleanProcessor.containsKey(cleanCode)) {
            try {
                Constructor constructor = contentCleanProcessor.get(cleanCode).getConstructor();
                return (ContextHandler) constructor.newInstance();
            } catch (Exception ex) {
                LOGGER.error("ContextHandler dispatcher getInstance error, exception:", ex);
            }
        }
        return null;
    }
}
