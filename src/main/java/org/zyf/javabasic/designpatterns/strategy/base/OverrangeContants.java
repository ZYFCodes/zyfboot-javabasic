package org.zyf.javabasic.designpatterns.strategy.base;

/**
 * @author yanfengzhang
 * @description 超范围相关常量集合
 * @date 2022/3/3  23:14
 */
public class OverrangeContants {

    /**
     * 控制规则
     */
    public static class BizScopeControlRule {
        /**
         * 仅限指定类目
         */
        public static final int ONLY = 1;
        /**
         * 需包含指定类目
         */
        public static final int NEED = 2;
        /**
         * 不得包含指定类目
         */
        public static final int NO = 3;
    }
}
