package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.validate;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.BizType;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentAttr;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveHitContext;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 敏感词校验：企业合规管控校验
 * @date 2022/4/5  18:15
 */
@Component
public class SensitiveComplianceValidator implements ContextHandler<ContentCleanResContext, SensitveHitContext> {

    /**
     * 敏感词分析处理：根据相关企业业务配置进行处理
     *
     * @param context 处理时的上下文数据
     * @return 处理结果（代进入敏感词生效处理）
     */
    @Override
    public SensitveHitContext handle(ContentCleanResContext context) {
        List<SensitiveWord> hitWords = Lists.newArrayList();
        try {
            /*此处只为模拟*/
            hitWords.addAll(context.getHitWords());
            hitWords.addAll(corporateCompliance(context));
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

    /**
     * 企业合规管控词库   实际应与企业对应词库进行匹配
     *
     * @param content 文本内容
     * @return 校验结果
     */
    private List<SensitiveWord> corporateCompliance(ContentCleanResContext content) {
        /*该词库只对某些业务进行校验,此处应设置成配置选项*/
        List<BizType> bizTypes = Lists.newArrayList(BizType.E_COMMERCE, BizType.LOGISTICS, BizType.ENTERTAINMENT, BizType.TAKEAWAY, BizType.FALASH_SALE, BizType.MREDICINE);
        ContentAttr contentAttr = content.getContentAttr();
        if (!bizTypes.contains(BizType.getEnumById(contentAttr.getBizType()))) {
            return Lists.newArrayList();
        }
        List<SensitiveWord> sensitiveWords = Lists.newArrayList();
        if (content.getCleanContent().contains("张彦峰企业词库")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("张彦峰企业词库")
                    .sensitiveId(23L)
                    .kind(4).build());
        }
        return sensitiveWords;
    }
}
