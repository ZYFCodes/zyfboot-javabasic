package org.zyf.javabasic.designpatterns.template.controller;

import java.io.Serializable;

/**
 * @author yanfengzhang
 * @description 通用返回类
 * @date 2022/4/24  21:13
 */
public class BizResponse<T> implements Serializable {
    private static final long serialVersionUID = 7649505881546361075L;
    protected int code;
    protected String msg = "";
    protected T data;

    public static <T> BizResponse<T> success() {
        return new BizResponse<>();
    }

    public static <T> BizResponse<T> success(String msg) {
        return new BizResponse<>(0, msg);
    }

    public static <T> BizResponse<T> success(T data) {
        return new BizResponse<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMsg(), data);
    }

    public BizResponse() {
        super();
    }

    public BizResponse(T data) {
        super();
        this.data = data;
    }

    public BizResponse(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public BizResponse(int code, String msg, T data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static BizResponse failedOf(ErrorCode errorCode) {
        return new BizResponse(errorCode.getCode(), errorCode.getMsg());
    }

    public static BizResponse failedOf(int code, String msg) {
        return new BizResponse(code, msg);
    }

    public void setCodeAndMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(this);
    }
}

