package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 敏感词生效：生效规则处理
 * @date 2022/4/17  22:14
 */
@Component
public class EffectiveRuleProcess implements ContextHandler<SensitveEffectiveContext, SensitiveEffectRes> {
    /**
     * 实际生效规则处理是否可以放行
     *
     * @param context  处理时的上下文数据:增加字段deliver为true则表示由下一个ContextHandler继续处理；为false则表示处理结束Content information
     * @param nextDeal 最终处理结果
     */
    @Override
    public void handle(SensitveEffectiveContext context, SensitiveEffectRes nextDeal) {
        /*前置节点处理异常，本节点不做处理（所以初始化传入的时候需要进行默认true，方便后期随时调整链路）*/
        if (!context.getDeliver()) {
            return;
        }

        try {
            /*此处只为模拟,根据当前命中的敏感词信息查询是否存在对应的生效规则策略,如果存在对放行的敏感词进行标志*/
            List<SensitiveWord> hitWords = getIgnoreSensitiveWord(context.getHitWords());
            List<SensitiveWord> hitWordsExcluded = hitWords.stream().filter(sensitiveWord -> !sensitiveWord.getHitRule()).collect(Collectors.toList());
            /*如果去除放行的敏感词依旧存在相关敏感词，则继续继续并进行下一步*/
            if (CollectionUtils.isNotEmpty(hitWordsExcluded)) {
                context.setIsHit(true);
                context.setHitWords(hitWords);
                context.setDeliver(true);
            } else {
                context.setDeliver(false);
                context.setReason("敏感词生效规则处理结束：已在生效规则处理中进行分析处理，本次命中敏感词全部生效");
            }
            BeanUtils.copyProperties(context, nextDeal);
        } catch (Exception e) {
            context.setDeliver(false);
            context.setReason("敏感词生效规则处理结束：相关企业合规管控校验处理过程中发生异常");
            BeanUtils.copyProperties(context, nextDeal);
        }
    }

    private List<SensitiveWord> getIgnoreSensitiveWord(List<SensitiveWord> hitWords) {
        if (CollectionUtils.isEmpty(hitWords)) {
            return Lists.newArrayList();
        }
        List<SensitiveWord> ignoreSensitiveWord = Arrays.asList(SensitiveWord.builder().sensitive("张彦峰").build(),
                SensitiveWord.builder().sensitive("张靓颖").build());
        List<String> ignoreSensitiveWordInfo = ignoreSensitiveWord.stream().map(SensitiveWord::getSensitive).collect(Collectors.toList());
        return hitWords.stream().peek(sensitiveWord -> {
            if (ignoreSensitiveWordInfo.contains(sensitiveWord.getSensitive())) {
                sensitiveWord.setHitRule(true);
            }
        }).collect(Collectors.toList());
    }
}
