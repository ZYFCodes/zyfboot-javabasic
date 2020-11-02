package org.zyf.javabasic.common;

/**
 * @author yanfengzhang
 * @description
 * @date 2020/10/30  17:35
 */
public class ActivityBizException extends RuntimeException {
    private String msg;
    private int code = 500;

    public ActivityBizException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public ActivityBizException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public ActivityBizException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ActivityBizException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
