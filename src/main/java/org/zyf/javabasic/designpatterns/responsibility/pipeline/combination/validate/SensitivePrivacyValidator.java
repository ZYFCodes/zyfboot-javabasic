package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.validate;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.constants.SensitiveCons;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums.SensitiveValidate;
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
@SensitiveValidate(validateCode = SensitiveCons.Validate.PRIVACY)
public class SensitivePrivacyValidator implements ContextHandler<ContentCleanResContext, SensitveHitContext> {

    /**
     * 敏感词分析处理：手机号身份证号处理
     *
     * @param context 处理时的上下文数据
     * @return
     */
    @Override
    public SensitveHitContext handle(ContentCleanResContext context) {
        return SensitveHitContext.builder()
                .content(context.getContent())
                .cleanContent(context.getCleanContent())
                .contentAttr(context.getContentAttr())
                .hitWords(getPrivacy(context.getCleanContent())).build();
    }

    private List<SensitiveWord> getPrivacy(String content) {
        /*模拟*/
        List<SensitiveWord> sensitiveWords = Lists.newArrayList();
        if (content.contains("18252066688")) {
            sensitiveWords.add(SensitiveWord.builder()
                    .sensitive("18252066688")
                    .sensitiveId(453L)
                    .kind(18).build());
        }
        return sensitiveWords;
    }
}
