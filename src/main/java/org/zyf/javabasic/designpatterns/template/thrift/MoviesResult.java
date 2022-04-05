package org.zyf.javabasic.designpatterns.template.thrift;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 获取的电影集合
 * @date 2022/3/18  23:35
 */
@Data
@Builder
public class MoviesResult {
    /**
     * 总数目
     */
    private long total;
    /**
     * 具体电影信息
     */
    public List<MovieInfo> movieInfos;
    /**
     * 电影ID集合
     */
    public List<Long> movieIdList;
    /**
     * 下一个滚动ID
     */
    private Long nextScrollId;
}
