package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description 敏感词校验：正则校验处理
 * @date 2022/4/5  18:09
 */
@Component
public class SensitiveRegularValidator implements ContextHandler<SensitveHitContext, SensitveResContext>{
    /**
     * 敏感词分析处理：根据相关业务配置进行相关正则校验处理
     * @param context 处理时的上下文数据
     * @param dealRes 增加字段deliver为true则表示由下一个ContextHandler继续处理；为false则表示处理结束Content information
     */
    @Override
    public void handle(SensitveHitContext context, SensitveResContext dealRes) {

    }
}
