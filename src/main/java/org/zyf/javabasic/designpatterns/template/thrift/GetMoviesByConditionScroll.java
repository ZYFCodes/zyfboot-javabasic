package org.zyf.javabasic.designpatterns.template.thrift;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author yanfengzhang
 * @description 获取电影thrift接口滚动处理
 * @date 2022/3/20  17:37
 */
@Slf4j
public class GetMoviesByConditionScroll extends ThriftInvokeScrollCommand<MoviesConditions, MoviesResult, MovieBasicInfo> {
    /**
     * 实际thrift接口（此处只做模拟）
     * 实际该处应该类似为：private MoviesInfoThriftService.Iface moviesInfoThriftService;
     */
    private MoviesInfoThriftService moviesInfoThriftService;

    /**
     * 实际分页调用构造器
     *
     * @param moviesConditions        请求查询分页请求
     * @param moviesInfoThriftService 实际分页调用电影ThriftService
     */
    public GetMoviesByConditionScroll(MoviesConditions moviesConditions, MoviesInfoThriftService moviesInfoThriftService) {
        super(moviesConditions);
        this.moviesInfoThriftService = moviesInfoThriftService;
    }

    /**
     * @param moviesConditions 请求查询分页请求
     * @param scrollId         当前滚动ID
     */
    @Override
    protected void setScrollId(MoviesConditions moviesConditions, Long scrollId) {
        if (Objects.isNull(moviesConditions)) {
            return;
        }
        moviesConditions.setScrollId(scrollId);
    }

    /**
     * @param thriftResult 请求查询分页请求
     * @return 下一个滚动ID
     */
    @Override
    protected Long getNextScrollId(MoviesResult thriftResult) {
        if (Objects.isNull(thriftResult)) {
            return 0L;
        }
        return thriftResult.getNextScrollId();
    }

    /**
     * 设置实际页大小
     *
     * @param moviesConditions 请求查询分页请求
     * @param pageSize         页大小
     */
    @Override
    protected void setPageSize(MoviesConditions moviesConditions, int pageSize) {
        if (Objects.isNull(moviesConditions)) {
            return;
        }
        moviesConditions.setPageSize(pageSize);
    }

    /**
     * 获取实际数据大小
     *
     * @param moviesResult 请求查询分页请求
     * @return 实际数据大小
     */
    @Override
    protected int getResultSize(MoviesResult moviesResult) {
        if (Objects.isNull(moviesResult)) {
            return 0;
        }
        return moviesResult.getMovieInfos().size();
    }

    /**
     * 实际业务调用thrift接口
     *
     * @param moviesConditions 请求查询分页请求
     * @return 实际业务调用thrift接口返回
     * @throws Exception 业务异常
     */
    @Override
    protected MoviesResult invoke(MoviesConditions moviesConditions) throws Exception {
        if (Objects.isNull(moviesConditions)) {
            return null;
        }
        return moviesInfoThriftService.getMoviesByConditionScroll(moviesConditions);
    }

    /**
     * 获取实际业务需要的结果（进行实际的转换）
     *
     * @param moviesResult 实际业务调用thrift接口返回
     * @return 实际业务需要的电影基本信息
     */
    @Override
    protected List<MovieBasicInfo> convertThriftResult(MoviesResult moviesResult) {
        if (Objects.isNull(moviesResult) || CollectionUtils.isEmpty(moviesResult.getMovieInfos())) {
            return Lists.newArrayList();
        }

        return moviesResult.getMovieInfos().stream().map(movieInfo -> MovieBasicInfo.builder()
                .id(movieInfo.getId())
                .name(movieInfo.getName())
                .desc(movieInfo.getDesc())
                .director(movieInfo.getDirector())
                .actor(movieInfo.getActor())
                .duration(movieInfo.getDuration())
                .year(movieInfo.getYear())
                .score(movieInfo.getScore()).build()).collect(Collectors.toList());
    }
}
