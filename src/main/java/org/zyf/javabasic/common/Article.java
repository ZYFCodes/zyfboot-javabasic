package org.zyf.javabasic.common;

import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/4/19  16:35
 */
@Data
public class Article {
    private Integer articleId;
    private Integer commentCount;
    private String description;
    private Integer diggCount;
    private Integer viewCount;
    private String editUrl;
    private Boolean forcePlan;
    private String title;
    private String url;
}
