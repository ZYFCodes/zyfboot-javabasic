package org.zyf.javabasic.aop.bizdeal.constants;

/**
 * 活动相关业务方法
 *
 * @author yanfengzhang
 */
public enum ActivityBizMethod {
    /**
     * 查询活动信息
     */
    QUERY("queryActivityDetail", true),
    /**
     * 创建或更新对应活动详情
     */
    CREATE_OR_UPDATE("createOrUpdateActivityDetail", true),
    /**
     * 删除活动详情
     */
    DELETE("deleteActivityDetail", false),
    /**
     * 检验活动有效性
     */
    CHECK("checkActivityInfo", true),
    /**
     * 活动上线操作
     */
    ONLINE("onlineActivity", true),
    /**
     * 活动下线操作
     */
    OFFLINE("offlineActivity", true),
    /**
     * 活动常规操作
     */
    DEFAULT("default", false);

    private final String methodName;
    private final boolean needClosed;

    ActivityBizMethod(String methodName, boolean needClosed) {
        this.methodName = methodName;
        this.needClosed = needClosed;
    }

    public boolean doBizMethodAfterHandler() {
        return !needClosed;
    }

    public String getMethodName() {
        return methodName;
    }
}
