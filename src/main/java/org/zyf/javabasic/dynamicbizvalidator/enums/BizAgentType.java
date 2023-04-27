package org.zyf.javabasic.dynamicbizvalidator.enums;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/4/26  23:45
 */
public enum BizAgentType {
    ALL(0, "全部"),
    DIRECT(1, "直营"),
    PROXY(2, "代理");

    /**
     * 业务类型处理:0:全部 1:直营，2:代理
     */
    private Integer code;
    /**
     * 业务类型描述
     */
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    BizAgentType(Integer code) {
        this.code = code;
    }

    BizAgentType(Integer code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    /**
     * 根据业务类型处理code获取对应的业务类型
     *
     * @param code 业务类型处理code
     * @return
     */
    public static BizAgentType getEnumById(Integer code) {
        for (BizAgentType bizAgentType : BizAgentType.values()) {
            if (bizAgentType.getCode().equals(code)) {
                return bizAgentType;
            }
        }
        return null;
    }

    /**
     * 根据业务类型处理描述获取对应的业务类型
     *
     * @param desc 业务类型处理描述
     * @return
     */
    public static BizAgentType getEnumByDesc(String desc) {
        for (BizAgentType bizAgentType : BizAgentType.values()) {
            if (bizAgentType.getDesc().equals(desc)) {
                return bizAgentType;
            }
        }
        return null;
    }

    /**
     * 判断业务类型code是否在指定范围
     *
     * @param code 业务类型处理code
     * @return true-在指定范围
     */
    public static boolean isBizAgentType(Integer code) {
        if (null == code) {
            return false;
        }
        for (BizAgentType tempEnum : BizAgentType.values()) {
            if (tempEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 通过是否打合作商标判定城市类型归属，有合作商标（agentID>0）为代理商家、无合作商标（agentID=0）为直营商家
     * * 与代理业务类型对应：0:全部 1:直营，2:代理
     *
     * @param agentType 实际门店数据
     * @return BizAgentType 代理业务类型
     */
    public static BizAgentType getAgentTypeByRealBiz(long agentType) {
        if (agentType == 0L) {
            return BizAgentType.DIRECT;
        }

        if (agentType > 0L) {
            return BizAgentType.PROXY;
        }

        return BizAgentType.ALL;
    }
}
