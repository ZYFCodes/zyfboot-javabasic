package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 敏感词校验：企业合规管控校验
 * @date 2022/4/5  18:15
 */
@Component
public class SensitiveComplianceValidator implements ContextHandler<SensitveHitContext, SensitveEffectiveContext> {
    /**
     * 敏感词分析处理：根据相关企业业务配置进行处理
     *
     * @param context  处理时的上下文数据：增加字段deliver为true则表示由下一个ContextHandler继续处理；为false则表示处理结束Content information
     * @param nextDeal 处理结果（代进入敏感词生效处理）
     */
    @Override
    public void handle(SensitveHitContext context, SensitveEffectiveContext nextDeal) {
        /*前置节点处理异常，本节点不做处理（所以初始化传入的时候需要进行默认true，方便后期随时调整链路）*/
        if (!context.getDeliver()) {
            return;
        }

        try {
            /*此处只为模拟*/
            List<SensitiveWord> hitWords = corporateCompliance(context.getContent());
            /*如果命中敏感词，则显示命中，且终止链路传递*/
            if (CollectionUtils.isNotEmpty(hitWords)) {
                context.setIsHit(true);
                context.setHitWords(hitWords);
                context.setDeliver(false);
                context.setReason("敏感词校验结束：已在企业合规管控校验处理中命中敏感词");
            } else {
                context.setDeliver(true);
            }
            BeanUtils.copyProperties(context, nextDeal);
        } catch (Exception e) {
            context.setDeliver(false);
            context.setReason("敏感词校验结束：相关企业合规管控校验处理过程中发生异常");
            BeanUtils.copyProperties(context, nextDeal);
        }
    }

    /**
     * 企业合规管控词库   实际应与企业对应词库进行匹配
     *
     * @param content 文本内容
     * @return 校验结果
     */
    private List<SensitiveWord> corporateCompliance(String content) {
        List<SensitiveWord> sensitiveWords = Lists.newArrayList();
        if (content.contains("张彦峰企业词库")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("张彦峰企业词库")
                    .sensitiveId(23L)
                    .kind(4).build());
        }
        return sensitiveWords;
    }
}
