package org.zyf.javabasic.designpatterns.template.controller;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  21:16
 */
public class ZYFServerException extends RuntimeException {
    private static final long serialVersionUID = -8268393986631885578L;
    private int errorCode;
    private String errorMsg = "";

    public ZYFServerException(int errorCode, String errorMsg) {
        super();
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public ZYFServerException(ErrorCode error) {
        super();
        this.errorCode = error.getCode();
        this.errorMsg = error.getMsg();
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
