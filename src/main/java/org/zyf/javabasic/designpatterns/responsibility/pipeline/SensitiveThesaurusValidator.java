package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 敏感词校验：相关词库处理
 * @date 2022/4/5  16:31
 */
@Component
public class SensitiveThesaurusValidator implements ContextHandler<SensitveHitContext, SensitveResContext> {
    /**
     * 敏感词分析处理：根据相关业务配置进行相关词库校验匹配
     *
     * @param context 处理时的上下文数据
     * @param dealRes 增加字段deliver为true则表示由下一个ContextHandler继续处理；为false则表示处理结束Content information
     */
    @Override
    public void handle(SensitveHitContext context, SensitveResContext dealRes) {
        Integer bizType = context.getBizType();
        /*根据业务方接入来源获取对应的业务方词库校验要求*/
        List<Integer> validatorModes = getBizSensitiveModes(bizType);
        if (CollectionUtils.isEmpty(validatorModes)) {
            /*没有配置则直接默认走企业词库校验*/
            validatorModes.add(1);
        }

        /*实际用策略处理，此处只为模拟*/
        List<SensitiveWord> hitWords = Lists.newArrayList();
        for (Integer validatorMode : validatorModes) {
            if (validatorMode.equals(1)) {
                /*企业词库校验*/
                hitWords.addAll(companySensitive(context.getCleanContent()));
            }
            if (validatorMode.equals(2)) {
                /*企业词库校验*/
                hitWords.addAll(departmentSensitive(context.getCleanContent()));
            }
            if (validatorMode.equals(3)) {
                /*企业词库校验*/
                hitWords.addAll(otherSensitive(context.getCleanContent()));
            }
        }

        if (CollectionUtils.isNotEmpty(hitWords)) {
            dealRes.setDeliver(true);
            dealRes.setHitWords(hitWords);
            dealRes.setIsHit(true);
        } else {
            dealRes.setDeliver(false);
            dealRes.setReason("敏感词校验结束：当前词库中没有命中敏感词");
        }
    }

    /**
     * 模拟假设配置的都是所有词库都跑一遍
     *
     * @return 业务方词库配置
     */
    private List<Integer> getBizSensitiveModes(Integer bizType) {
        /**
         * 1-企业词库校验；2-部门词库校验；3-其他词库校验
         */
        return Arrays.asList(1, 2, 3);
    }

    /**
     * 企业敏感词库（编号1）匹配,实际为词库校验，此处只做模拟
     *
     * @param content 用户内容
     * @return 匹配结果
     */
    private List<SensitiveWord> companySensitive(String content) {
        List<SensitiveWord> sensitiveWords = Lists.newArrayList();
        if (content.contains("张彦峰")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("张彦峰")
                    .sensitiveId(1L)
                    .kind(1).build());
        }
        if (content.contains("张峰峰")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("张峰峰")
                    .sensitiveId(86L)
                    .kind(1).build());
        }
        return sensitiveWords;
    }

    /**
     * 部门敏感词库（编号2）匹配,实际为不同部门的词库校验，此处只做模拟
     *
     * @param content 用户内容
     * @return 匹配结果
     */
    private List<SensitiveWord> departmentSensitive(String content) {
        List<SensitiveWord> sensitiveWords = Lists.newArrayList();
        if (content.contains("妓院")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("妓院")
                    .sensitiveId(23L)
                    .kind(2).build());
        }
        if (content.contains("赌博")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("赌博")
                    .sensitiveId(36L)
                    .kind(2).build());
        }
        return sensitiveWords;
    }

    /**
     * 其他敏感词库（编号3）匹配,实际为不同第三方词库校验，此处只做模拟
     *
     * @param content 用户内容
     * @return 匹配结果
     */
    private List<SensitiveWord> otherSensitive(String content) {
        List<SensitiveWord> sensitiveWords = Lists.newArrayList();
        if (content.contains("禁药")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("禁药")
                    .sensitiveId(28L)
                    .kind(3).build());
        }
        if (content.contains("毒品")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("毒品")
                    .sensitiveId(376L)
                    .kind(3).build());
        }
        return sensitiveWords;
    }
}
