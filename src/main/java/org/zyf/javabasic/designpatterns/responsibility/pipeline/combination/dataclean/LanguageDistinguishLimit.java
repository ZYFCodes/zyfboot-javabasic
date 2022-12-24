package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.dataclean;

import com.kanlon.entity.DetectMode;
import com.kanlon.language.LanguageDistinguish;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.constants.SensitiveCons;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums.SensitiveClean;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentInfoContext;

/**
 * @author yanfengzhang
 * @description 文本语言限制
 * @date 2022/4/17  22:22
 */
@Component
@SensitiveClean(cleanCode = SensitiveCons.Clean.LANGUAGE_LIMIT)
public class LanguageDistinguishLimit implements ContextHandler<ContentInfoContext, ContentCleanResContext> {

    /**
     * 文本语言限制，避免用户各种语言进行非法宣传，对文本进行语言限制
     *
     * @param context 处理时的上下文数据
     * @return 处理结果（代进入敏感词词库校验）
     */
    @Override
    public ContentCleanResContext handle(ContentInfoContext context) {
        try {
            char[] valueChars = context.getCleanContent().toCharArray();
            /*1.对文本语言进行识别，并判断语言类型种类*/
            /*2.查看对应语言类型是否符合系统处理的要求，如果不符合抛出异常，链路结束处理*/

            /*将本次清洗数据载入待继续清洗实体中*/
            context.setCleanContent(context.getCleanContent());
            /*设置处理结果*/
            return ContentCleanResContext.builder()
                    .isCleanDone(true)
                    .content(context.getContent())
                    .cleanContent(context.getCleanContent())
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
                    .reason("数据清洗异常：文本语言已超出系统限制要求")
                    .build();
        }
    }

    public static void main(String[] args) {
        System.out.println(LanguageDistinguish.getLanguageByString("com.cybozu.labs.langdetect.Detector.getProbabilities", DetectMode.PRECISION));
        System.out.println(LanguageDistinguish.getLanguageByString("尽管每种应用都会有所不同，但是本质上都是相似的，需要比较单独个体的相似性。", DetectMode.PRECISION));
        System.out.println(LanguageDistinguish.getLanguageByString("com.cybozu.labs.langdetect.Detector.getProbabilities尽管每种应用都会有所不同，但是本质上都是相似的，需要比较单独个体的相似性。", DetectMode.PRECISION));
        System.out.println(LanguageDistinguish.getLanguageByString("BTS (방탄소년단) 'Save ME' Official MV\n", DetectMode.PRECISION));

    }
}
