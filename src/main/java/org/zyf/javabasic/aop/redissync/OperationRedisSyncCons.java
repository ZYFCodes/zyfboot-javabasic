package org.zyf.javabasic.aop.redissync;

/**
 * @author yanfengzhang
 * @description 异步同步redis相关处理常量信息
 * @date 2022/7/19  23:52
 */
public class OperationRedisSyncCons {

    /**
     * 处理的业务常量
     */
    public static final class BizType {
        /*敏感词业务*/
        public final static int SENSITIVE = 1;
        /*超范围资质业务*/
        public final static int OVERRANGE = 2;
        /*会员渠道业务*/
        public final static int MEMBER_CHANNEL = 3;
        /*红包发布业务*/
        public final static int RED_PACKAGE = 4;
    }

    /**
     * 处理的业务RUD操作指示
     */
    public static final class DealType {
        /*新增*/
        public final static int CREATE = 1;
        /*修改*/
        public final static int UPDATE = 2;
        /*删除*/
        public final static int DELETE = 3;
    }
}
