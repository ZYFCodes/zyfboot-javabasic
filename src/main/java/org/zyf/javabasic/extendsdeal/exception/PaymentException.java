package org.zyf.javabasic.extendsdeal.exception;

/**
 * @program: zyfboot-javabasic
 * @description: PaymentException
 * @author: zhangyanfeng
 * @create: 2025-03-09 15:17
 **/
public class PaymentException extends Exception{
    private int errorCode;

    public PaymentException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public PaymentException(String message) {
        super(message);
        this.errorCode = 99999999;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
