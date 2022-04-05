package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description 敏感词校验：手机号身份证号处理
 * @date 2022/4/5  18:19
 */
@Component
public class SensitivePrivacyValidator implements ContextHandler<SensitveHitContext, SensitveResContext>{
    /**
     * 敏感词分析处理：手机号身份证号处理
     * @param context 处理时的上下文数据
     * @param dealRes 增加字段deliver为true则表示由下一个ContextHandler继续处理；为false则表示处理结束Content information
     */
    @Override
    public void handle(SensitveHitContext context, SensitveResContext dealRes) {

    }
}
