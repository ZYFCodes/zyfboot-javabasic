package org.zyf.javabasic.skills.reflection.dto;

import lombok.Data;

/**
 * 描述：商品
 *
 * @author yanfengzhang
 * @date 2020-01-02 17:02
 */
@Data
public class Product {
    /**
     * 商品id
     */
    private Integer id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品描述
     */
    private String description;
}
