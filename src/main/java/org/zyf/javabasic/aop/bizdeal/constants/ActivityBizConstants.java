package org.zyf.javabasic.aop.bizdeal.constants;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/10/30  23:41
 */
public final class ActivityBizConstants {

    /**
     * 活动的业务类型
     */
    public static class ActivityBizType {
        public static final String AUTO_RENEMAL = "5";
        public static final String BUY_FOR_GET = "3";
        public static final String DISCOUNT = "6";
        public static final String FIRST_PURCHASE = "4";
        public static final String LIMIT_TIME = "2";
        public static final String PRICE_CUT = "1";
    }

    /**
     * 活动处理中业务码
     */
    public static class ActivityBizCode {
        public static final int ANNO_EMPTY_BIZTYPE_ERR = 1001;
        public static final int NO_HANDLER_FOUND = 1002;
        public static final int HANDLER_NO_VALID_METHOD_ERR = 1003;
        public static final int HANDLER_BIZ_ERR = 1004;
        public static final int UNKNOWN_MSG_FORMAT_ERR = 1008;
    }

    /**
     * 活动的相关状态信息
     */
    public static class ActivityBizStatus {
        public static final int CREATE = 0;
        public static final int ONLINE = 1;
        public static final int OFFLINE = 2;
        public static final int DELETE = 99;
    }
}
