package org.zyf.javabasic.dynamicbizvalidator.dynamicdeal;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zyf.javabasic.common.contants.CommonConstants;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yanfengzhang
 * @description 静态校验
 * @date 2023/3/15  23:32
 */
public class ProductStaticValidator {

    private static Logger log = LoggerFactory.getLogger(ProductStaticValidator.class);

    /**
     * 可售时间格式校验
     *
     * @param shippingTimeX
     * @return
     */
    public static boolean shippingTimeXCheck(String shippingTimeX) {
        if (StringUtils.isBlank(shippingTimeX)) {
            return true;
        }
        if ("[[],[],[],[],[],[],[]]".equals(shippingTimeX)) {
            return false;
        }
        return "-".equals(shippingTimeX) || poiSpTimeXValidForSg(shippingTimeX, 0L) || poiSpTimeXValid(shippingTimeX, 0L);
    }

    public static String getPreOrderDealTime(String shippingTime) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        return getPreOrderDealTime(shippingTime, hour, minute);
    }

    public static String getPreOrderDealTime(String shippingTime, int hour, int minute) {

        String range = shippingTime;
        if (StringUtils.isEmpty(range)) {
            return null;
        }

        /** 起始配送时间 */
        String beg_time = "";
        /** 去掉所有空格 */
        range = trimAllWhitespace(range);
        String[] ranges = range.split(CommonConstants.HALF_WIDTH_COMMA);
        for (String section : ranges) {
            if (StringUtils.isEmpty(section)) {
                continue;
            }
            String[] time = section.split(CommonConstants.CONNECTION_SIGN);
            if (time.length < 2) {
                continue;
            }
            String stime = time[0];
            if (StringUtils.isEmpty(stime)) {
                continue;
            }
            /*比起始配送时间小，保存为开始配送时间 */
            if (compare(hour, minute, stime) < 0) {
                beg_time = stime;
                break;
            }
        }
        return beg_time;
    }

    public static String getPreOrderDealTimeX(String shippingTime, String shippingTimeX) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        return getPreOrderDealTimeX(shippingTime, shippingTimeX, hour, minute);
    }

    /**
     * 新版门店"xx点开始送餐”
     *
     * @param shippingTime  老版配送时间
     * @param shippingTimeX 新版配送时间
     * @param hour          兼容老版的hour
     * @param minute        兼容老版的minute
     * @return because shippingTimeX extends shippingTime
     */
    public static String getPreOrderDealTimeX(String shippingTime, String shippingTimeX, int hour, int minute) {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_WEEK);
        day = (day + 5) % 7;
        String[][] arr = JSON.parseObject(shippingTimeX, String[][].class);

        if (arr[day].length == 0) {
            /*shippingTimeX don't have implements*/
            return null;
        }

        /*起始配送时间 */
        String beg_time = "";
        String[] ranges = arr[day];
        for (String section : ranges) {
            if (StringUtils.isEmpty(section)) {
                continue;
            }

            String[] time = section.split(CommonConstants.CONNECTION_SIGN);
            if (time.length < 2) {
                continue;
            }

            String stime = time[0];
            if (StringUtils.isEmpty(stime)) {
                continue;
            }

            /*比起始配送时间小，保存为开始配送时间 */
            if (compare(hour, minute, stime) < 0) {
                beg_time = stime;
                break;
            }
        }
        return beg_time;
    }

    public static boolean isPreOrder(String shippingTime, int hour, int minute) {
        String range = trimAllWhitespace(shippingTime);
        /*范围为空时，直接返回*/
        if (StringUtils.isEmpty(range)) {
            return false;
        }
        /*分离商家营业时间段，一天开始营业的时间和最后打烊的时间*/
        String[] openTimes = range.split(CommonConstants.HALF_WIDTH_COMMA);
        String[] timeSlot = openTimes[openTimes.length - 1].split(CommonConstants.CONNECTION_SIGN);
        String lastCloseTime = "";
        if (timeSlot.length < 1) {
            return false;
        } else {
            lastCloseTime = timeSlot[1];
        }
        /*预下单的最早时间是七点*/
        String openPreOrderTime = "7:00";
        /*默认的前提是可以下单，则只需要判断下单的时间是否在营业时间内，不在营业时间内则是预订单*/
        for (String t : openTimes) {
            if (StringUtils.isNotEmpty(t)) {
                String startTime = t.substring(0, t.indexOf(CommonConstants.CONNECTION_SIGN));
                String endTime = t.substring(t.indexOf(CommonConstants.CONNECTION_SIGN) + 1);
                if ((compare(hour, minute, startTime) >= 0) && compare(hour, minute, endTime) <= 0) {//在商家的营业时间内
                    return false;
                }
                if ((compare(hour, minute, openPreOrderTime) < 0) || compare(hour, minute, lastCloseTime) > 0) {//在七点之前，打烊之后
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isPreOrderX(String shippingTime, String shippingTimeX) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        return isPreOrderX(shippingTime, shippingTimeX, hour, minute);
    }

    /**
     * 新版门店是否预下单
     *
     * @param shippingTime  老版配送时间
     * @param shippingTimeX 新版配送时间
     * @param hour          兼容老版的hour
     * @param minute        兼容老版的minute
     * @return because shippingTimeX extends shippingTime
     */
    public static boolean isPreOrderX(String shippingTime, String shippingTimeX, int hour, int minute) {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_WEEK);
        day = (day + 5) % 7;
        String[][] arr = JSON.parseObject(shippingTimeX, String[][].class);
        if (arr[day].length == 0) {
            /*shippingTimeX don't have implements*/
            return false;
        }

        try {
            /*分离商家营业时间段，一天开始营业的时间和最后打烊的时间*/
            String[] openTimes = arr[day];
            String[] timeSlot = openTimes[openTimes.length - 1].split(CommonConstants.CONNECTION_SIGN);
            String lastCloseTime = "";
            if (timeSlot.length < 1) {
                return false;
            } else {
                lastCloseTime = timeSlot[1];
            }
            /*预下单的最早时间是七点*/
            String openPreOrderTime = "7:00";
            /*默认的前提是可以下单，则只需要判断下单的时间是否在营业时间内，不在营业时间内则是预订单*/
            for (String t : openTimes) {
                if (StringUtils.isNotEmpty(t)) {
                    String startTime = t.substring(0, t.indexOf(CommonConstants.CONNECTION_SIGN));
                    String endTime = t.substring(t.indexOf(CommonConstants.CONNECTION_SIGN) + 1);
                    if ((compare(hour, minute, startTime) >= 0) && compare(hour, minute, endTime) <= 0) {//在商家的营业时间内
                        return false;
                    }
                    if ((compare(hour, minute, openPreOrderTime) < 0) || compare(hour, minute, lastCloseTime) > 0) {//在七点之前，打烊之后
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            String errorInfo = String.format("预下单信息解析错误: shippingTimeX=%s", shippingTimeX);
            log.error(errorInfo, e);
            return false;
        }
    }

    public static String getTodayShippingTime(String shippingTime, String shippingTimeX) {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_WEEK);
        day = (day + 5) % 7;
        String[][] arr = JSON.parseObject(shippingTimeX, String[][].class);
        if (arr[day].length == 0) {
            /*shippingTimeX don't have implements*/
            return null;
        }
        return JSON.toJSONString(arr[day]).replace("[", "").replace("]", "").replace("\"", "");
    }

    public static String remindWhenUnOpen(String shppingTime, int n_hour, int n_minute) {

        shppingTime = trimAllWhitespace(shppingTime);
        String[] ts = shppingTime.split(CommonConstants.HALF_WIDTH_COMMA);

        // 该字段表示是否在真实的营业时间内
        boolean isOpen = false;
        String tt = "";

        for (String t : ts) {
            if (StringUtils.isNotEmpty(t)) {
                String t_b = t.substring(0, t.indexOf(CommonConstants.CONNECTION_SIGN));
                String t_e = t.substring(t.indexOf(CommonConstants.CONNECTION_SIGN) + 1);
                if (compare(n_hour, n_minute, t_b) < 0) {
                    isOpen = false;
                    tt = t_b;
                    break;
                } else if (compare(n_hour, n_minute, t_e) > 0) {
                    continue;
                } else {
                    isOpen = true;
                    break;
                }
            }
        }

        // 在非营业时间内，同时在关店时间之前
        if (!isOpen && StringUtils.isNotEmpty(tt)) {
            int index = tt.indexOf(":");
            if (index > 0) {
                return "餐厅尚未开始营业，您的订单会在餐厅开始营业（" + tt.substring(0, index) + ":" + tt.substring(index + 1) + "）之后处理。";
            }
            return "餐厅尚未开始营业，您的订单会在餐厅开始营业（" + tt + ":00）之后处理。";
        }
        return "";
    }

    public static String getNextShipTime(String shippingtime) {
        shippingtime = trimAllWhitespace(shippingtime);
        if (StringUtils.isEmpty(shippingtime)) {
            return null;
        }
        String[] ranges = shippingtime.split(CommonConstants.HALF_WIDTH_COMMA);
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        for (String range : ranges) {
            if (StringUtils.isNotEmpty(range)) {
                String t_b = range.substring(0, range.indexOf(CommonConstants.CONNECTION_SIGN));
                String t_e = range.substring(range.indexOf(CommonConstants.CONNECTION_SIGN) + 1);
                if (compare(hour, minute, t_b) < 0) {
                    return t_b;
                } else if (compare(hour, minute, t_e) > 0) {
                    continue;
                } else {
                    break;
                }
            }
        }
        return null;
    }

    public static boolean isInShippingTime(String shippingTime, Integer pre_book, int hour, int minute) {
        try {
            String range = trimAllWhitespace(shippingTime);

            /*范围为空时，直接返回*/
            if (StringUtils.isEmpty(range)) {
                return false;
            }

            String[] openTimes = range.split(CommonConstants.HALF_WIDTH_COMMA);

            /*商家不接受预定*/
            if (pre_book == null || pre_book.equals(0)) {
                for (String t : openTimes) {
                    if (StringUtils.isNotEmpty(t)) {
                        String t_b = t.substring(0, t.indexOf(CommonConstants.CONNECTION_SIGN));
                        String t_e = t.substring(t.indexOf(CommonConstants.CONNECTION_SIGN) + 1);
                        if (compare(hour, minute, t_b) < 0) {
                            return false;
                        } else if (compare(hour, minute, t_e) > 0) {
                            continue;
                        } else {
                            return true;
                        }
                    }
                }
                return false;
            }

            /*商家接受预定*/
            String[] timeSlot = openTimes[openTimes.length - 1].split(CommonConstants.CONNECTION_SIGN);
            String lastCloseTime = "";
            if (timeSlot.length < 1) {
                return false;
            } else {
                lastCloseTime = timeSlot[1];
            }
            String openPreOrderTime = "7:00";

            if (StringUtils.isEmpty(lastCloseTime)) {
                return false;
            }
            for (String t : openTimes) {
                if (StringUtils.isNotEmpty(t)) {
                    String t_b = t.substring(0, t.indexOf(CommonConstants.CONNECTION_SIGN));//营业时间段的开门时间
                    String t_e = t.substring(t.indexOf(CommonConstants.CONNECTION_SIGN) + 1);//营业时间段的关门时间
                    if (compare(hour, minute, t_b) >= 0 && compare(hour, minute, t_e) <= 0) {//当前时间在商家的营业时间内，显示营业中
                        return true;
                    }
                    if (compare(hour, minute, openPreOrderTime) >= 0 && compare(hour, minute, lastCloseTime) <= 0) {//当前时间在不在营业时间中但是在7点到打烊时间之间，可以预定，显示营业中
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            /*异常时，直接返回false */
            log.warn("异常：", ex);
            return false;
        }
    }

    public static String isShipWhenUnOpen(String shppingTime, int n_hour, int n_minute) {

        shppingTime = trimAllWhitespace(shppingTime);
        String[] ts = shppingTime.split(CommonConstants.HALF_WIDTH_COMMA);

        /*该字段表示是否在真实的营业时间内*/
        boolean isOpen = false;
        String tt = "";

        for (String t : ts) {
            if (StringUtils.isNotEmpty(t)) {
                String t_b = t.substring(0, t.indexOf(CommonConstants.CONNECTION_SIGN));
                String t_e = t.substring(t.indexOf(CommonConstants.CONNECTION_SIGN) + 1);
                if (compare(n_hour, n_minute, t_b) < 0) {
                    isOpen = false;
                    tt = t_b;
                    break;
                } else if (compare(n_hour, n_minute, t_e) > 0) {
                    continue;
                } else {
                    isOpen = true;
                    break;
                }
            }
        }

        /*在非营业时间内，同时在关店时间之前*/
        if (!isOpen && StringUtils.isNotEmpty(tt)) {
            return "餐厅尚未开始营业，您的订单提交后，餐厅会在开始营业后11分钟内处理!";
        }
        return "您的订单提交后，餐厅会在11分钟内处理!";
    }

    private static Integer compare(int n_hour, int n_minute, String t) {

        int t_b = 0;
        int t_e = 0;
        int k = t.indexOf(":");

        if (k > 0) {
            /*有小时和分钟*/
            t_b = Integer.parseInt(t.substring(0, k));
            t_e = Integer.parseInt(t.substring(k + 1));
        } else {
            /*只有小时*/
            t_b = Integer.parseInt(t);
            t_e = 0;
        }

        if (n_hour > t_b) {
            return 1;
        }

        if (n_hour == t_b) {
            if (n_minute > t_e) {
                return 1;
            }
            if (n_minute == t_e) {
                return 0;
            }
            return -1;
        }
        return -1;

    }

    private static String trimAllWhitespace(String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        int index = 0;
        while (sb.length() > index) {
            if (Character.isWhitespace(sb.charAt(index))) {
                sb.deleteCharAt(index);
            } else {
                index++;
            }
        }
        return sb.toString();
    }

    public static List<String> getArrivalRange(String shippingTimeX) {
        String todayShippingTime = getTodayShippingTime(null, shippingTimeX);
        if (StringUtils.isBlank(todayShippingTime)) {
            return null;
        }

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        List<TimeRange> openTimeRange = getOpenTimeRange(todayShippingTime, now);
        if (isPreOrderX(null, shippingTimeX, hour, minute)) {
            TimeRange timeRange = getLastTimeRange(openTimeRange, now);
            if (timeRange != null) {
                // if 间隔>30: 起始时间-30分钟;
                if (timeRange.btime + 30 * 60 < timeRange.etime) {
                    timeRange.btime += 30 * 60;
                }
                return genDeliveryTimeRange(timeRange);
            }
        }

        return null;
    }

    public static List<String> getArrivalPoints(String shippingTimeX) {
        String todayShippingTime = getTodayShippingTime(null, shippingTimeX);
        if (StringUtils.isBlank(todayShippingTime)) {
            return null;
        }

        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        List<TimeRange> OpenTimeRange = getOpenTimeRange(todayShippingTime, now);
        if (isPreOrderX(null, shippingTimeX, hour, minute)) {
            TimeRange timeRange = getLastTimeRange(OpenTimeRange, now);
            if (timeRange != null) {
                // if 间隔>30: 起始时间-30分钟;
                if (timeRange.btime + 30 * 60 < timeRange.etime) {
                    timeRange.btime += 30 * 60;
                }
                return genDeliveryTimePoints(timeRange);
            }
        }

        return null;
    }

    /**
     * @param shippingTime 格式：9:30-14:00,16:30-19:00,
     * @param status       waimai.wm_poi.status
     * @param preBook      waimai.wm_poi.pre_book
     * @return
     */
    public static int poiStatusView(String shippingTime, int status, int preBook) {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        if (status == 1 && isInShippingTime(shippingTime, 0, hour, minute)) {
            return 1;
        } else if (status == 1 && preBook == 1 && org.apache.commons.lang3.StringUtils.isNotEmpty(shippingTime) && org.apache.commons.lang3.StringUtils.isNotEmpty(getPreOrderDealTime(shippingTime))) {
            if (hour >= 0 && hour <= 6) {//00:00-6:59不接单
                return 3;
            }
            return 2;
        } else if (status == 3 || org.apache.commons.lang3.StringUtils.isEmpty(shippingTime) || org.apache.commons.lang3.StringUtils.isEmpty(getPreOrderDealTime(shippingTime))) {//当天配送时间为空串||当天已经没有可用配送时间
            return 3;
        } else if (status == 1 && preBook == 0) {
            return 4;
        }
        return -1;
    }

    private static List<TimeRange> getOpenTimeRange(String shipTime, Calendar now) {

        if (StringUtils.isEmpty(trimAllWhitespace(shipTime))) {
            return null;
        }

        List<TimeRange> timeRanges = new ArrayList<TimeRange>();

        for (String range : shipTime.split(CommonConstants.HALF_WIDTH_COMMA)) {
            if (StringUtils.isEmpty(range)) {
                continue;
            }

            String[] times = range.split(CommonConstants.CONNECTION_SIGN);
            if (times.length < 2) {
                continue;
            }

            Integer btime = changeStr2time(times[0], now);
            Integer etime = changeStr2time(times[1], now);
            if (btime == null || btime == 0 || etime == null || etime == 0) {
                continue;
            }

            TimeRange timeRange = new TimeRange();
            timeRange.btime = btime;
            timeRange.etime = etime;
            timeRanges.add(timeRange);
        }

        return timeRanges;
    }

    private static Integer changeStr2time(String btimestr, Calendar now) {
        String[] time = btimestr.split(":");
        Calendar c = (Calendar) now.clone();

        if (time.length >= 1) {
            int hour = Integer.parseInt(time[0]);
            if (hour < 0 || hour > 24) {
                return null;
            }
            // if hour == 24; time is tomorrow 0.
            c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
        }

        if (time.length >= 2) {
            int min = Integer.parseInt(time[1]);
            if (min < 0 || min > 59) {
                return null;
            }
            c.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        }

        return (int) (c.getTimeInMillis() / 1000);
    }

    private static TimeRange getLastTimeRange(List<TimeRange> openTimeRange,
                                              Calendar now) {
        if (openTimeRange == null) {
            return null;
        }

        int inow = (int) (now.getTimeInMillis() / 1000);

        for (TimeRange range : openTimeRange) {
            if (inow < range.btime) {
                return range;
            } else if (inow >= range.btime && inow <= range.etime) {
                return null;
            }
        }

        return null;
    }

    private static List<String> genDeliveryTimeRange(TimeRange timeRange) {
        List<String> ranges = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        for (int i = timeRange.btime; i < timeRange.etime; i += 20 * 60) {
            StringBuffer sb = new StringBuffer();
            sb.append(df.format(new Date(i * 1000L)));
            sb.append(CommonConstants.CONNECTION_SIGN);
            if (timeRange.etime - i < 2 * 20 * 60) {
                sb.append(df.format(new Date(timeRange.etime * 1000L)));
                ranges.add(sb.toString());
                return ranges;
            } else {
                sb.append(df.format(new Date(i * 1000L + 20 * 60 * 1000L)));
                ranges.add(sb.toString());
                continue;
            }
        }
        return ranges;
    }

    private static List<String> genDeliveryTimePoints(TimeRange timeRange) {
        List<String> ranges = new ArrayList<String>();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        for (int i = timeRange.btime; i < timeRange.etime; i += 20 * 60) {
            if (timeRange.etime - i < 2 * 20 * 60) {
                ranges.add(df.format(new Date(i * 1000L)));
                ranges.add(df.format(new Date(timeRange.etime * 1000L)));
                return ranges;
            } else {
                ranges.add(df.format(new Date(i * 1000L)));
            }
        }
        return ranges;
    }

    public static boolean poiSpTimeXValid(String shippingTimesStr) {
        return poiSpTimeXValid(shippingTimesStr, 0L);
    }

    public static boolean poiSpTimeXValidForSg(String shippingTimesStr) {
        return poiSpTimeXValidForSg(shippingTimesStr, 0L);
    }

    public static boolean poiSpTimeXValid(String shippingTimesStr, long wmPoiId) {

        if (StringUtils.isNotBlank(shippingTimesStr) && shippingTimesStr.contains("24:00")) {
            log.info("shippingTimesStr contains 24:00: wmPoiId[{}]", wmPoiId);
        }

        String[][] shippingTimeStrArrayX;
        try {
            JSON.DEFAULT_PARSER_FEATURE = Feature.config(JSON.DEFAULT_PARSER_FEATURE, Feature.AllowSingleQuotes, false);//配送时间不支持单引号
            shippingTimeStrArrayX = JSON.parseObject(shippingTimesStr, String[][].class, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
            JSON.DEFAULT_PARSER_FEATURE = Feature.config(JSON.DEFAULT_PARSER_FEATURE, Feature.AllowSingleQuotes, true);//还原配置
        } catch (Exception e) {
            log.warn("异常：", e);
            return false;
        }
        if (shippingTimeStrArrayX == null || shippingTimeStrArrayX.length != 7) {
            return false;
        }
        //match 0:00/00:00/1:00/01:00 --- 23:59
        String hhmmMatcher = "^((([0-9]{1})|([0-1][0-9])|([1-2][0-3])):([0-5][0-9]))|(23:59)$";

        for (String[] oneDay : shippingTimeStrArrayX) {
            String lastEndTime = null;
            for (String t : oneDay) {
                //0:00-3:30
                if (t.contains(CommonConstants.CONNECTION_SIGN)) {
                    String[] times = t.split(CommonConstants.CONNECTION_SIGN);
                    if (times.length == 2) {
                        String startTime = times[0];
                        String endTime = times[1];
                        if (startTime.length() < 5) {
                            startTime = "0" + startTime;
                        }
                        if (endTime.length() < 5) {
                            endTime = "0" + endTime;
                        }

                        if (!startTime.matches(hhmmMatcher) || !endTime.matches(hhmmMatcher) || startTime.compareTo(endTime) > 0
                                || (lastEndTime != null && lastEndTime.compareTo(startTime) > 0)) {//上一个seg的endTime不能大于下一个seg的startTime
                            return false;
                        }
                        lastEndTime = endTime;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        }
        return true;
    }

    public static boolean poiSpTimeXValidForSg(String shippingTimesStr, long wmPoiId) {
        if (StringUtils.isNotBlank(shippingTimesStr) && shippingTimesStr.contains("24:00")) {
            log.info("shippingTimesStr contains 24:00: wmPoiId[{}]", wmPoiId);
        }
        if (shippingTimesStr.startsWith("{")) {
            JSONObject shippingTimeJson = JSONObject.parseObject(shippingTimesStr);
            int code = shippingTimeJson.getIntValue("code");
            String value = shippingTimeJson.getString("value");
            switch (code) {
                case 1:
                    return validShippingTimeCodeOneStr(value);
                case 2:
                    return validShippingTimeCodeTwoStr(value);
                default:
                    return false;
            }
        } else {
            return validShippingTimeCodeOneStr(shippingTimesStr);
        }
    }

    public static boolean validShippingTimeCodeTwoStr(String shippingTimesStr) {
        if (StringUtils.isEmpty(shippingTimesStr)) {
            return false;
        }
        String[] shippingTimeStrArrayX;
        try {
            JSON.DEFAULT_PARSER_FEATURE = Feature.config(JSON.DEFAULT_PARSER_FEATURE, Feature.AllowSingleQuotes, false);//配送时间不支持单引号
            shippingTimeStrArrayX = JSON.parseObject(shippingTimesStr, String[].class, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
            JSON.DEFAULT_PARSER_FEATURE = Feature.config(JSON.DEFAULT_PARSER_FEATURE, Feature.AllowSingleQuotes, true);//还原配置
        } catch (Exception e) {
            log.warn("异常：", e);
            return false;
        }
        if (shippingTimeStrArrayX == null || shippingTimeStrArrayX.length != 2) {
            return false;
        }
        //yyyy-MM-dd hh:mm:ss的校验正则
        String matcher = "^(19|20)\\d\\d[-/.]((0[1-9])|([1-9])|(1[0-2]))[-/.](([0-2][1-9])|([1-2]0)|(3[0-1])|([1-9])) (([0-1]\\d)|([1-9])|(2[0-3]|(0))):(([0-5]\\d)|([1-9])):(([0-5]\\d)|([1-9]))$";
        //开始时间不符合规则校验失败
        if (!shippingTimeStrArrayX[0].matches(matcher)) {
            return false;
        }
        //结束时间 可以是 - 或者遵循正则规则 //开始时间必须要小于结束时间
        if (!CommonConstants.CONNECTION_SIGN.equals(shippingTimeStrArrayX[1])) {
            return shippingTimeStrArrayX[1].matches(matcher) && (shippingTimeStrArrayX[0].compareTo(shippingTimeStrArrayX[1]) < 0);
        }
        return true;
    }

    public static boolean validShippingTimeCodeOneStr(String shippingTimesStr) {
        if (StringUtils.isEmpty(shippingTimesStr)) {
            return false;
        }
        if ("-".equals(shippingTimesStr)) {
            return true;
        }
        String[][] shippingTimeStrArrayX;
        try {
            JSON.DEFAULT_PARSER_FEATURE = Feature.config(JSON.DEFAULT_PARSER_FEATURE, Feature.AllowSingleQuotes, false);//配送时间不支持单引号
            shippingTimeStrArrayX = JSON.parseObject(shippingTimesStr, String[][].class, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
            JSON.DEFAULT_PARSER_FEATURE = Feature.config(JSON.DEFAULT_PARSER_FEATURE, Feature.AllowSingleQuotes, true);//还原配置
        } catch (Exception e) {
            log.warn("异常：", e);
            return false;
        }
        if (shippingTimeStrArrayX == null || shippingTimeStrArrayX.length != 7) {
            return false;
        }
        //match 0:00/00:00/1:00/01:00 --- 23:59
        String hhmmMatcher = "^((([0-9]{1})|([0-1][0-9])|([1-2][0-3])):([0-5][0-9]))|(23:59)$";

        for (String[] oneDay : shippingTimeStrArrayX) {
            String lastEndTime = null;
            for (String t : oneDay) {
                //0:00-3:30
                if (t.contains(CommonConstants.CONNECTION_SIGN)) {
                    String[] times = t.split(CommonConstants.CONNECTION_SIGN);
                    if (times.length == 2) {
                        String startTime = times[0];
                        String endTime = times[1];
                        if (startTime.length() < 5) {
                            startTime = "0" + startTime;
                        }
                        if (endTime.length() < 5) {
                            endTime = "0" + endTime;
                        }

                        if (!startTime.matches(hhmmMatcher) || !endTime.matches(hhmmMatcher) || startTime.compareTo(endTime) > 0
                                || (lastEndTime != null && lastEndTime.compareTo(startTime) > 0)) {//上一个seg的endTime不能大于下一个seg的startTime
                            return false;
                        }
                        lastEndTime = endTime;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            }
        }
        return true;
    }

    public static class TimeRange {
        Integer btime;
        Integer etime;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.toString(this);
        }
    }
}
