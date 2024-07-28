package org.zyf.javabasic.common;

/**
 * @program: zyfboot-javabasic
 * @description: ArticleStats
 * @author: zhangyanfeng
 * @create: 2024-07-20 19:02
 **/
public class ArticleStats {
    private int views;
    private int likes;
    private int favorites;

    public ArticleStats(int views, int likes, int favorites) {
        this.views = views;
        this.likes = likes;
        this.favorites = favorites;
    }

    public int getViews() {
        return views;
    }

    public int getLikes() {
        return likes;
    }

    public int getFavorites() {
        return favorites;
    }

    @Override
    public String toString() {
        return "ArticleStats{" +
                "views=" + views +
                ", likes=" + likes +
                ", favorites=" + favorites +
                '}';
    }
}
