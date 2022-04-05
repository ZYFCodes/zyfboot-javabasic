package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.github.houbb.opencc4j.util.ZhTwConverterUtil;
import com.luhuiguo.chinese.ChineseUtils;
import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description 数据清洗：中文繁体转换为简体
 * @date 2022/4/5  12:32
 */
@Component
public class TraditionalSimplifiedConversion implements ContextHandler<ContentInfoContext, SensitveHitContext> {
    /**
     * 对用户内容进行处理：中文繁体转换为简体
     *
     * @param context 处理时的上下文数据
     * @return 处理结果（代进入敏感词词库校验）
     */
    @Override
    public void handle(ContentInfoContext context, SensitveHitContext sensitveHitContext) {
        try {
            String traditionalChinese = context.getContent();
            /*中国大陆普通词库进行第一次转换*/
            String commonConverter = ChineseUtils.toSimplified(traditionalChinese);
            /*中国港澳词库进行第二次转换*/
            String macauAndHKConverter = ZhConverterUtil.toSimple(commonConverter);
            /*中国台湾词库进行第三次转换并返回*/
            String finalContent = ZhTwConverterUtil.toSimple(macauAndHKConverter);

            /*将清洗数据载入待校验实体中*/
            sensitveHitContext.setDeliver(true);
            sensitveHitContext.setCleanContent(finalContent);
        } catch (Exception e) {
            sensitveHitContext.setDeliver(false);
            sensitveHitContext.setReason("数据清洗异常：中文繁体转换失败");
        }
    }
}
