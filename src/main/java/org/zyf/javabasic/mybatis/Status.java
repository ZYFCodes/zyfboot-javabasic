package org.zyf.javabasic.mybatis;

/**
 * @author yanfengzhang
 * @description 订单实体类 Order
 * 其中包含一个 Status 枚举类型
 * @date 2023/5/13  23:34
 */
public enum Status {
    CREATED(0, "新建"),
    PROCESSING(1, "处理中"),
    COMPLETED(2, "已完成"),
    CANCELLED (3, "已取消")      ;

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

     Status(Integer code, String desc) {
        this.desc = desc;
        this.code = code;
    }

    public static  Status getEnumById(Integer code) {
        for ( Status  Status :  Status.values()) {
            if ( Status.getCode().equals(code)) {
                return  Status;
            }
        }
        return null;
    }

    public static  Status getEnumByDesc(String desc) {
        for ( Status  Status :  Status.values()) {
            if ( Status.getDesc().equals(desc)) {
                return  Status;
            }
        }
        return null;
    }

    public static boolean isStatus(Integer code) {
        if (null == code) {
            return false;
        }
        for ( Status tempEnum :  Status.values()) {
            if (tempEnum.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }
}
