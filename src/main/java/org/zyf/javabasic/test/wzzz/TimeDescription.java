package org.zyf.javabasic.test.wzzz;

import com.nlf.calendar.Lunar;
import com.nlf.calendar.Solar;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * @program: zyfboot-javabasic
 * @description: TimeDescription
 * @author: zhangyanfeng
 * @create: 2025-01-11 18:36
 **/
public class TimeDescription {

    // 随机返回时间描述
    public static String getRandomTimeDescription(String inputDateTime, int optionNew, boolean isCurrentTime) {
        Random random = new Random();
        LocalDateTime now = LocalDateTime.now();

        if (isCurrentTime) {
            // 定义目标数字数组
            int[] numbers = {1, 9, 10, 11, 12};
            // 返回当前时间的表达
            return getCurrentTimeDescription(now, getRandomNumber(numbers), random);
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime inputTime = LocalDateTime.parse(inputDateTime, formatter);
            // 定义目标数字数组
            int[] numbers = {2, 3, 4, 5, 6, 7, 8};
            // 返回距离当前时间的表达
            return getTimeDifferenceDescription(inputTime, now, getRandomNumber(numbers), random);
        }
    }

    public static int getRandomNumber(int[] numbers) {

        // 创建随机数生成器
        Random random = new Random();

        // 随机生成索引并返回对应的数字
        return numbers[random.nextInt(numbers.length)];
    }

    // 获取当前时间的描述
    private static String getCurrentTimeDescription(LocalDateTime now, int option, Random random) {
        switch (option) {
            case 1:
                String[] timeOfDay = {
                        "上午", "下午", "清晨", "傍晚", "午夜",
                        "早晨", "中午", "黄昏", "黎明", "夜晚", "凌晨",
                        "拂晓", "破晓", "午后", "薄暮", "初更", "二更", "三更", "四更", "五更",
                        "饭点", "工作时间", "下班时间", "休息时间",
                        "夏夜", "冬晨", "秋暮", "春晓",
                        "茶余饭后", "梦醒时分", "夜读时分", "星月交辉时"
                };
                String dayOfWeek = getDayOfWeek(now);
                String prefix = random.nextBoolean() ? "周" : "星期";
                String time = random.nextBoolean() ?
                        now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")) :
                        now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm"));
                return time + " " + prefix + dayOfWeek;
                //return prefix + dayOfWeek + timeOfDay[random.nextInt(timeOfDay.length)];
            case 9:
                return now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
            case 10:
                return now.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm"));
            case 11:
                return getAncientChineseYearAndMonth(now);
            case 12:
                String ancientYear = getAncientChineseYear(now);
                String modernMonthTime = now.format(DateTimeFormatter.ofPattern("MM月HH:mm"));
                return ancientYear + " " + modernMonthTime;
            default:
                return "";
        }
    }

    // 获取距离当前时间的描述
    private static String getTimeDifferenceDescription(LocalDateTime inputTime, LocalDateTime now, int option, Random random) {
        Duration duration = Duration.between(inputTime, now);
        long totalDays = duration.toDays();
        long totalHours = duration.toHours();
        long totalWeeks = totalDays / 7;
        long totalYears = totalDays / 365;
        long remainingMonths = (totalDays % 365) / 30;
        long remainingWeeks = (totalDays % 365) % 30 / 7;
        long remainingDays = (totalDays % 365) % 30 % 7;

        switch (option) {
            case 2:
                return totalDays + "天";
            case 3:
                return totalDays + "天" + (totalHours % 24) + "小时";
            case 4:
                return totalWeeks + "周";
            case 5:
                return totalYears + "年" + (totalDays % 365 / 7) + "周";
            case 6:
                return totalYears + "年" + remainingMonths + "月";
            case 7:
                return totalYears + "年" + remainingMonths + "月" + remainingWeeks + "周";
            case 8:
                String dayLabel = random.nextBoolean() ? "天" : "日";
                return totalYears + "年" + remainingMonths + "月" + remainingWeeks + "周" + remainingDays + dayLabel;
            default:
                return "";
        }
    }

    // 获取中国古代年份和月份
    private static String getAncientChineseYearAndMonth(LocalDateTime now) {
        Solar solar = new Solar(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        Lunar lunar = solar.getLunar();
        return lunar.getYearInGanZhi() + "年" + lunar.getMonthInChinese() + "月";
    }

    // 获取中国古代年份
    private static String getAncientChineseYear(LocalDateTime now) {
        Solar solar = new Solar(now.getYear(), now.getMonthValue(), now.getDayOfMonth());
        Lunar lunar = solar.getLunar();
        return lunar.getYearInGanZhi() + "年";
    }

    // 获取星期几
    private static String getDayOfWeek(LocalDateTime now) {
        String dayOfWeek = now.getDayOfWeek().getDisplayName(java.time.format.TextStyle.FULL, java.util.Locale.CHINESE);
        return dayOfWeek.replace("星期", "");
    }

    public static void main(String[] args) {
        // 测试随机方法
        String inputDateTime = "2020-03-27 19:04:30";
        System.out.println(getRandomTimeDescription(inputDateTime, 1, true));  // 当前时间描述
        System.out.println(getRandomTimeDescription(inputDateTime, 3, false)); // 距离当前时间描述
    }
}

