package org.zyf.javabasic.generic;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/1/28  23:02
 */
@Data
public class Movie {
    /**
     * 电影主键id
     */
    private Integer id;
    /**
     * 电影名称
     */
    private String name;
    /**
     * 具体内容阐述
     */
    private String content;
    /**
     * 电影时间
     */
    private Integer time;
    /**
     * 电影类型
     */
    private Integer type;
    /**
     * 电影产地
     */
    private String product;
    /**
     * 上线时间
     */
    private Integer onLineTime;
}
