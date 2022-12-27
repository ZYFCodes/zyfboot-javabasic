package org.zyf.javabasic.designpatterns.responsibility.pipeline.combination.constants;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/12/23  18:18
 */
public class SensitiveCons {

    /**
     * 数据清洗
     */
    public static class Clean {
        /**
         * 中文繁体转换为简体
         */
        public static final int TRADITIONAL_TO_SIMPLE = 1001;
        /**
         * 去除相关特殊符号
         */
        public static final int REMOVE_SPECIAL_SYMBOLS = 1002;
        /**
         * 去除相关emoji信息
         */
        public static final int REMOVE_EMOJI = 1003;
        /**
         * 文本语言限制
         */
        public static final int LANGUAGE_LIMIT = 1004;
        /**
         * 排除隐藏字符
         */
        public static final int EXCULDE_HIDDEN = 1005;
    }

    /**
     * 敏感词校验
     */
    public static class Validate {
        /**
         * 企业合规管控校验
         */
        public static final int COMPLIANCE = 2001;
        /**
         * 敏感词分析处理：根据相关业务配置进行相关词库校验匹配
         */
        public static final int THESAURUS = 2002;
        /**
         * 正则校验处理
         */
        public static final int REGULAR = 2003;
        /**
         * 手机号身份证号处理
         */
        public static final int PRIVACY = 2004;
    }

    /**
     * 敏感词校验
     */
    public static class Effect {
        /**
         * 合规管控处理
         */
        public static final int COMPLIANCE_CONTROL = 3001;
        /**
         * 生效规则处理
         */
        public static final int RULE = 3002;
        /**
         * 白名单处理
         */
        public static final int WHITE = 3003;
    }
}
