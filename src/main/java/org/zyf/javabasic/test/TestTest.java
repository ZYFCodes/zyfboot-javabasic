package org.zyf.javabasic.test;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Sets;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

/**
 * @author yanfengzhang
 * @description
 * @date 2022/6/8  12:29
 */
public class TestTest {

    public static void main(String[] args) {
        String idPath = ",2,3,4,5,6,7";
        if (StringUtils.isNotEmpty(idPath) && idPath.startsWith(",")) {
            idPath = idPath.substring(1);
        }
        if (StringUtils.isNotEmpty(idPath) && idPath.endsWith(",")) {
            idPath = idPath.substring(0, idPath.length() - 1);
        }
        System.out.println(idPath);


        Set<Integer> provinceIds = Sets.newHashSet();
        provinceIds.add(2);
        provinceIds.add(2);
        provinceIds.add(6);
        provinceIds.add(5);
        provinceIds.add(2);
        System.out.println(provinceIds);


        System.out.println(System.currentTimeMillis());

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        //  2016/05/05-01:01:34:364
        System.out.println(new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss:SSS").format(date));

        Calendar calendar = Calendar.getInstance();
        long time = calendar.getTimeInMillis();
        System.out.println(time);
    }
}
