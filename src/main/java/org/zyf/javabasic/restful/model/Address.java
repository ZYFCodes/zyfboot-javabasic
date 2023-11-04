package org.zyf.javabasic.restful.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: zyfboot-javabasic
 * @description: 定义 Address 类的结构
 * @author: zhangyanfeng
 * @create: 2023-10-29 19:51
 **/
@Data
public class Address {
    @NotNull(message = "街道不能为空")
    private String street;

    @NotNull(message = "城市不能为空")
    private String city;

    @NotNull(message = "邮政编码不能为空")
    private String zipCode;
}

