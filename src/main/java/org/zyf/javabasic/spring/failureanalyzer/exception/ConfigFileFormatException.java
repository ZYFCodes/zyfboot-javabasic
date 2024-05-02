package org.zyf.javabasic.spring.failureanalyzer.exception;

import com.alibaba.fastjson.JSON;
import org.zyf.javabasic.spring.failureanalyzer.model.ConfigFileFormatErrorInfo;

/**
 * @program: zyfboot-javabasic
 * @description: 配置文件格式问题异常
 * @author: zhangyanfeng
 * @create: 2024-05-02 19:23
 **/
public class ConfigFileFormatException extends RuntimeException {

    private final ConfigFileFormatErrorInfo errorInfo;

    public ConfigFileFormatException(ConfigFileFormatErrorInfo errorInfo) {
        super("Configuration file format error: " + JSON.toJSONString(errorInfo));
        this.errorInfo = errorInfo;
    }

    public ConfigFileFormatErrorInfo getErrorInfo() {
        return errorInfo;
    }

}
