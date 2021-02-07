package org.zyf.javabasic.generic;

import lombok.Builder;
import lombok.Data;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/1/28  23:51
 */

@Data
@Builder
public class TVSeries {
    /**
     * 电视剧主键id
     */
    private Integer id;
    /**
     * 电视剧名称
     */
    private String name;
    /**
     * 电视剧集数
     */
    private Integer count;
    /**
     * 具体内容阐述
     */
    private String content;
    /**
     * 每集时间
     */
    private Integer time;
    /**
     * 上线时间
     */
    private Integer onLineTime;
}
