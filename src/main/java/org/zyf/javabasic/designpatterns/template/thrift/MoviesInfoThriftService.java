package org.zyf.javabasic.designpatterns.template.thrift;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author yanfengzhang
 * @description 模拟实际电影thrift接口
 * @date 2022/3/18  23:39
 */
@Service
@Slf4j
public class MoviesInfoThriftService {

    private static Map<Integer, String> descWords = Maps.newHashMap();

    static {
        descWords.put(1, "人生若无悔，那该多无趣啊。——《一代宗师》");
        descWords.put(2, "冬天冷，是因为为了让我们懂得周围人的温暖，是多么的珍贵。——《熔炉》");
        descWords.put(3, "你要尽全力保护你的梦想。那些嘲笑你梦想的人，他们注定失败，他们想把你变成和他们一样。我坚信，只要心中有梦想，我就会与众不同。你也是。——《当幸福来敲门》");
        descWords.put(5, "成功的含义不在于得到什么，而是在于你从那个奋斗的起点走了多远。——《心灵捕手》");
        descWords.put(6, "让朋友低估你的优点，让敌人高估你的缺点。——《教父》");
        descWords.put(7, "所有大人都曾是小孩，虽然只有少数人记得。——《小王子》");
        descWords.put(8, "记住，希望是好事，也许是人间至善，而美好的事物永不消逝。——《肖申克的救赎》");
        descWords.put(9, "当你不能再拥有的时候，唯一可以做的，就是令自己不要忘记。——《东邪西毒》");
        descWords.put(10, "我们要学会珍惜我们生活的每一天，因为，这每一天的开始，都将是我们余下生命之中的第一天。除非我们即将死去。——《美国美人》");
    }

    public MoviesResult getMoviesByConditionPage(MoviesConditions moviesConditions) {
        if (!checkPosInteger(moviesConditions.getPageSize())) {
            return null;
        }

        List<MovieInfo> movieInfos = Lists.newArrayList();
        List<Long> movieIdList = Lists.newArrayList();
        /*模拟数据，返回第一页数据和指定的页面大小*/
        if (moviesConditions.getPageNo() == 1) {
            for (int i = 1; i <= moviesConditions.getPageSize(); i++) {
                movieIdList.add((long) i);
                movieInfos.add(MovieInfo.builder()
                        .id((long) i)
                        .name("张彦峰喜欢的电影" + i)
                        .desc(descWords.get(i))
                        .actor("张彦峰")
                        .director("张彦峰")
                        .belong(2)
                        .score(new Random().nextInt(10) % (10 - i + 1) + i).build());
            }
        }
        if (moviesConditions.getPageNo() == 2) {
            for (int i = 6; i <= moviesConditions.getPageSize() + 5; i++) {
                movieIdList.add((long) i);
                movieInfos.add(MovieInfo.builder()
                        .id((long) i)
                        .name("张彦峰喜欢的电影" + i)
                        .desc(descWords.get(i))
                        .actor("张彦峰")
                        .director("张彦峰")
                        .belong(2)
                        .score(new Random().nextInt(10) % (10 - i + 1) + i).build());
            }
        }
        return MoviesResult.builder().total(5).movieIdList(movieIdList).movieInfos(movieInfos).build();
    }

    public MoviesResult getMoviesByConditionScroll(MoviesConditions moviesConditions) {
        if (!checkPosInteger(moviesConditions.getPageSize()) || moviesConditions.getScrollId() < 0) {
            return null;
        }

        List<MovieInfo> movieInfos = Lists.newArrayList();
        List<Long> movieIdList = Lists.newArrayList();
        /*模拟数据，返回第一页数据和指定的页面大小*/
        if (moviesConditions.getScrollId() == 0) {
            for (int i = 1; i <= moviesConditions.getPageSize(); i++) {
                movieIdList.add((long) i);
                movieInfos.add(MovieInfo.builder()
                        .id((long) i)
                        .name("张彦峰喜欢的电影" + i)
                        .desc(descWords.get(i))
                        .actor("张彦峰")
                        .director("张彦峰")
                        .belong(2)
                        .score(new Random().nextInt(10) % (10 - i + 1) + i).build());
            }
            return MoviesResult.builder().total(5).movieIdList(movieIdList).movieInfos(movieInfos).nextScrollId(1L).build();
        }
        if (moviesConditions.getScrollId() == 1) {
            for (int i = 6; i <= moviesConditions.getPageSize() + 5; i++) {
                movieIdList.add((long) i);
                movieInfos.add(MovieInfo.builder()
                        .id((long) i)
                        .name("张彦峰喜欢的电影" + i)
                        .desc(descWords.get(i))
                        .actor("张彦峰")
                        .director("张彦峰")
                        .belong(2)
                        .score(new Random().nextInt(10) % (10 - i + 1) + i).build());
            }
        }
        return MoviesResult.builder().total(5).movieIdList(movieIdList).movieInfos(movieInfos).nextScrollId(-1L).build();
    }

    /**
     * ID信息合法性:必须为正整数
     *
     * @param id id信息
     * @return true-合法；false-非法
     */
    public static boolean checkPosInteger(Integer id) {
        return null != id && id > 0L;
    }


}
