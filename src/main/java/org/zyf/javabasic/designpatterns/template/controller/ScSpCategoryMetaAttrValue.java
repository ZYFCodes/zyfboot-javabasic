package org.zyf.javabasic.designpatterns.template.controller;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/11  21:21
 */
@Data
public class ScSpCategoryMetaAttrValue {
    private int id;
    private int attrMetaCode;
    private String text;
    private String value;
    private int valueAction;
    private int valueType;
}
