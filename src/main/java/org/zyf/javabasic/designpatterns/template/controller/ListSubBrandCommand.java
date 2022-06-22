package org.zyf.javabasic.designpatterns.template.controller;

import javax.validation.constraints.Min;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/4/24  21:33
 */
public class ListSubBrandCommand extends BaseCommand {
    @Min(value = 1, message = "parentId必须大于0")
    private int parentId;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
