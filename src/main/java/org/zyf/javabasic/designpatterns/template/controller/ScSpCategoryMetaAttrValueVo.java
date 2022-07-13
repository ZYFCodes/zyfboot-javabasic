package org.zyf.javabasic.designpatterns.template.controller;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/7/11  21:19
 */
@Data
public class ScSpCategoryMetaAttrValueVo {

    private int id; // required
    private String text; // required
    private String value; // required

    private int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    private String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    private String getValue() {
        return value;
    }

    private void setValue(String value) {
        this.value = value;
    }
}
