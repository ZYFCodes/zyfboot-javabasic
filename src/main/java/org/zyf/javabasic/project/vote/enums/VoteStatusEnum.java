package org.zyf.javabasic.project.vote.enums;

/**
 * @program: zyfboot-javabasic
 * @description: 投票状态枚举
 * @author: zhangyanfeng
 * @create: 2022-10-01 17:11
 **/
public enum VoteStatusEnum {
    CLOSED("CLOSED", "已关闭"),
    RUNNING("RUNNING", "进行中"),
    ;

    private String status;

    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    VoteStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
