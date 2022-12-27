package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.PipelineRouteConfig;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentInfoContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveEffectiveContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveHitContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.common.CommonHeadHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.common.CommonTailHandler;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 敏感词管道执行器
 * @date 2022/12/21  16:49
 */
@Component
@Log4j2
public class SensitivePipelineExecutor {
    @Autowired
    private CommonHeadHandler commonHeadHandler;
    @Autowired
    private CommonTailHandler commonTailHandler;

    public String getSensitiveDealRes(ContentInfoContext contentInfoContext) {
        StringBuilder sensitiveDealRes = new StringBuilder();
        sensitiveDealRes.append("用户文本【").append(contentInfoContext.getContent()).append("】");
        /*1.检查请求内容的合法性*/
        /*2.获取对应请求的文本清洗结果*/
        ContentCleanResContext contentCleanResContext = getContentCleanRes(contentInfoContext);
        if (!contentCleanResContext.isCleanDone()) {
            sensitiveDealRes.append("内部清洗处理异常：").append("具体原因为【").append(contentCleanResContext.getReason()).append("】");
            return sensitiveDealRes.toString();
        }

        /*3.整合相关的校验能力*/
        SensitveHitContext sensitveHitContext = getSensitveHitRes(contentCleanResContext);
        if (!sensitveHitContext.getHasHit()) {
            sensitiveDealRes.append("无任何敏感词命中");
            return sensitiveDealRes.toString();
        }
        /*4.整合最终的命中结果*/
        SensitveEffectiveContext sensitveEffectiveContext = getSensitveHitRes(sensitveHitContext);
        if (sensitveEffectiveContext.getIsHit()) {
            sensitiveDealRes.append("命中相关敏感词：").append("【").append(sensitveEffectiveContext.getHitWords().stream().map(SensitiveWord::getSensitive).collect(Collectors.toList())).append("】");
            return sensitiveDealRes.toString();
        }

        sensitiveDealRes.append("无任何敏感词命中");
        return sensitiveDealRes.toString();
    }

    /**
     * 根据用户文本获取对应数据清洗结果内容
     *
     * @param contentInfoContext 用户文本内容
     * @return 数据清洗结果
     */
    public ContentCleanResContext getContentCleanRes(ContentInfoContext contentInfoContext) {
        /*【通用头处理器】处理*/
        commonHeadHandler.handle(contentInfoContext);
        ContentCleanResContext contentCleanResContext = null;
        for (Integer cleanCode : PipelineRouteConfig.getContentCleanProcessor().keySet()) {
            contentCleanResContext = (ContentCleanResContext) PipelineRouteConfig.getInstance(cleanCode).handle(contentInfoContext);
            if (Objects.isNull(contentCleanResContext)) {
                continue;
            }
            if (!contentCleanResContext.isCleanDone()) {
                /*【通用尾处理器】处理*/
                commonTailHandler.handle(contentInfoContext);
                return contentCleanResContext;
            }
        }
        /*【通用尾处理器】处理*/
        commonTailHandler.handle(contentInfoContext);
        return contentCleanResContext;
    }

    /**
     * 根据用户数据清洗结果获取相关词库命中情况
     *
     * @param contentCleanResContext 数据清洗结果
     * @return 词库命中情况
     */
    public SensitveHitContext getSensitveHitRes(ContentCleanResContext contentCleanResContext) {
        /*【通用头处理器】处理*/
        commonHeadHandler.handle(contentCleanResContext);
        SensitveHitContext sensitveHitContext = null;
        List<SensitiveWord> hitWords = Lists.newArrayList();
        for (Integer validateCode : PipelineRouteConfig.getSensitiveValidateProcessor().keySet()) {
            sensitveHitContext = (SensitveHitContext) PipelineRouteConfig.getInstance(validateCode).handle(contentCleanResContext);
            if (Objects.isNull(sensitveHitContext)) {
                continue;
            }
            hitWords.addAll(sensitveHitContext.getHitWords());
        }
        /*【通用尾处理器】处理*/
        commonTailHandler.handle(contentCleanResContext);

        if (Objects.isNull(sensitveHitContext)) {
            return SensitveHitContext.builder().hasHit(false).build();
        }

        /*根据统计词库信息决定最后的词库结果*/
        if (CollectionUtils.isNotEmpty(hitWords)) {
            sensitveHitContext.setHasHit(true);
            sensitveHitContext.setHitWords(hitWords);
        }
        return sensitveHitContext;
    }

    /**
     * 获取最终返回的命中结果
     *
     * @param sensitveHitContext 词库命中结果
     * @return 经过其他分析后最终命中结果
     */
    public SensitveEffectiveContext getSensitveHitRes(SensitveHitContext sensitveHitContext) {
        /*【通用头处理器】处理*/
        commonHeadHandler.handle(sensitveHitContext);

        SensitveEffectiveContext sensitveEffectiveContext = null;
        List<SensitiveWord> whitedWords = Lists.newArrayList();
        List<SensitiveWord> complianceIgnoreWords = Lists.newArrayList();
        List<SensitiveWord> ruleIgnoreWords = Lists.newArrayList();
        for (Integer effectCode : PipelineRouteConfig.getSensitiveEffectProcessor().keySet()) {
            sensitveEffectiveContext = (SensitveEffectiveContext) PipelineRouteConfig.getInstance(effectCode).handle(sensitveHitContext);
            if (Objects.isNull(sensitveHitContext)) {
                continue;
            }
            if (CollectionUtils.isNotEmpty(sensitveEffectiveContext.getComplianceIgnoreWords())) {
                complianceIgnoreWords.addAll(sensitveEffectiveContext.getComplianceIgnoreWords());
            }
            if (CollectionUtils.isNotEmpty(sensitveEffectiveContext.getRuleIgnoreWords())) {
                ruleIgnoreWords.addAll(sensitveEffectiveContext.getRuleIgnoreWords());
            }
            if (CollectionUtils.isNotEmpty(sensitveEffectiveContext.getWhitedWords())) {
                whitedWords.addAll(sensitveEffectiveContext.getWhitedWords());
            }
            /*如果已经没有命中的词了则直接返回即可*/
            if (!sensitveEffectiveContext.getIsHit()) {
                sensitveEffectiveContext.setWhitedWords(whitedWords);
                sensitveEffectiveContext.setComplianceIgnoreWords(complianceIgnoreWords);
                sensitveEffectiveContext.setRuleIgnoreWords(ruleIgnoreWords);
                return sensitveEffectiveContext;
            }
        }
        /*【通用尾处理器】处理*/
        commonTailHandler.handle(sensitveHitContext);

        sensitveEffectiveContext.setWhitedWords(whitedWords);
        sensitveEffectiveContext.setComplianceIgnoreWords(complianceIgnoreWords);
        sensitveEffectiveContext.setRuleIgnoreWords(ruleIgnoreWords);
        return sensitveEffectiveContext;
    }

}
