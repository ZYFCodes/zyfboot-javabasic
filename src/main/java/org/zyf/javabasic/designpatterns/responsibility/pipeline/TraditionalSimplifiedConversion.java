package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.github.houbb.opencc4j.util.ZhTwConverterUtil;
import com.luhuiguo.chinese.ChineseUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description 数据清洗：中文繁体转换为简体
 * @date 2022/4/5  22:32
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
    public void handle(ContentInfoContext context, SensitveHitContext nextDeal) {
        /*前置节点处理异常，本节点不做处理（所以初始化传入的时候需要进行默认true，方便后期随时调整链路）*/
        if (!context.getDeliver()) {
            return;
        }
        try {
            String traditionalChinese = context.getContent();
            /*中国大陆普通词库进行第一次转换*/
            String commonConverter = ChineseUtils.toSimplified(traditionalChinese);
            /*中国港澳词库进行第二次转换*/
            String macauAndHKConverter = ZhConverterUtil.toSimple(commonConverter);
            /*中国台湾词库进行第三次转换*/
            String finalContent = ZhTwConverterUtil.toSimple(macauAndHKConverter);

            /*将清洗数据载入待校验实体中*/
            context.setContent(finalContent);
            context.setDeliver(true);
            BeanUtils.copyProperties(context, nextDeal);
        } catch (Exception e) {
            context.setDeliver(false);
            context.setReason("数据清洗异常：中文繁体转换失败");
        }
    }
}
