package org.zyf.javabasic.test.wzzz;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: 使用 Solar 和 Lunar 类进行阳历到农历的转换
 * @author: zhangyanfeng
 * @create: 2025-01-11 18:26
 **/
public class AncientChineseTime {
    // 天干
    private static final String[] TIANGAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};
    // 地支
    private static final String[] DIZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};
    // 十二时辰
    private static final String[] SHICHEN = {"子时", "丑时", "寅时", "卯时", "辰时", "巳时", "午时", "未时", "申时", "酉时", "戌时", "亥时"};

    public static String getAncientTime() {
        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 转换为农历日期
        Solar solar = new Solar(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        Lunar lunar = solar.getLunar();

        // 获取干支年、月、日
        String ganzhiYear = lunar.getYearInGanZhi();
        String ganzhiMonth = lunar.getMonthInGanZhi();
        String ganzhiDay = lunar.getDayInGanZhi();

        // 获取时辰对应的干支
        int hour = now.getHour();
        String ganzhiHour = getGanZhiHour(hour, lunar.getDayGanIndex());

        // 随机选取一个时辰
        String randomShichen = getRandomShichen();

        // 格式化时间（时分秒）
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // 返回完整的中国古代时间表示
        return String.format("农历%s年%s月%s日 %s %s",
                lunar.getYearInChinese(),
                lunar.getMonthInChinese(),
                lunar.getDayInChinese(),
                ganzhiHour,
                randomShichen);
    }

    private static String getGanZhiHour(int hour, int dayGanIndex) {
        // 计算时辰地支索引
        int dizhiIndex = hour / 2;
        // 计算天干索引（公式：(日干索引 * 2 + 时辰地支索引) % 10）
        int tianganIndex = (dayGanIndex * 2 + dizhiIndex) % 10;
        return TIANGAN[tianganIndex] + DIZHI[dizhiIndex];
    }

    private static String getRandomShichen() {
        // 随机选取一个时辰
        Random random = new Random();
        return SHICHEN[random.nextInt(SHICHEN.length)];
    }

    // 新增：根据输入的时间字符串获取农历时间
    public static String getAncientTimeFromString(String timeString) {
        // 将输入的时间字符串解析为 LocalDateTime 对象
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(timeString, formatter);

        // 转换为农历日期
        Solar solar = new Solar(dateTime.getYear(), dateTime.getMonthValue(), dateTime.getDayOfMonth());
        Lunar lunar = solar.getLunar();

        // 获取干支年、月、日
        String ganzhiYear = lunar.getYearInGanZhi();
        String ganzhiMonth = lunar.getMonthInGanZhi();
        String ganzhiDay = lunar.getDayInGanZhi();

        // 获取时辰对应的干支
        int hour = dateTime.getHour();
        String ganzhiHour = getGanZhiHour(hour, lunar.getDayGanIndex());

        // 随机选取一个时辰
        String randomShichen = getRandomShichen();

        // 格式化时间（时分秒）
        String time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        // 返回完整的中国古代时间表示
        return String.format("农历%s年%s月%s日 %s %s",
                lunar.getYearInChinese(),
                lunar.getMonthInChinese(),
                lunar.getDayInChinese(),
                ganzhiHour,
                randomShichen);
    }

    public static void main(String[] args) {
        System.out.println(getAncientTime());
    }
}

