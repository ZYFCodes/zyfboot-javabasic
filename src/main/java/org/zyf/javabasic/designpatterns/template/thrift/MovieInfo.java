package org.zyf.javabasic.designpatterns.template.thrift;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author yanfengzhang
 * @description 电影资源信息（只是样板）
 * @date 2022/3/18  23:33
 */
@Data
@Builder
public class MovieInfo {
    /**
     * ID编号
     */
    private Long id;
    /**
     * 电影名称
     */
    private String name;
    /**
     * 电影简介
     */
    private String desc;
    /**
     * 电影导演
     */
    private String director;
    /**
     * 电影演员
     */
    private String actor;
    /**
     * 电影综合评分
     */
    private Integer score;
    /**
     * 电影时长
     */
    private Integer duration;
    /**
     * 归属排序：1-热播榜，2-好评榜，3-新上线，4-爱奇艺自制（热播），5-腾讯自制（热播），6-优酷自制（热播）
     */
    private int belong;
    /**
     * 播放类型：1-剧情，2-喜剧，3-动作，4-爱情，5-惊悚，6-犯罪，7-悬疑，8-战争，9-科幻，10-动画，11-恐怖
     * 12-家庭，13-传记，14-冒险，15-奇幻，16-武侠，17-历史，18-运动，19-歌舞，20-音乐，21-记录
     * 22-伦理，23-西部
     */
    private List<Integer> playTypes;
    /**
     * 电影产地归类：1-内地，2-中国香港，3-美国，4-欧洲，5-日本，6-韩国，7-英国，8-法国，9-德国，10-泰国
     * 11-印度，12-意大利，13-西班牙，14-加拿大，15-澳大利亚，16-中国台湾，17-拉丁美洲，18-越南
     * 19-俄罗斯，20-其他
     */
    private int productPlace;
    /**
     * 电影源：1-院线，2-蓝光，3-奥斯卡，4-自制电影，5-独播，6-烂片，7-网络电影，8-巨制
     */
    private List<Integer> sources;
    /**
     * 付费类型：1-免费，2-付费，3-VIP
     */
    private int paymentType;
    /**
     * 上映年份
     */
    private int year;
}
