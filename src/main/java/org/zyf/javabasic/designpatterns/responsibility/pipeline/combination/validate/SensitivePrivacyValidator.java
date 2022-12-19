package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.validate;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveHitContext;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 敏感词校验：手机号身份证号处理
 * @date 2022/4/5  22:19
 */
@Component
public class SensitivePrivacyValidator implements ContextHandler<ContentCleanResContext, SensitveHitContext> {

    /**
     *  敏感词分析处理：手机号身份证号处理
     * @param context 处理时的上下文数据
     * @return
     */
    @Override
    public SensitveHitContext handle(ContentCleanResContext context) {
        List<SensitiveWord> hitWords = Lists.newArrayList();
        try {
            /*此处只为模拟*/
            hitWords.addAll(context.getHitWords());
            hitWords.addAll(getPrivacy(context.getCleanContent()));
            /*如果命中敏感词，则显示命中，且终止链路传递*/
            return SensitveHitContext.builder()
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
                    .deliver(true)
                    .hitWords(hitWords).build();
        } catch (Exception e) {
            /*此处只为模拟*/
            hitWords.addAll(context.getHitWords());
            /*如果命中敏感词，则显示命中，且终止链路传递*/
            return SensitveHitContext.builder()
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
                    .deliver(true)
                    .hitWords(hitWords).build();
        }
    }

    private List<SensitiveWord> getPrivacy(String content) {
        /*模拟*/
        List<SensitiveWord> sensitiveWords = Lists.newArrayList();
        if (content.contains("手机号身份证号处理")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("手机号身份证号处理")
                    .sensitiveId(23L)
                    .kind(4).build());
        }
        return sensitiveWords;
    }
}
