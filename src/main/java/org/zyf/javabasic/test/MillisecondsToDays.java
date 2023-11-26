package org.zyf.javabasic.test;

/**
 * @program: zyfboot-javabasic
 * @description: 天数
 * @author: zhangyanfeng
 * @create: 2023-11-26 22:56
 **/
public class MillisecondsToDays {
    public static void main(String[] args) {
        long milliseconds = 23328000000L;
        long days = convertMillisecondsToDays(milliseconds);
        System.out.println("Milliseconds: " + milliseconds);
        System.out.println("Days: " + days);
    }

    public static long convertMillisecondsToDays(long milliseconds) {
        // 1秒 = 1000毫秒
        // 1分钟 = 60秒
        // 1小时 = 60分钟
        // 1天 = 24小时

        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        return days;
    }
}
