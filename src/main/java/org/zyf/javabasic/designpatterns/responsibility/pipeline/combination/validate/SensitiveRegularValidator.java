package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.validate;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.ContextHandler;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.base.Base64;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.ContentCleanResContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.RegularTypeEnum;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitiveWord;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.SensitveHitContext;
import org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.model.WordRegular;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author yanfengzhang
 * @description 敏感词校验：正则校验处理
 * @date 2022/4/5  22:09
 */
@Component
@Log4j2
public class SensitiveRegularValidator implements ContextHandler<ContentCleanResContext, SensitveHitContext> {

    private LoadingCache<String, Map<WordRegular, Pattern[]>> wordRegularCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(10, TimeUnit.MINUTES)
            /*构建缓存*/
            .build(new CacheLoader<String, Map<WordRegular, Pattern[]>>() {
                /*初始化加载数据的缓存信息*/
                @Override
                public Map<WordRegular, Pattern[]> load(String wordRegularInfo) throws Exception {
                    return getwordRegularCache();
                }
            });

    /**
     * 敏感词分析处理：根据相关业务配置进行相关正则校验处理
     * @param context 处理时的上下文数据
     * @return 处理结果（代进入敏感词生效处理）
     */
    @Override
    public SensitveHitContext handle(ContentCleanResContext context) {
        List<SensitiveWord> hitWords = Lists.newArrayList();
        try {
            /*此处只为模拟*/
            hitWords.addAll(context.getHitWords());
            hitWords.addAll(getSensitiveRegularValidator(context.getCleanContent()));
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

    private Map<WordRegular, Pattern[]> getwordRegularCache() {
        /*从指定词正则库中拉取配置，在此处放本地缓存或redis，这里只进行模拟*/
        List<WordRegular> words = Lists.newArrayList();
        words.add(WordRegular.builder().id(11L).type(RegularTypeEnum.AND_REGULAR.getCode()).words("与,正则").build());

        Map<WordRegular, Pattern[]> wordRegularPattern = Maps.newHashMap();
        String[] wordstr;
        Pattern p1;
        Pattern p2;
        for (WordRegular wordRegular : words) {
            switch (RegularTypeEnum.getByCode(wordRegular.getType())) {
                case SIAMPLE_REGULAR:
                    Pattern p = Pattern.compile(Base64.decodeToString(wordRegular.getWords()), Pattern.CASE_INSENSITIVE);
                    wordRegularPattern.put(wordRegular, new Pattern[]{p});
                    break;
                case AND_REGULAR:
                case N_AND_REGULAR:
                    wordstr = wordRegular.getWords().split(",");
                    if (2 != wordstr.length) {
                        continue;
                    }
                    p1 = Pattern.compile(Base64.decodeToString(wordstr[0]), Pattern.CASE_INSENSITIVE);
                    p2 = Pattern.compile(Base64.decodeToString(wordstr[1]), Pattern.CASE_INSENSITIVE);
                    wordRegularPattern.put(wordRegular, new Pattern[]{p1, p2});
                    break;
                default:
                    break;
            }
        }
        return wordRegularPattern;
    }

    private List<SensitiveWord> getSensitiveRegularValidator(String content) throws ExecutionException {
        List<SensitiveWord> result = Lists.newArrayList();
        if (StringUtils.isEmpty(content)) {
            return result;
        }
        final String CHAR_PATTERN = "[\\s\\d\\pP+~$`^=|<>～｀＄＾＋＝｜＜＞￥× ]";
        content = content.replaceAll(CHAR_PATTERN, "");
        boolean illegal = false;
        Matcher matcher1 = null;
        Matcher matcher2 = null;

        Map<WordRegular, Pattern[]> getwordRegularCache = wordRegularCache.get("wordRegularInfo");
        for (Map.Entry<WordRegular, Pattern[]> e : getwordRegularCache.entrySet()) {
            switch (Objects.requireNonNull(RegularTypeEnum.getByCode(e.getKey().getType()))) {
                case SIAMPLE_REGULAR:
                    Matcher matcher = e.getValue()[0].matcher(content);
                    illegal = matcher.find();
                    /*已经命中正则的前提下，需要同时满足业务身份的要求*/
                    if (illegal) {
                        result.add(SensitiveWord.builder().sensitive(matcher.group()).sensitiveId(e.getKey().getId()).kind(5).build());
                    }
                    break;
                case AND_REGULAR:
                    matcher1 = e.getValue()[0].matcher(content);
                    matcher2 = e.getValue()[1].matcher(content);
                    illegal = matcher1.find() && matcher2.find();
                    if (illegal) {
                        result.add(SensitiveWord.builder().sensitive(matcher1.group()).sensitiveId(e.getKey().getId()).kind(5).build());
                        result.add(SensitiveWord.builder().sensitive(matcher2.group()).sensitiveId(e.getKey().getId()).kind(5).build());
                    }
                    break;
                case N_AND_REGULAR:
                    matcher1 = e.getValue()[0].matcher(content);
                    matcher2 = e.getValue()[1].matcher(content);
                    illegal = matcher1.find() && !matcher2.find();
                    if (illegal) {
                        result.add(SensitiveWord.builder().sensitive(matcher1.group()).sensitiveId(e.getKey().getId()).kind(5).build());
                    }
                    break;
                default:
                    break;
            }
        }
        return result;
    }
}
