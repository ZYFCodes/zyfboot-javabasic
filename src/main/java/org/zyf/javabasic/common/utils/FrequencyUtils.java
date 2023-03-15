package org.zyf.javabasic.common.utils;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/15  23:02
 */
public class FrequencyUtils {
    /**
     * 价格修改频率
     *
     * @param unixTime       当前时间
     * @param lastModifyTime 最新修改时间
     * @return 修改频率
     */
    public static int getPriceFrequency(int unixTime, long lastModifyTime) {
        long timeDur = unixTime - lastModifyTime;
        return (int) (timeDur / 86400);
    }
}
