package org.zyf.javabasic.dispatcher.exception;

/**
 * @program: zyfboot-javabasic
 * @description: 分发错误码
 * @author: zhangyanfeng
 * @create: 2024-04-21 19:06
 **/
public class DistributionException extends Exception {
    private int errorCode;

    public DistributionException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}