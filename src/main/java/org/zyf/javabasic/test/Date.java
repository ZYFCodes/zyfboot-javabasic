package org.zyf.javabasic.test;

import org.joda.time.DateTime;

/**
 * @author yanfengzhang
 * @className Date
 * @description TODO
 * @date 2020/7/28 21:14
 */
public class Date {

    public static void main(String[] args) {
        int now = new Long(DateTime.now().getMillis() / 1000).intValue();
        System.out.println(now);
    }
}
