package org.zyf.javabasic.generic;

/**
 * @author yanfengzhang
 * @description 更新车辆位置信息
 * @date 2021/1/28  23:47
 */
public enum CheckBizType {
    /**
     * 电视剧审核业务
     */
    TV_SERIES(1, "tv_series"),
    /**
     * 电影审核业务
     */
    MOVIE(2, "movie"),
    /**
     * 综艺审核业务
     */
    VARIETY_SHOW(3, "variety_show"),
    /**
     * 动画审核业务
     */
    ANIMATION(4, "animation"),
    /**
     * 游戏审核业务
     */
    GAME(5, "game"),
    /**
     * 音乐审核业务
     */
    MUSIC(6, "music");

    private final int type;
    private String name;

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    CheckBizType(int type) {
        this.type = type;
    }

    CheckBizType(int type, String name) {
        this.name = name;
        this.type = type;
    }

    /**
     * @param type 基本类型
     * @return 工作流枚举信息
     * @description 按照基本类型返回对应的枚举信息
     * @author yanfengzhang
     */
    public static CheckBizType getEnumByType(Integer type) {
        CheckBizType checkBizType = null;
        for (CheckBizType tempEnum : CheckBizType.values()) {
            if (tempEnum.getType() == type) {
                checkBizType = tempEnum;
                return checkBizType;
            }
        }
        return checkBizType;
    }

    /**
     * @param name 基本类型
     * @return 工作流枚举信息
     * @description 按照基本类型返回对应的枚举信息
     * @author yanfengzhang
     */
    public static CheckBizType getEnumByName(String name) {
        CheckBizType checkBizType = null;
        for (CheckBizType tempEnum : CheckBizType.values()) {
            if (tempEnum.getName().equals(name)) {
                checkBizType = tempEnum;
                return checkBizType;
            }
        }
        return checkBizType;
    }
}
