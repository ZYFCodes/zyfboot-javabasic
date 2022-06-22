package org.zyf.javabasic.servicegovernance.exception;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/21  23:57
 */
public class MnsException extends Exception{
    public MnsException() {
        this("Mns Exception");
    }

    public MnsException(String msg) {
        super(msg);
    }
}
