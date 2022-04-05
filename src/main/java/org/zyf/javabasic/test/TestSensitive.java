package org.zyf.javabasic.test;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author yanfengzhang
 * @description
 * @date 2021/7/21  17:21
 */
public class TestSensitive {

    public static void main(String[] args) {
        List<String> result = Lists.newArrayList();
        String stopWords = "@¥^…&（）()、。 ；：|【】[]{}-—_%*$#！/\\<>《》，,.:“”\"』『•·‘’'?？+=！!°";
        if (StringUtils.isNotEmpty(stopWords)) {
            for (int index = 0; index < stopWords.length(); index++) {
                result.add(String.valueOf(stopWords.charAt(index)));
            }
        }
        System.out.println(result);

        String ip = "10.83.224.94:8410,10.80.251.46:8410,10.184.93.251:8410,10.178.64.66:8410,10.85.34.60:8410,10.178.155.148:8410,10.73.133.172:8410,10.80.253.6:8410,10.178.138.218:8410,10.80.253.74:8410,10.184.90.117:8410,10.75.176.78:8410,10.54.169.144:8410,10.80.254.25:8410,10.81.209.148:8410,10.180.180.12:8410,10.85.54.85:8410,10.188.34.68:8410,10.82.52.187:8410";
        String[] ips = ip.split(",");
        System.out.println(ips.length);
    }
}
