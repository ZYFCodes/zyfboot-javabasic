package org.zyf.javabasic.spring.failureanalyzer.model;

/**
 * @program: zyfboot-javabasic
 * @description: 表示不同的错误类型，例如缺少必要属性、属性值格式错误等
 * @author: zhangyanfeng
 * @create: 2024-05-02 19:57
 **/
public class ConfigFileFormatErrorInfo {
    private final boolean fileNotFound;
    private final ErrorType errorType;
    private final String fileName;

    public ConfigFileFormatErrorInfo(boolean fileNotFound, ErrorType errorType, String fileName) {
        this.fileNotFound = fileNotFound;
        this.errorType = errorType;
        this.fileName = fileName;
    }

    public boolean isFileNotFound() {
        return fileNotFound;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public String getFileName() {
        return fileName;
    }

    public DescriptionAndAction getDescriptionAndAction() {
        String description;
        String action;

        if (fileNotFound) {
            description = "Configuration file '" + fileName + "' not found";
            action = "Check if the configuration file exists.";
        } else {
            switch (errorType) {
                case MISSING_PROPERTY:
                    description = "Missing required property in configuration file '" + fileName + "'";
                    action = "Ensure all required properties are provided in the configuration file.";
                    break;
                case INVALID_VALUE:
                    description = "Invalid value for property in configuration file '" + fileName + "'";
                    action = "Correct the value of the property in the configuration file.";
                    break;
                case OTHER:
                default:
                    description = "Other configuration file format error in file '" + fileName + "'";
                    action = "Review the configuration file for formatting issues.";
                    break;
            }
        }

        return new DescriptionAndAction(description, action);
    }

    public enum ErrorType {
        MISSING_PROPERTY,
        INVALID_VALUE,
        OTHER
    }
}
