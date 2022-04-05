package org.zyf.javabasic.test;

import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 描述：
 *
 * @author yanfengzhang
 * @date 2020-04-21 20:07
 */
public class Solution {
    public static void main(String[] args) {
        String str1 = "abcdefg";
        String str2 = "adefgwgeweg";
        System.out.println(getMaxSubString(str1, str2));
    }

    public static List<String> getMaxSubString(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return null;
        }

        String max;
        String min;
        if (str1.length() > str2.length()) {
            max = str1;
            min = str2;
        } else {
            max = str2;
            min = str1;
        }

        List<String> subStrings = Lists.newArrayList();
        String maxSubString = "";
        for (int i = 0; i < min.length(); i++) {
            for (int begin = 0, end = min.length() - i; begin < end; begin++) {
                String tmp = min.substring(begin, end);
                if (max.contains(tmp) && tmp.length() >= maxSubString.length()) {
                    maxSubString = tmp;
                    subStrings.add(maxSubString);
                }
            }
        }
        return subStrings;
    }
}



