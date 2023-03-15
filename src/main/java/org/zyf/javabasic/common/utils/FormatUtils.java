package org.zyf.javabasic.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanfengzhang
 * @description
 * @date 2023/3/15  23:00
 */
public class FormatUtils {
    public static final long HOUR = 60 * 60L;

    public static long timeFormatter(String strTime) {
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(strTime));
        } catch (ParseException e) {

        }
        long time = c.getTimeInMillis();
        time /= 1000;
        return time;
    }

    public static long timeFormatterAddOneDay(String strTime) {

        Calendar c = Calendar.getInstance();
        try {
            c.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(strTime));
            c.add(Calendar.DATE, 1);
        } catch (ParseException e) {

        }
        long time = c.getTimeInMillis();
        time /= 1000;
        return time;
    }

    public static long getTimeBeforeOneWeek() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) - 7);
        long time = c.getTimeInMillis();
        time /= 1000;
        return time;
    }

    public static long getTimeBeforeOneMonth() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.DATE, c.get(Calendar.DATE) - 31);
        long time = c.getTimeInMillis();
        time /= 1000;
        return time;
    }

    public static long getNow() {
        return System.currentTimeMillis() / 1000;
    }

    public static boolean isMobiPhoneNum(String telNum) {
        if (StringUtils.isNotBlank(telNum)) {
            String regex = "^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(14[0-9]))\\d{8}$";
            Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher m = p.matcher(telNum);
            return m.matches();
        } else {
            return false;
        }
    }

    /**
     * 电话号码验证
     *
     * @param str
     * @return
     */
    public static boolean isTelephone(String str) {
        boolean flag = false;
        if (StringUtils.isBlank(str)) {
            return flag;
        }
        /**对于400的 特殊处理***/
        if (str.length() > 3 && str.startsWith("4")) {
            String subStr = str.substring(0, 3);
            if (subStr.equals("400")) {
                return true;
            }
        }

        Matcher m;
        Pattern p1 = Pattern.compile("^(010|02\\d|0[3-9]\\d{2})?\\d{6,8}$");  // 验证带区号的
        Pattern p2 = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");         // 验证没有区号的
        if (str.length() > 9) {
            m = p1.matcher(str);
            flag = m.matches();
        } else {
            m = p2.matcher(str);
            flag = m.matches();
        }
        return flag;
    }

    public static Integer getTodayLeftTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        long now = c.getTimeInMillis() / 1000;
        c.set(Calendar.DATE, c.get(Calendar.DATE) + 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        long todayEnd = c.getTimeInMillis() / 1000;
        long result = todayEnd - now;
        return (int) result;
    }


    public static String transferTimetoSecond(String timeStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = dateFormat.parse(timeStr);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer result = calendar.get(Calendar.HOUR_OF_DAY) * 3600 + calendar.get(Calendar.MINUTE) * 60;
        return result.toString();
    }

    public static String transferSecondtoTime(String timeStr) {
        int result = Integer.parseInt(timeStr) * 1000 - TimeZone.getDefault().getRawOffset();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(result);
    }

    public static boolean isInAnHour(long start, long end) {
        return isInAllowTime(start, end, HOUR);
    }

    public static boolean isInAllowTime(long start, long end, long limit) {
        if (end - start <= limit) {
            return true;
        }
        return false;
    }

    /**
     * 返回当前时间的秒数
     *
     * @return
     */
    public static int unixTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * @param date
     * @return yyyy-MM-dd HH:mm:ss的字符串
     */
    public static String Date2StringSec(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    /**
     * @param seconds
     * @return 当前时间的秒数
     */
    public static String Date2StringSec(Integer seconds) {
        return Date2StringSec(fromUnixTime(seconds));
    }

    /**
     * 把表转换为Date
     *
     * @param seconds
     * @return
     */
    public static Date fromUnixTime(Integer seconds) {
        return new Date(seconds * 1000L);
    }
}
