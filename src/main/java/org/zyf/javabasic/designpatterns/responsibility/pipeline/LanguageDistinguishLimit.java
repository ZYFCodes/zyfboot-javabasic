package org.zyf.javabasic.designpatterns.responsibility.pipeline;

import com.kanlon.entity.DetectMode;
import com.kanlon.language.LanguageDistinguish;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

/**
 * @author yanfengzhang
 * @description 文本语言限制
 * @date 2022/4/17  22:22
 */
@Component
public class LanguageDistinguishLimit implements ContextHandler<ContentInfoContext, SensitveHitContext> {

    /**
     * 文本语言限制，避免用户各种语言进行非法宣传，对文本进行语言限制
     *
     * @param context  处理时的上下文数据
     * @param nextDeal 处理结果（代进入敏感词词库校验）
     */
    @Override
    public void handle(ContentInfoContext context, SensitveHitContext nextDeal) {
        /*前置节点处理异常，本节点不做处理*/
        if (!context.getDeliver()) {
            return;
        }

        try {
            char[] valueChars = context.getContent().toCharArray();
            /*1.对文本语言进行识别，并判断语言类型种类*/
            /*2.查看对应语言类型是否符合系统处理的要求，如果不符合抛出异常，链路结束处理*/

            /*将清洗数据载入待校验实体中*/
            context.setDeliver(true);
            BeanUtils.copyProperties(context, nextDeal);
        } catch (Exception e) {
            context.setDeliver(false);
            context.setReason("数据清洗异常：文本语言已超出系统限制要求");
        }
    }

    public static void main(String[] args) {
        System.out.println(LanguageDistinguish.getLanguageByString("com.cybozu.labs.langdetect.Detector.getProbabilities", DetectMode.PRECISION));
        System.out.println(LanguageDistinguish.getLanguageByString("尽管每种应用都会有所不同，但是本质上都是相似的，需要比较单独个体的相似性。", DetectMode.PRECISION));
        System.out.println(LanguageDistinguish.getLanguageByString("com.cybozu.labs.langdetect.Detector.getProbabilities尽管每种应用都会有所不同，但是本质上都是相似的，需要比较单独个体的相似性。", DetectMode.PRECISION));
        System.out.println(LanguageDistinguish.getLanguageByString("BTS (방탄소년단) 'Save ME' Official MV\n", DetectMode.PRECISION));

    }
}
