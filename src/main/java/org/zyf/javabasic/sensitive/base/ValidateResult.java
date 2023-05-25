package org.zyf.javabasic.sensitive.base;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/24  20:17
 */
@Data
public class ValidateResult {
    /**
     * 原始字符串，
     * 全词匹配：校验时可能会对特殊字符进行过，所有source和sensitive存在不一样的情况
     */
    private String source;

    /**
     * 命中的敏感词
     */
    private String sensitive;

    /**
     * 敏感词
     */
    private WordDomain wordDomain;

    /**
     * 当前匹配的类型
     */
    private ValidateRule rule;

    /**
     * 敏感词类型区分
     */
    private SensitiveWordWithType sensitiveWordWithType;

    /**
     * 敏感词清真类型区分
     */
    private SensitiveWordWithMuslim sensitiveWordWithMuslim;

    /**
     * 改词是否需要清真资质 -- 用于清真门店是否对门店进行清真提醒
     */
    public SensitiveWordWithQua sensitiveWordWithQua;

    public ValidateResult(String source, String sensitive, WordDomain word, ValidateRule rule,
                          SensitiveWordWithType sensitiveWordWithType, SensitiveWordWithMuslim sensitiveWordWithMuslim, SensitiveWordWithQua sensitiveWordWithQua) {
        this.source = source;
        this.sensitive = sensitive;
        this.wordDomain = word;
        this.rule = rule;
        this.sensitiveWordWithType = sensitiveWordWithType;
        this.sensitiveWordWithMuslim = sensitiveWordWithMuslim;
        this.sensitiveWordWithQua = sensitiveWordWithQua;
    }
}
