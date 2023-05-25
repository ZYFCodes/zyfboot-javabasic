package org.zyf.javabasic.sensitive.base;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/5/24  20:14
 */

/**
 * 目前支持的校验类型
 *
 * @author wuxiaoxu 20191119
 */
public enum ValidateRule {
    /**
     * 当敏感词开启泛化时，nlp会走泛化逻辑
     */
    NLP(1),

    /**
     * 当敏感词未开启泛化时，NLP中会当做去次匹配来处理，code用于日志记录，与nlp保持一致
     */
    NLP_KEYWORD(1),

    /**
     * 关键字
     */
    KEYWORD(2),
    /**
     * 正则
     */
    REGULAR(3),

    /**
     * 过滤后的保时洁结果
     */
    BAOSHIJIE_WAIMAI(4),

    /**
     * 保时洁返回的所有结果
     */
    BAOSHIJIE(5),

    /**
     * 同音词返回的所有结果
     */
    HOMONY(6);

    private int code;

    ValidateRule(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static ValidateRule getRule(String ruleName) {
        for (ValidateRule validateRule : ValidateRule.values()) {
            if (validateRule.name().equals(ruleName)) {
                return validateRule;
            }
        }
        return null;
    }

}

