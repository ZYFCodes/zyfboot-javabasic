package org.zyf.javabasic.spring.failureanalyzer.analyzer;

import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;
import org.zyf.javabasic.spring.failureanalyzer.exception.ConfigFileNotFoundException;

/**
 * @program: zyfboot-javabasic
 * @description: 检查必要文件是否存在异常
 * @author: zhangyanfeng
 * @create: 2024-05-02 18:26
 **/
public class ZYFConfigFileFailureAnalyzer extends AbstractFailureAnalyzer<ConfigFileNotFoundException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, ConfigFileNotFoundException cause) {
        String description = description(cause);
        String action = action(cause);
        return new FailureAnalysis(description, action, cause);
    }

    private String description(ConfigFileNotFoundException ex) {
        return String.format("Failed to load configuration file '%s'.", ex.getFileNames());
    }

    private String action(ConfigFileNotFoundException ex) {
        return String.format("Check if the configuration file:'%s' exists.", ex.getFileNames());
    }
}
