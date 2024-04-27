package org.zyf.javabasic.bizthreadpool.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: zyfboot-javabasic
 * @description: 业务返回类型
 * @author: zhangyanfeng
 * @create: 2024-04-27 10:53
 **/
public enum ResultType {
    RECOMMEND(2053, "推荐", "General", "结合用户喜好进行真实推荐"),
    VIDEO(2054, "视频", "Entertainment", "观看各类视频内容"),
    LIVE(2055, "直播", "Entertainment", "实时直播观看"),
    FASHION(2056, "穿搭", "Fashion", "关于时尚搭配的建议和趋势"),
    BOOK(2057, "读书", "Education", "推荐书籍与阅读体验"),
    FUNNY(2058, "搞笑", "Entertainment", "幽默搞笑的视频与图片"),
    MOVIE(2059, "影视", "Entertainment", "电影电视剧的推荐与评论"),
    WALLPAPER(2060, "壁纸", "Lifestyle", "高清壁纸资源"),
    FITNESS(2061, "健身塑型", "Health", "健身指导与塑形建议"),
    TRAVEL(2062, "旅行", "Lifestyle", "旅游攻略与景点推荐"),
    SPORTS(2063, "竞技体育", "Sports", "体育赛事与运动员动态"),
    TECH_DIGITAL(2064, "科技数码", "Technology", "数码产品的最新资讯"),
    MENS_GROOMING(2065, "男士理容", "Fashion", "男性美容与个人护理"),
    SPORT(2066, "运动", "Sports", "运动健身的指导与好处"),
    ANIME(2067, "动漫", "Entertainment", "动画与漫画推荐"),
    HOME_DECOR(2068, "家装", "Lifestyle", "家居装修与设计灵感"),
    TRENDY_SHOES(2069, "潮鞋", "Fashion", "流行鞋款介绍与评测"),
    STUDY(2070, "学习", "Education", "学习资源与方法分享"),
    TECH_POPULAR_SCIENCE(2071, "科技科普", "Technology", "科学技术的有趣知识"),
    FOOD(2072, "美食", "Food", "美食制作与分享"),
    CAR(2073, "汽车", "Automotive", "汽车资讯与评测"),
    MUSIC(2074, "音乐", "Entertainment", "音乐新闻与推荐"),
    HOME_FURNISHING(2075, "家居", "Lifestyle", "家具选购与生活方式");

    private final int code;
    private final String description;
    private final String category;
    private final String detail;

    private ResultType(int code, String description, String category, String detail) {
        this.code = code;
        this.description = description;
        this.category = category;
        this.detail = detail;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getDetail() {
        return detail;
    }

    public static ResultType findByCode(int code) {
        for (ResultType type : ResultType.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public static int count() {
        return ResultType.values().length;
    }

    public static List<ResultType> findByCategory(String category) {
        return Arrays.stream(ResultType.values())
                .filter(type -> type.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}

