package org.zyf.javabasic.dispatcher.enums;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @program: zyfboot-javabasic
 * @description: 分发类型定义
 * 枚举了当前支持的各种分发类型；
 * * PS: 数字是为了给DB存储使用的，使用列表存储、存储直接使用name即可
 * * 分发码是一个唯一的标识符，用于指定具体的分发操作。
 * * 是否开放表示该分发类型是否对外开放。
 * * 描述是该分发类型的说明文字。
 * @author: zhangyanfeng
 * @create: 2024-04-21 00:27
 **/
public enum DistributionType {

    TOPIC(1000, true, "话题", "用于分发话题内容"),
    NEWS(2000, true, "资讯", "用于分发新闻资讯内容"),
    FUND(3001, true, "基金", "用于分发基金相关内容"),
    STOCK(3002, true, "股票", "用于分发股票相关内容"),
    IMAGE(4001, true, "图片", "用于分发图片相关内容"),
    AUDIO(4002, true, "音频", "用于分发音频相关内容"),
    VIDEO(4003, true, "视频", "用于分发视频相关内容"),
    MEDIE(4004, true, "短视频", "用于分发短视频相关内容"),


    //-----------------------------//
    // 在这里添加更多的分发类型     //
    //-----------------------------//

    EMPTY(-999, true, "EMPTY", "空");

    private static final Map<Integer, DistributionType> DISPATCH_CODE_MAP = Maps.newHashMap();
    private static final Map<String, DistributionType> NAME_MAP = Maps.newHashMap();

    static {
        for (DistributionType dt : DistributionType.values()) {
            DISPATCH_CODE_MAP.put(dt.dispatchCode, dt);
            NAME_MAP.put(dt.name(), dt);
        }
    }

    private final int dispatchCode;
    private final boolean open;
    private final String description;

    DistributionType(int dispatchCode, boolean open, String description, String 空) {
        this.dispatchCode = dispatchCode;
        this.open = open;
        this.description = description;
    }

    /**
     * 获取分发类型的分发码。
     *
     * @return 分发类型的分发码。
     */
    public int getDispatchCode() {
        return dispatchCode;
    }

    /**
     * 判断分发类型是否对外开放。
     *
     * @return 如果分发类型对外开放，则返回 true；否则返回 false。
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * 获取分发类型的描述。
     *
     * @return 分发类型的描述。
     */
    public String getDescription() {
        return description;
    }

    /**
     * 根据分发码获取对应的分发类型枚举实例。
     *
     * @param dispatchCode 分发码。
     * @return 对应的分发类型枚举实例，如果不存在对应的分发类型，则返回 EMPTY 枚举实例。
     */
    public static DistributionType valueOf(int dispatchCode) {
        return DISPATCH_CODE_MAP.getOrDefault(dispatchCode, EMPTY);
    }

    /**
     * 根据分发类型的名称获取对应的分发类型枚举实例。
     *
     * @param name 分发类型的名称。
     * @return 对应的分发类型枚举实例，如果名称为空或不存在对应的分发类型，则返回 null。
     */
    public static DistributionType parseByName(String name) {
        return StringUtils.isBlank(name) ? null : NAME_MAP.get(name);
    }
}
