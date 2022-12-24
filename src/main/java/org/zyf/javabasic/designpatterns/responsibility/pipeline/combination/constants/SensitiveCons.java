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
        public static final int TRADITIONAL_TO_SIMPLE = 1;
        /**
         * 去除相关特殊符号
         */
        public static final int REMOVE_SPECIAL_SYMBOLS = 2;
        /**
         * 去除相关emoji信息
         */
        public static final int REMOVE_EMOJI = 3;
        /**
         * 文本语言限制
         */
        public static final int LANGUAGE_LIMIT = 4;
        /**
         * 排除隐藏字符
         */
        public static final int EXCULDE_HIDDEN = 5;
    }
}
