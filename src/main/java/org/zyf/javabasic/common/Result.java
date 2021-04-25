package org.zyf.javabasic.common;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/4/19  16:34
 */
@Data
public class Result {
    private int code;
    private String message;
    private DataCsdn data;
}
