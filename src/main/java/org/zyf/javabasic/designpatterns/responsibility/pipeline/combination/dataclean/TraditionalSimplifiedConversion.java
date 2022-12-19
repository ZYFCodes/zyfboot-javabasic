package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.dataclean;

import com.github.houbb.opencc4j.util.ZhConverterUtil;
import com.github.houbb.opencc4j.util.ZhTwConverterUtil;
import com.luhuiguo.chinese.ChineseUtils;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentInfoContext;

/**
 * @author yanfengzhang
 * @description 数据清洗：中文繁体转换为简体
 * @date 2022/4/5  22:32
 */
@Component
public class TraditionalSimplifiedConversion implements ContextHandler<ContentInfoContext, ContentCleanResContext> {

    /**
     * 对用户内容进行处理：中文繁体转换为简体
     *
     * @param context 处理时的上下文数据
     * @return 处理结果（代进入敏感词词库校验）
     */
    @Override
    public ContentCleanResContext handle(ContentInfoContext context) {
        try {
            /*其他链路中清洗后的词*/
            String traditionalChinese = context.getCleanContent();
            /*中国大陆普通词库进行第一次转换*/
            String commonConverter = ChineseUtils.toSimplified(traditionalChinese);
            /*中国港澳词库进行第二次转换*/
            String macauAndHKConverter = ZhConverterUtil.toSimple(commonConverter);
            /*中国台湾词库进行第三次转换*/
            String finalContent = ZhTwConverterUtil.toSimple(macauAndHKConverter);

            /*将本次清洗数据载入待继续清洗实体中*/
            context.setCleanContent(finalContent);
            /*设置处理结果*/
            return ContentCleanResContext.builder()
                    .isCleanDone(true)
                    .content(context.getContent())
                    .cleanContent(finalContent)
                    .contentAttr(context.getContentAttr())
                    .build();
        } catch (Exception e) {
            /*设置处理结果*/
            return ContentCleanResContext.builder()
                    .isCleanDone(false)
                    .content(context.getContent())
                    /*记录下中间态数据*/
                    .cleanContent(context.getCleanContent())
                    .contentAttr(context.getContentAttr())
                    .reason("数据清洗异常：中文繁体转换失败")
                    .build();
        }
    }
}
