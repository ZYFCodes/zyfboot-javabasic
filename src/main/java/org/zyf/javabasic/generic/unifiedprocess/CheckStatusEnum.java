package org.zyf.javabasic.generic.unifiedprocess;

/**
 * 检查状态信息校验
 */
public enum CheckStatusEnum {
    /**
     * 检查完成
     */
    SUCCESS(0, "检查完成，数据正确"),
    /**
     * 异常
     */
    EXCEPTION(1, "系统异常"),
    /**
     * 手动过滤
     */
    MANUAL_FILTER(2, "条件过滤"),
    /**
     * 检查活动层面相关信息
     */
    ACTIVITY_PARAM_ERROR(1001,"活动数据请求参数错误"),
    ACTIVITY_ID_ERROR(1002, "活动ID不合法"),
    ACTIVITY_NAME_ERROR(1003, "活动名称不能为空"),
    ACTIVITY_TIME_NULL_ERROR(1004, "活动开始时间和结束时间不能为空"),
    ACTIVITY_TIME_COMPARE_ERROR(1005, "结束时间不能早于起始时间"),
    /**
     * 影视层面相关信息校验：电影  Film and television
     */
    FILM_PARAM_ERROR(2001,"电影基本请求数据错误"),
    FILM_NAME_ERROR(2002, "电影名称不合法"),
    FILM_TIME_ERROR(2003, "电影时间信息不合法"),
    /**
     * 影视层面相关信息校验：电视剧  Film and television
     */
    TV_PARAM_ERROR(2004, "电视剧基本请求数据错误"),
    TV_CONTENT_ERROR(2005, "电视剧基本内容不合法"),
    ;

    private int value;
    private String desc;

    CheckStatusEnum(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
