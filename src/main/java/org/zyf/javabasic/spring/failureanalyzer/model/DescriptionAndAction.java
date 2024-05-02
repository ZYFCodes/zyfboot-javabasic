package org.zyf.javabasic.spring.failureanalyzer.model;

/**
 * @program: zyfboot-javabasic
 * @description: DescriptionAndAction
 * @author: zhangyanfeng
 * @create: 2024-05-02 20:19
 **/
public class DescriptionAndAction {
    private final String description;
    private final String action;

    public DescriptionAndAction(String description, String action) {
        this.description = description;
        this.action = action;
    }

    public String getDescription() {
        return description;
    }

    public String getAction() {
        return action;
    }
}
