package org.zyf.javabasic.test;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;

/**
 * @author yanfengzhang
 * @className DateTest
 * @description TODO
 * @date 2020/7/28 21:14
 */
public class DateTest {

    public static void main(String[] args) {
        int now1 = new Long(DateTime.now().getMillis() / 1000).intValue();
        System.out.println(now1);

        int flag = 1612166400;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now2 = sdf.format(new java.util.Date());
        String flagFormat = sdf.format(new java.util.Date((long) flag * 1000));
        System.out.println(now2.equals(flagFormat));
    }
}
