package org.zyf.javabasic.spring.failureanalyzer.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.zyf.javabasic.spring.failureanalyzer.exception.ConfigFileFormatException;
import org.zyf.javabasic.spring.failureanalyzer.model.ConfigFileFormatErrorInfo;
import org.zyf.javabasic.spring.failureanalyzer.model.DescriptionAndAction;

/**
 * @program: zyfboot-javabasic
 * @description: 指定配置文件具体格式要求
 * @author: zhangyanfeng
 * @create: 2024-05-02 20:31
 **/
public class ZYFConfigFileFormatFailureanalyzer extends AbstractFailureAnalyzer<ConfigFileFormatException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, ConfigFileFormatException cause) {
        ConfigFileFormatErrorInfo errorInfo = cause.getErrorInfo();

        String description;
        String action;

        if (errorInfo.isFileNotFound()) {
            description = "Configuration file '" + errorInfo.getFileName() + "' not found";
            action = "Check if the configuration file exists.";
        } else {
            DescriptionAndAction descriptionAndAction = errorInfo.getDescriptionAndAction();

            description = descriptionAndAction.getDescription();
            action = descriptionAndAction.getAction();
        }

        return new FailureAnalysis(description, action, cause);
    }
}
