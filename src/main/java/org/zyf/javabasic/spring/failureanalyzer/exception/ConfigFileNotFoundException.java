package org.zyf.javabasic.spring.failureanalyzer.exception;

/**
 * @program: zyfboot-javabasic
 * @description: ConfigFileNotFoundException
 * @author: zhangyanfeng
 * @create: 2024-05-02 17:25
 **/
public class ConfigFileNotFoundException extends RuntimeException {

    private final String fileNames;

    public ConfigFileNotFoundException(String fileNames) {
        super("Configuration file '" + fileNames + "' not found");
        this.fileNames = fileNames;
    }

    public String getFileNames() {
        return fileNames;
    }
}
