package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.effect;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.constants.SensitiveCons;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums.SensitiveEffect;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveEffectiveContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveHitContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 敏感词生效：白名单处理
 * @date 2022/4/17  22:20
 */
@Component
@SensitiveEffect(effectCode = SensitiveCons.Effect.WHITE)
public class WhitelistProcess implements ContextHandler<SensitveHitContext, SensitveEffectiveContext> {

    /**
     * 实际白名单处理是否可以放行
     *
     * @param context 处理时的上下文数据
     * @return 最终处理结果
     */
    @Override
    public SensitveEffectiveContext handle(SensitveHitContext context) {
        List<SensitiveWord> hitWords = context.getHitWords();
        /*如果未命中任何敏感词则直接返回*/
        if (CollectionUtils.isEmpty(hitWords)) {
            return SensitveEffectiveContext.builder()
                    /*没有任何词生效*/
                    .isHit(false)
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
                    .hitWords(Lists.newArrayList()).build();
        }

        /*此处只为模拟,根据当前命中的敏感词信息查询是否存在加白的词,如果存在对放行的敏感词进行标志*/
        List<SensitiveWord> ignoreSensitiveWord = getIgnoreSensitiveByWhite(context.getHitWords());
        if (CollectionUtils.isEmpty(ignoreSensitiveWord)) {
            /*没有需要放行的词，则当前词就是命中的，可直接返回*/
            return SensitveEffectiveContext.builder()
                    /*已经有生效的词直接返回*/
                    .isHit(true)
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
                    .hitWords(hitWords)
                    .whitedWords(Lists.newArrayList()).build();
        }

        /*整合敏感词配置规则放行的词*/
        List<Long> ignoreSensitiveWordIds = ignoreSensitiveWord.stream().map(SensitiveWord::getSensitiveId).collect(Collectors.toList());
        /*去除放行词后命中的词*/
        List<SensitiveWord> finalHitWords = hitWords.stream().filter(sensitiveWord -> !ignoreSensitiveWordIds.contains(sensitiveWord.getSensitiveId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(finalHitWords)) {
            return SensitveEffectiveContext.builder()
                    /*已经有生效的词直接返回*/
                    .isHit(false)
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
                    .hitWords(Lists.newArrayList())
                    .whitedWords(ignoreSensitiveWord).build();
        }

        /*此时链路可能还会记继续，保持对应内容的传递*/
        context.setHitWords(finalHitWords);
        /*返回当前命中的内容*/
        return SensitveEffectiveContext.builder()
                /*已经有生效的词直接返回*/
                .isHit(true)
                .content(context.getContent())
                .cleanContent(context.getCleanContent())
                .hitWords(finalHitWords)
                .whitedWords(ignoreSensitiveWord).build();
    }

    private List<SensitiveWord> getIgnoreSensitiveByWhite(List<SensitiveWord> hitWords) {
        if (CollectionUtils.isEmpty(hitWords)) {
            return Lists.newArrayList();
        }

        return hitWords.stream().filter(sensitiveWord -> ignoreByWhite(sensitiveWord)).collect(Collectors.toList());

    }

    private boolean ignoreByWhite(SensitiveWord sensitiveWord) {
        /*1.根据敏感词配置内容获取对应敏感词实际白名单生效配置，此处做模拟*/
        if (sensitiveWord.getSensitiveId() == 36) {
            return true;
        }
        return false;
    }
}
