package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.dataclean;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.base.BCConvert;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.constants.SensitiveCons;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.enums.SensitiveClean;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentInfoContext;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author yanfengzhang
 * @description 排除隐藏字符
 * @date 2022/4/17  22:20
 */
@Component
@SensitiveClean(cleanCode = SensitiveCons.Clean.EXCULDE_HIDDEN)
public class ExcludeHiddenCharacters implements ContextHandler<ContentInfoContext, ContentCleanResContext> {

    private LoadingCache<String, Set<Integer>> hiddenCharactersCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            /*构建缓存*/
            .build(new CacheLoader<String, Set<Integer>>() {
                /*初始化加载数据的缓存信息*/
                @Override
                public Set<Integer> load(String specialSymbols) throws Exception {
                    return getHiddenCharacters();
                }
            });

    /**
     * 排除隐藏字符，避免用户采用一些特殊的隐藏字符来逃避敏感词命中
     *
     * @param context 处理时的上下文数据
     * @return 处理结果（代进入敏感词词库校验）
     */
    @Override
    public ContentCleanResContext handle(ContentInfoContext context) {
        try {
            String HIDDEREN = "hidden";

            Set<Integer> emojis = hiddenCharactersCache.get(HIDDEREN);
            /*其他链路中清洗后的词*/
            char[] valueChars = context.getCleanContent().toCharArray();
            StringBuilder cleanContent = new StringBuilder();
            for (char valueChar : valueChars) {
                int temp = BCConvert.charConvert(valueChar);
                if (emojis.contains(temp)) {
                    /*过滤隐藏字符*/
                    continue;
                }
                cleanContent.append(valueChar);
            }

            /*设置处理结果*/
            return ContentCleanResContext.builder()
                    .isCleanDone(true)
                    .content(context.getContent())
                    .cleanContent(cleanContent.toString())
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
                    .reason("数据清洗异常：排除隐藏字符失败")
                    .build();
        }
    }

    /**
     * 本处举例，实际应该放置到指定的配置页面来实时生效
     *
     * @return 相关特殊符号集合
     */
    private static Set<Integer> getHiddenCharacters() {
        List<String> specialSymbolsRes = Lists.newArrayList();
        String speciSymbols = "\u2069\u202A\u202B\u202C\u202D\u202E\u2060\u2061\u2062\u2063\u2064\uE7C7\uE7C8" +
                "͏\u2069\u2060\u2061\u2062\u2063\u2064\u2067\u206C\u206B";
        if (StringUtils.isNotBlank(speciSymbols)) {
            for (int index = 0; index < speciSymbols.length(); index++) {
                specialSymbolsRes.add(String.valueOf(speciSymbols.charAt(index)));
            }
        }

        Set<Integer> specialSymbolsSet = new HashSet<>();
        if (!CollectionUtils.isEmpty(specialSymbolsRes)) {
            char[] chs;
            for (String curr : specialSymbolsRes) {
                chs = curr.toCharArray();
                for (char c : chs) {
                    specialSymbolsSet.add(BCConvert.charConvert(c));
                }
            }
        }
        return specialSymbolsSet;
    }

    public static void main(String[] args) {
        String content = "你\u2069\u202A好\u2062\u2063\u2064\uE7C7\uE7C8，张\u2067\u206C彦\u2061\u2062\u2063峰！";
        char[] valueChars = content.toCharArray();
        Set<Integer> specialSymbols = getHiddenCharacters();
        StringBuilder cleanContent = new StringBuilder();
        for (char valueChar : valueChars) {
            int temp = BCConvert.charConvert(valueChar);
            if (specialSymbols.contains(temp)) {
                /*过滤特殊字符*/
                continue;
            }
            cleanContent.append(valueChar);
        }
        System.out.println(cleanContent.toString());
    }
}
