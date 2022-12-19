package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.effect;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentAttr;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveEffectiveContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveHitContext;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 敏感词生效：生效规则处理
 * @date 2022/4/17  22:14
 */
@Component
public class EffectiveRuleProcess implements ContextHandler<SensitveHitContext, SensitveEffectiveContext> {

    /**
     * 实际生效规则处理是否可以放行
     * @param context 处理时的上下文数据
     * @return
     */
    @Override
    public SensitveEffectiveContext handle(SensitveHitContext context) {
        List<SensitiveWord> hitWords = context.getHitWords();
        /*如果未命中任何敏感词则直接返回*/
        if(CollectionUtils.isEmpty(hitWords)){
            return SensitveEffectiveContext.builder()
                    /*没有任何词生效*/
                    .isHit(false)
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
                    .hitWords(Lists.newArrayList())
                    .complianceIgnoreWords(context.getComplianceIgnoreWords())
                    .whitedWords(context.getWhitedWords())
                    .ruleIgnoreWords(context.getRuleIgnoreWords()).build();
        }

        /*此处只为模拟,根据当前命中的敏感词信息查询是否存在因规则而舍弃的,如果存在对放行的敏感词进行标志*/
        List<SensitiveWord> ignoreSensitiveWord = getIgnoreSensitiveWord(context.getHitWords(),context.getContentAttr());
        if(CollectionUtils.isEmpty(ignoreSensitiveWord)){
            /*没有需要放行的词，则当前词就是命中的，可直接返回*/
            return SensitveEffectiveContext.builder()
                    /*已经有生效的词直接返回*/
                    .isHit(true)
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
                    .hitWords(hitWords)
                    .complianceIgnoreWords(Lists.newArrayList())
                    .whitedWords(context.getWhitedWords())
                    .ruleIgnoreWords(context.getRuleIgnoreWords()).build();
        }

        /*整合敏感词配置规则放行的词*/
        List<Long> ignoreSensitiveWordIds = ignoreSensitiveWord.stream().map(SensitiveWord::getSensitiveId).collect(Collectors.toList());
        /*去除放行词后命中的词*/
        List<SensitiveWord> finalHitWords = hitWords.stream().filter(sensitiveWord -> !ignoreSensitiveWordIds.contains(sensitiveWord.getSensitiveId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(finalHitWords)) {
            /*此时链路可能还会记继续，保持对应内容的传递*/
            context.setHitWords(Lists.newArrayList());
            context.setRuleIgnoreWords(ignoreSensitiveWord);
            return SensitveEffectiveContext.builder()
                    /*已经有生效的词直接返回*/
                    .isHit(false)
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
                    .hitWords(Lists.newArrayList())
                    .complianceIgnoreWords(context.getComplianceIgnoreWords())
                    .whitedWords(context.getWhitedWords())
                    .ruleIgnoreWords(ignoreSensitiveWord).build();
        }

        /*返回当前命中的内容*/
        return SensitveEffectiveContext.builder()
                /*已经有生效的词直接返回*/
                .isHit(true)
                .content(context.getContent())
                .cleanContent(context.getCleanContent())
                .hitWords(finalHitWords)
                .complianceIgnoreWords(context.getComplianceIgnoreWords())
                .whitedWords(context.getWhitedWords())
                .ruleIgnoreWords(ignoreSensitiveWord).build();
    }

    private List<SensitiveWord> getIgnoreSensitiveWord(List<SensitiveWord> hitWords, ContentAttr contentAttr) {
        if (CollectionUtils.isEmpty(hitWords)) {
            return Lists.newArrayList();
        }

        return hitWords.stream().filter(sensitiveWord -> ignoreByrule(sensitiveWord,contentAttr)).collect(Collectors.toList());

    }

    private boolean ignoreByrule(SensitiveWord sensitiveWord,ContentAttr contentAttr){
        /*1.根据敏感词配置内容获取对应敏感词实际生效配置，此处做模拟*/
        if(contentAttr.getCityCode().equals("110010")){
            /*改词只对该地区生效,不忽略*/
            return false;
        }
        return true;
    }
}
